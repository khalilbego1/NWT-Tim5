package etf.unsa.ba.api_gateway.jwt.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import etf.unsa.ba.api_gateway.jwt.JWTToken;

import java.util.Date;
import java.util.List;

public class DefaultJWTToken implements JWTToken {
    private DecodedJWT decodedJWT;

    public DefaultJWTToken(DecodedJWT decodedJWT) {
        this.decodedJWT = decodedJWT;
    }

    @Override
    public String getUsername() {
        return decodedJWT.getClaim("username").asString();
    }

    @Override
    public Date getExpirationTime() {
        return decodedJWT.getExpiresAt();
    }

    @Override
    public String getPassword() {
        return decodedJWT.getClaim("password").asString();
    }

    @Override
    public List<String> getRoles() {
        return decodedJWT.getClaim("roles").asList(String.class);
    }
}
