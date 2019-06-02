package etf.unsa.ba.user_management.jwt;

import etf.unsa.ba.user_management.jwt.exception.InvalidTokenException;
import etf.unsa.ba.user_management.model.TokenRequest;

import java.time.OffsetDateTime;

public interface JWTProvider {
    String create(TokenRequest tokenRequest, OffsetDateTime expirationTime);

    JWTToken decode(String token) throws InvalidTokenException;
}
