package etf.unsa.ba.api_gateway.service.impl;

import etf.unsa.ba.api_gateway.jwt.impl.DefaultJWTProvider;
import etf.unsa.ba.api_gateway.model.TokenRequest;
import etf.unsa.ba.api_gateway.model.TokenResponse;
import etf.unsa.ba.api_gateway.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TokenServiceImpl implements TokenService {
    private final DefaultJWTProvider jwtProvider;
    @Value("${token.ttl}")
    private Long tokenTimeToLive;

    public TokenServiceImpl(DefaultJWTProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public TokenResponse generate(TokenRequest tokenRequest) {
        OffsetDateTime expirationTime = OffsetDateTime.now().plusMinutes(tokenTimeToLive);
        String token = jwtProvider.create(tokenRequest, expirationTime);

        return TokenResponse.builder()
                .token(token)
                .expirationTime(expirationTime.format(DateTimeFormatter.ISO_DATE_TIME))
                .build();
    }
}
