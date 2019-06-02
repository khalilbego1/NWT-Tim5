package etf.unsa.ba.user_management.service.jwt;

import etf.unsa.ba.user_management.jwt.impl.DefaultJWTProvider;
import etf.unsa.ba.user_management.model.TokenRequest;
import etf.unsa.ba.user_management.model.TokenResponse;
import etf.unsa.ba.user_management.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TokenServiceImpl implements TokenService {
    private final DefaultJWTProvider jwtProvider;
    @Value("${token.ttl}")
    private Long tokenTimeToLive;

    public TokenServiceImpl(DefaultJWTProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public TokenResponse generate(TokenRequest tokenRequest, UserEntity user) {
        OffsetDateTime expirationTime = OffsetDateTime.now().plusMinutes(tokenTimeToLive);
        String token = jwtProvider.create(tokenRequest, user, expirationTime);

        return TokenResponse.builder()
                .token(token)
                .expirationTime(expirationTime.format(DateTimeFormatter.ISO_DATE_TIME))
                .build();
    }
}
