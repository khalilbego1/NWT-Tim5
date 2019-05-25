package etf.unsa.ba.api_gateway.jwt.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import etf.unsa.ba.api_gateway.jwt.JWTProvider;
import etf.unsa.ba.api_gateway.jwt.JWTToken;
import etf.unsa.ba.api_gateway.jwt.exception.InvalidTokenException;
import etf.unsa.ba.api_gateway.model.TokenRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.OffsetDateTime;

@Service
public class DefaultJWTProvider implements JWTProvider {
    private static final String JWT_ISSUER = "TravelAgency";
    private static final String JWT_SECRET = "TravelAgency_secret";

    private Algorithm algorithm;
    private JWTVerifier jwtVerifier;

    public DefaultJWTProvider() {
        this.algorithm = Algorithm.HMAC256(JWT_SECRET);
        this.jwtVerifier = JWT.require(algorithm)
                .withIssuer(JWT_ISSUER)
                .build();
    }

    @Override
    public String create(TokenRequest tokenRequest, OffsetDateTime expirationTime) {
        return JWT.create()
                .withIssuer(JWT_ISSUER)
                .withExpiresAt(Date.from(expirationTime.toInstant()))
                .withClaim("username", tokenRequest.getUsername())
                .withClaim("password", tokenRequest.getPassword())
                .sign(algorithm);
    }

    @Override
    public JWTToken decode(String token) throws InvalidTokenException {
        try {
            DecodedJWT jwt = jwtVerifier.verify(token);
            return new DefaultJWTToken(jwt);
        } catch (Exception e) {
            throw new InvalidTokenException(e.getMessage());
        }
    }
}
