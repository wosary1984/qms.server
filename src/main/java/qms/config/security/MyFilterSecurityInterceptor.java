package qms.config.security;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
@Service
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter{

	// ~ Static fields/initializers
	// =====================================================================================
	private static final String FILTER_APPLIED = "__spring_security_filterSecurityInterceptor_filterApplied";
		
	@Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;
	
	@Autowired
    public void setMyAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager) {
        super.setAccessDecisionManager(myAccessDecisionManager);
    }
	
	private boolean observeOncePerRequest = true;
	

	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }
	
	public void invoke(FilterInvocation fi) throws IOException, ServletException {
		
		if ((fi.getRequest() != null)
				&& (fi.getRequest().getAttribute(FILTER_APPLIED) != null)
				&& observeOncePerRequest) {
			// filter already applied to this request and user wants us to observe
			// once-per-request handling, so don't re-do security checking
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		}
		else {
			// first time this request being called, so perform security checking
			if (fi.getRequest() != null) {
				fi.getRequest().setAttribute(FILTER_APPLIED, Boolean.TRUE);
			}
			//这里做主要URL比对，将当前URL与securityMetadataSource(我们自己配置)中的URL过滤条件进行比对
			//首先判断当前URL是permit的还是需要验证的
			//若需要验证，尝试加载保存在SecurityContextHolder.getContext()中的已登录信息
			//调用AbstractSecurityInterceptor中的AccessDecisionManager对象的decide方法
			//如果对于配置中需要登录才可访问的URL，已经查找到登录信息，则执行下一个Filter
			InterceptorStatusToken token = super.beforeInvocation(fi);
			try {
				fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
			}
			finally {
				super.finallyInvocation(token);
			}
			super.afterInvocation(token, null);
		}
		
    }

	@Override
	public Class<?> getSecureObjectClass() {
		// TODO Auto-generated method stub
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
