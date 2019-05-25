package etf.unsa.ba.api_gateway.service;

import etf.unsa.ba.api_gateway.model.TokenRequest;
import etf.unsa.ba.api_gateway.model.TokenResponse;

public interface TokenService {
    TokenResponse generate(TokenRequest tokenRequest);
}
