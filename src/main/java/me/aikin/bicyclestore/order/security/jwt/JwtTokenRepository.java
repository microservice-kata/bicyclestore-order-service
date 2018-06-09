package me.aikin.bicyclestore.order.security.jwt;

public interface JwtTokenRepository {
    String extractAuthorizedPayload(String jwtToken);

    boolean validateToken(String jwtToken);
}
