package etf.unsa.ba.api_gateway.filter;

import etf.unsa.ba.api_gateway.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    public JwtTokenAuthenticationFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }
        String token = header.replace("Bearer ", "");

        try {
            RestTemplate restTemplate = new RestTemplate();
            UserEntity userEntity = restTemplate.postForObject("http://localhost:8585/travelAgency/user-service/validate", token, UserEntity.class);
            List<String> authorities = Collections.singletonList("ROLE_" + userEntity.getRole().getType().toString());
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userEntity.getUsername(),
                    userEntity.getPassword(),
                    authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }
        chain.doFilter(request, response);
    }
}
