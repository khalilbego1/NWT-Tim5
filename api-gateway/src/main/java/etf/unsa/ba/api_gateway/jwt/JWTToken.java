package etf.unsa.ba.api_gateway.jwt;

import java.util.Date;
import java.util.List;

public interface JWTToken {
    String getUsername();
    Date getExpirationTime();
    String getPassword();
    List<String> getRoles();
}
