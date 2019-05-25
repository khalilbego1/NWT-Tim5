package etf.unsa.ba.api_gateway.jwt;

import etf.unsa.ba.api_gateway.jwt.exception.InvalidTokenException;
import etf.unsa.ba.api_gateway.model.TokenRequest;

import java.time.OffsetDateTime;

public interface JWTProvider {
    String create(TokenRequest tokenRequest, OffsetDateTime expirationTime);
    JWTToken decode(String token) throws InvalidTokenException;
}
