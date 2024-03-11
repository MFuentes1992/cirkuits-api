package com.cirkuits.cirkuitsapi.Auth;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;

import java.security.interfaces.RSAPublicKey;

public class AuthService {
    private DecodedJWT jwt;
    private JwkProvider provider;

    private Algorithm algorithm;
    private Jwk jwk;
    @Value("${cirkuits.auth0.jwks.uri}")
    private String jwkUri;
    public AuthService(String token) throws JwkException {
        jwt = JWT.decode(token);
        provider = new UrlJwkProvider(jwkUri);
    }
    public boolean isValidToken() throws  JwkException {
        jwk = provider.get(jwt.getKeyId());
        algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
        algorithm.verify(jwt);
        return true;
    }

    public boolean isTokenExpired() {
        return jwt.getExpiresAt().after(new java.util.Date());
    }
}
