package qms.config.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import qms.service.user.CustomUserService;

@EnableWebSecurity
@Profile("h2")
public class ApiWebSecurityConfigH2 extends WebSecurityConfigurerAdapter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomUserService loginUserDetailsService;

	@Autowired
	private CustomLoginHandler customLoginHandler;

	@Autowired
	private CustomLogoutHandler customLogoutHandler;

	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;

	// @Autowired
	// private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		// auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
		// .withUser("feng").password("123456").roles("ADMIN").and().withUser("user").password("123456").roles("USER");
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.cors();

		http.authorizeRequests()

				// below code doesn't work, because registered FilterSecurityInterceptor will
				// intercept all request URL
				// in MyAccessDecisionManager will released permitted URL

				// allow anonymous access access to Swagger docs
				.antMatchers("/v2/api-docs", "/**/swagger-ui.html", "/webjars/**", "/swagger-resources/**",
						"/configuration/**")
				.permitAll()
				// allow anonymous access treant resource, icon resource
				.antMatchers("/", "/index.html", "/resources/**", "/treant/**", "/icons/**").permitAll()
				.antMatchers("/h2-console/**").permitAll()
				// allow anonymous check his session
				.antMatchers("/my/session").permitAll().antMatchers("/api/**").authenticated()
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

		 // this will ignore only h2-console csrf, spring security 4+
		 http.csrf().ignoringAntMatchers("/h2-console/**");
		 //this will allow frames with same origin which is much more safe
		 http.headers().frameOptions().sameOrigin();

		http.formLogin();

		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/api/session/logout"))
				// 登出前调用，可用于日志
				.addLogoutHandler(customLogoutHandler)
				// 登出后调用，用户信息已不存在
				.logoutSuccessHandler(customLogoutHandler);

		http.exceptionHandling()
				// 已登入用户的权限错误
				.accessDeniedHandler(customAccessDeniedHandler)
				// 未登入用户的权限错误
				.authenticationEntryPoint(customAccessDeniedHandler);

		http.csrf()
				// 登入API不启用CSFR检查
				.ignoringAntMatchers("/api/session/**");

		http.addFilterBefore(new AcceptHeaderLocaleFilter(), UsernamePasswordAuthenticationFilter.class);

		// 替换原先的表单登入 Filter
		http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		// 绑定 CSRF TOKEN 到响应的 HEADER 上
		http.addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class);

		// 拦截所有的请求进行定制的授权验证
		// http.addFilterBefore(myFilterSecurityInterceptor,
		// FilterSecurityInterceptor.class);
	}

	// @Override
	// public void configure(WebSecurity webSecurity) throws Exception
	// {
	// webSecurity
	// .ignoring()
	// .antMatchers("/resources/**").anyRequest();
	// }

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		logger.debug("register cors filter");
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		// configuration.setAllowedHeaders(Arrays.asList("authorization",
		// "content-type", "x-auth-token"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setExposedHeaders(Arrays.asList("X-CSRF-TOKEN"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	private CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
		CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
		filter.setAuthenticationSuccessHandler(customLoginHandler);
		filter.setAuthenticationFailureHandler(customLoginHandler);
		filter.setAuthenticationManager(authenticationManager());
		filter.setFilterProcessesUrl("/api/session/login");
		return filter;
	}

	private static class AcceptHeaderLocaleFilter implements Filter {
		private AcceptHeaderLocaleResolver localeResolver;

		private AcceptHeaderLocaleFilter() {
			localeResolver = new AcceptHeaderLocaleResolver();
			localeResolver.setDefaultLocale(Locale.US);
		}

		@Override
		public void init(FilterConfig filterConfig) {
		}

		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			Locale locale = localeResolver.resolveLocale((HttpServletRequest) request);
			LocaleContextHolder.setLocale(locale);

			chain.doFilter(request, response);
		}

		@Override
		public void destroy() {
		}
	}

}
