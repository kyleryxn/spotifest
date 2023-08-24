package com.spotifest.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Component
public class SpotifyTokenExchange {
    private static final String TOKEN_ENDPOINT = "https://accounts.spotify.com/api/token";

    @Value("${spotify.client.id}")
    private String clientId;

    @Value("${spotify.client.secret}")
    private String clientSecret;

    public String exchangeCodeForAccessToken(String authorizationCode, String redirectUri) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(clientId, clientSecret);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("code", authorizationCode);
        body.add("redirect_uri", redirectUri);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        var responseEntity = restTemplate.exchange(TOKEN_ENDPOINT, HttpMethod.POST, requestEntity, Map.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return (String) Objects.requireNonNull(responseEntity.getBody()).get("access_token");
        } else {
            throw new RuntimeException("Failed to exchange authorization code for access token");
        }
    }

}