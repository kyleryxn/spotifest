package com.spotifest.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class SpotifyAuthUrlBuilder {

    @Value("${spotify.client.id}")
    String client_id;

    public String createAuthorizationUrl() {
        String scope = "user-top-read user-read-email";
        String redirect_uri = "http://localhost:8080/lineup";

        JsonObject queryParams = new JsonObject();
        queryParams.add("response_type", new JsonPrimitive("code"));
        queryParams.add("client_id", new JsonPrimitive(client_id));
        queryParams.add("scope", new JsonPrimitive(scope));
        queryParams.add("redirect_uri", new JsonPrimitive(redirect_uri));

        String queryString = queryParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + URLEncoder.encode(entry.getValue().getAsString(), StandardCharsets.UTF_8))
                .reduce((param1, param2) -> param1 + "&" + param2)
                .orElse("");

        return "https://accounts.spotify.com/authorize?" + queryString;
    }

}
