package qms.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import qms.controller.BaseController;

@Component
public class CustomLoginHandler extends BaseController
		implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

	// Login Success
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
		logger.info("User login successfully, name={}, requested seesionid={}, current seesionid={}",
				authentication.getName(), request.getRequestedSessionId(), request.getSession(false).getId());
		responseText(response,
				successResult(ACTION_LOGIN, HttpServletResponse.SC_OK, BaseController.getJSON(authentication)));
	}

	// Login Failure
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException {
		logger.info("Login failed: {}, requested seesionid={}, current seesionid={}", exception.getMessage(),
				request.getRequestedSessionId(), request.getSession(false).getId());
		responseText(response, errorResult(ACTION_LOGIN, HttpServletResponse.SC_OK, exception.getMessage()));
	}

}