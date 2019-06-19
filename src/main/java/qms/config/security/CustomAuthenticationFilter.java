package qms.config.security;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		
        UsernamePasswordAuthenticationToken authRequest;
        
        logger.info("attempt to authenticate user");
        try (InputStream is = request.getInputStream()) {
            // 使用JsonPath读取JSON请求，你也可以换成你喜欢的库
            DocumentContext context = JsonPath.parse(is);
            String username = context.read("$.username", String.class);
            String password = context.read("$.password", String.class);
            authRequest = new UsernamePasswordAuthenticationToken(username, password);
        } catch (IOException  | PathNotFoundException  e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            authRequest = new UsernamePasswordAuthenticationToken("", "");
        }
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
	
	protected boolean isPreflight(HttpServletRequest request) {
        return "OPTIONS".equals(request.getMethod());
    }
	
	protected boolean isPostRequest(HttpServletRequest request) {
		return "POST".equals(request.getMethod());
    }

}
