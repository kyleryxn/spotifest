package com.spotifest.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spotifest.api.SpotifyApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SpotifyUserService implements UserService {
    private final SpotifyApiClient apiClient;
    private final Gson gson;

    @Autowired
    public SpotifyUserService(SpotifyApiClient apiClient) {
        this.apiClient = apiClient;
        this.gson = new Gson();
    }

    @Override
    public String getUsername(String accessToken) {
        ResponseEntity<String> userDataResponse = apiClient.getUsername(accessToken);
        String usernameJson = userDataResponse.getBody();
        JsonObject jsonObjectUsername = gson.fromJson(usernameJson, JsonObject.class);

        return jsonObjectUsername.get("display_name").getAsString().replaceAll("\\s", "");
    }

}
