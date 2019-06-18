package qms.config.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import qms.controller.BaseController;

@Component
public class CustomLogoutHandler extends BaseController implements LogoutHandler, LogoutSuccessHandler {
    // Before Logout
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            logger.info("Before==>user={} logout, requested sessionid={}", authentication.getName(),
                    request.getRequestedSessionId());
        } else {
            logger.info("requested sessionid={}, authentication is null", request.getRequestedSessionId());
        }

    }

    // After Logout
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        if (authentication != null) {
            logger.info("After==>user={} logout,requested session id={}", authentication.getName(),
                    request.getRequestedSessionId());
        } else {
            logger.info("requested sessionid={}, authentication is null", request.getRequestedSessionId());
        }
        responseText(response, successResult(ACTION_LOGOUT, HttpServletResponse.SC_OK, BaseController.getJSON(null)));
    }
}