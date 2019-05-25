package etf.unsa.ba.api_gateway.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class JWTConfig {
    @Value("${security.jwt.uri:/auth/**}")
    private String Uri;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${token.ttl}")
    private int expiration;
}
