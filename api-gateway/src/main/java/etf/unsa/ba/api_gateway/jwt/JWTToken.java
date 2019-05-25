package etf.unsa.ba.api_gateway.jwt;

import java.util.Date;

public interface JWTToken {
    String getUsername();
    Date getExpirationTime();
    String getPassword();
}
