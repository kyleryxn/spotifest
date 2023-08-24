package com.spotifest.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SpotifyApiClient {
    private static final String USER_ENDPOINT = "https://api.spotify.com/v1/me";
    private static final String TOP_ARTISTS_ENDPOINT = "https://api.spotify.com/v1/me/top/artists?time_range=long_term&offset=0&limit=50";

    public ResponseEntity<String> getUsername(String accessToken) {
        return getStringResponseEntity(accessToken, USER_ENDPOINT);
    }

    public ResponseEntity<String> getTopArtists(String accessToken) {
        return getStringResponseEntity(accessToken, TOP_ARTISTS_ENDPOINT);
    }

    private ResponseEntity<String> getStringResponseEntity(String accessToken, String endpoint) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(endpoint, HttpMethod.GET, requestEntity, String.class);
    }

}