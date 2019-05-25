package etf.unsa.ba.api_gateway.jwt.impl;

import etf.unsa.ba.api_gateway.jwt.JWTProvider;
import etf.unsa.ba.api_gateway.jwt.JWTToken;
import etf.unsa.ba.api_gateway.model.TokenRequest;

import java.time.OffsetDateTime;

public class JWTProviderImpl implements JWTProvider {
    @Override
    public String create(TokenRequest tokenRequest, OffsetDateTime expirationTime) {
        return null;
    }

    @Override
    public JWTToken decode(String token) {
        return null;
    }
}
