package etf.unsa.ba.user_management.jwt.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import etf.unsa.ba.user_management.jwt.JWTToken;

import java.util.Date;

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
    public Integer getId() {
        return decodedJWT.getClaim("username").asInt();
    }

    @Override
    public Date getExpirationTime() {
        return decodedJWT.getExpiresAt();
    }

    @Override
    public String getRole() {
        return decodedJWT.getClaim("role").asString();
    }
}
