package etf.unsa.ba.user_management.jwt;

import etf.unsa.ba.user_management.jwt.exception.InvalidTokenException;
import etf.unsa.ba.user_management.model.TokenRequest;
import etf.unsa.ba.user_management.model.entity.UserEntity;

import java.time.OffsetDateTime;

public interface JWTProvider {
    String create(TokenRequest tokenRequest, UserEntity user, OffsetDateTime expirationTime);

    JWTToken decode(String token) throws InvalidTokenException;
}
