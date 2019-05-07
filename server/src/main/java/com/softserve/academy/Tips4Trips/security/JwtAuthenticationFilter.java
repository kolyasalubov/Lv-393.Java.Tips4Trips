package com.softserve.academy.Tips4Trips.security;

import com.softserve.academy.Tips4Trips.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserDetailsServiceImpl customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                        throws ServletException, IOException {
        if(true)
        {filterChain.doFilter(request, response);
        return;  }
        String jwt = getJwtFromRequest(request);

            if (!StringUtils.hasText(jwt)) {
                filterChain.doFilter(request, response);
                return;
            } else if (tokenProvider.validateToken(jwt)) {
                try {
                    refreshSession(request, jwt);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ServletException("Could not set user"
                            + " authentication in security context while "
                            + "checking authentication token! Try to log out "
                            + "and log in again.");
                }
            } else {
                throw new ServletException("Authentication token not valid! " +
                        "Try to log out and log in again.");
            }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken)
                && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7, bearerToken.length());
            return token.equals("Invalid") ? null : token;
        }
        return null;
    }

    private String refreshToken() {
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String newToken = tokenProvider.generateToken(authentication);
            return "Bearer " + newToken;
        }
        return null;
    }

    private void refreshSession(HttpServletRequest request, String jwt) {
        Long userId = tokenProvider.getUserIdFromJWT(jwt);

        UserDetails userDetails = customUserDetailsService
                .loadUserById(userId);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities()
                );
        authentication.setDetails(new WebAuthenticationDetailsSource()
                .buildDetails(request)
        );

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
    }
}