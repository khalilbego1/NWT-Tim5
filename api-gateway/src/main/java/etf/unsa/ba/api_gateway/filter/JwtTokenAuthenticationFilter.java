package etf.unsa.ba.api_gateway.filter;

import etf.unsa.ba.api_gateway.config.JWTConfig;
import etf.unsa.ba.api_gateway.jwt.JWTProvider;
import etf.unsa.ba.api_gateway.jwt.JWTToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("NullableProblems")
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    private final JWTConfig jwtConfig;
    private final JWTProvider jwtProvider;

    public JwtTokenAuthenticationFilter(JWTConfig jwtConfig, JWTProvider jwtProvider) {
        this.jwtConfig = jwtConfig;
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader(jwtConfig.getHeader());
        if (header == null || !header.startsWith(jwtConfig.getPrefix())) {
            chain.doFilter(request, response);
            return;
        }
        String token = header.replace(jwtConfig.getPrefix(), "");

        try {
            JWTToken decodedToken = jwtProvider.decode(token);
            String username = decodedToken.getUsername();
            if (username != null) {
                List<String> authorities = (List<String>) decodedToken.getRoles();
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username,
                        null,
                        authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }
    }
}
