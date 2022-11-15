package kz.kdlolymp.gynecology.config;

import kz.kdlolymp.gynecology.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeTemporaryPasswordAuthenticationHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username;
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }else {
            username = principal.toString();
        }
        boolean hasTemporaryPassword = userService.findByUsername(username).isTemporary();
        if (hasTemporaryPassword) {
            response.sendRedirect("change-password");
        } else {
            response.sendRedirect("work-page");
        }
    }
}
