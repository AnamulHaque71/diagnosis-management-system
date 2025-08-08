package com.example.dms.config;

import com.example.dms.model.PatientModel;
import com.example.dms.model.UserModel;
import com.example.dms.service.PatientService;
import com.example.dms.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private PatientService patientService;
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        boolean isAdmin = authorities.stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            // Example: get patientId dynamically (replace with your logic)

            if (authentication != null && authentication.isAuthenticated()) {
                // Get logged-in user email (username)
                String email = authentication.getName();

                // Load full user from DB (assuming userService exists)
                UserModel user = userService.findByEmail(email);
                PatientModel patientModel = patientService.findByUserId(user.getId());

                Long patientId = patientModel.getId(); // TODO: Replace with real patient ID
                response.sendRedirect(request.getContextPath() + "/patient/view/" + patientId);
            }


        }
    }
}
