package me.aikin.bicyclestore.order.security.jwt;

import me.aikin.bicyclestore.order.security.principal.UserPrincipal;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    boolean validateToken(HttpServletRequest request);

    UserPrincipal getAuthorizedCurrentUser(HttpServletRequest request);
}
