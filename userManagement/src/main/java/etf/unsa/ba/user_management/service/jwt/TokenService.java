package etf.unsa.ba.user_management.service.jwt;

import etf.unsa.ba.user_management.model.TokenRequest;
import etf.unsa.ba.user_management.model.TokenResponse;
import etf.unsa.ba.user_management.model.entity.UserEntity;

public interface TokenService {
    TokenResponse generate(TokenRequest tokenRequest, UserEntity user);
}
