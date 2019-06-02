package etf.unsa.ba.user_management.jwt;

import java.util.Date;

public interface JWTToken {
    String getUsername();

    Integer getId();

    Date getExpirationTime();

    String getRole();
}
