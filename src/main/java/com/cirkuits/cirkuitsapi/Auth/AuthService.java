package com.cirkuits.cirkuitsapi.Auth;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.security.interfaces.RSAPublicKey;

public class AuthService {
    private DecodedJWT jwt;

    private JwkProvider provider;

    private Algorithm algorithm;

    private Jwk jwk;


    public AuthService(String token, String jwkUri) throws JwkException {
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
        System.out.printf("Token expires at: %s\n", jwt.getExpiresAt());
        return jwt.getExpiresAt().before(new java.util.Date());
    }
}
