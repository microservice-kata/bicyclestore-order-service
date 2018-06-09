package me.aikin.bicyclestore.order.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import me.aikin.bicyclestore.order.utils.JsonHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class JwtTokenRepositoryImpl implements JwtTokenRepository  {
    @Value("${security.jwt.secret:_SEMS_JWT_SECRET_201805260909999}")
    private String jwtSecret;

    @Value("${security.jwt.expiration-in-seconds}")
    private long jwtExpirationInSeconds;


    @Override
    public String extractAuthorizedPayload(String jwtToken) {
        Claims claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(jwtToken)
            .getBody();
        return JsonHelper.toJson(claims);
    }

    @Override
    public boolean validateToken(String jwtToken) {
        if (!StringUtils.hasText(jwtToken)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
