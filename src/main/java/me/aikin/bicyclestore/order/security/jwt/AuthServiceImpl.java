package me.aikin.bicyclestore.order.security.jwt;

import me.aikin.bicyclestore.order.exception.InvalidCredentialException;
import me.aikin.bicyclestore.order.security.principal.UserPrincipal;
import me.aikin.bicyclestore.order.utils.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthServiceImpl implements AuthService {

    @Value("${security.jwt.token-prefix:Bearer}")
    private String tokenPrefix;

    @Value("${security.jwt.token-key:Authorization}")
    private String tokenKey;

    @Autowired
    private JwtTokenRepository jwtTokenRepository;


    @Override
    public boolean validateToken(HttpServletRequest request) {
        String jwtToken = getJwtFromRequest(request);
        return jwtTokenRepository.validateToken(jwtToken);
    }

    @Override
    public UserPrincipal getAuthorizedCurrentUser(HttpServletRequest request) {
        String jwtToken = getJwtFromRequest(request);
        if (jwtTokenRepository.validateToken(jwtToken)) {
            return JsonHelper.parseJson(jwtTokenRepository.extractAuthorizedPayload(jwtToken), UserPrincipal.class);
        }
        throw new InvalidCredentialException();
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(tokenKey);
        String prefix = String.join(" ", tokenPrefix);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(prefix)) {
            return bearerToken.substring(prefix.length(), bearerToken.length());
        }
        return null;
    }
}
