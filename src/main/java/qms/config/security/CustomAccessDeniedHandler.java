package qms.config.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import qms.controller.BaseController;

@Component
public class CustomAccessDeniedHandler extends BaseController implements AuthenticationEntryPoint, AccessDeniedHandler {
    // NoLogged Access Denied
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        logger.debug("anonymousUser access:{}, method:{},requested session id:{}", request.getRequestURI(),
                request.getMethod(), request.getRequestedSessionId());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        responseText(response,
                errorResult(ACTION_ACCESS, HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage()));
    }

    // Logged Access Denied
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException {
        logger.debug("{} access:{}, method:{}, requested session id:{}", request.getRemoteUser(),
                request.getRequestURI(), request.getMethod(), request.getRequestedSessionId());
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        responseText(response,
                errorResult(ACTION_ACCESS, HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage()));
    }
}