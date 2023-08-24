package com.spotifest.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.spotifest.api.SpotifyApiClient;
import com.spotifest.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpotifyArtistService implements ArtistService {
    private final SpotifyApiClient apiClient;
    private final Gson gson;

    @Autowired
    public SpotifyArtistService(SpotifyApiClient apiClient) {
        this.apiClient = apiClient;
        this.gson = new Gson();
    }

    @Override
    public List<Artist> getTopArtists(String accessToken) {
        List<Artist> artists = new ArrayList<>();
        ResponseEntity<String> topArtistsResponse = apiClient.getTopArtists(accessToken);
        String topArtistsJson = topArtistsResponse.getBody();

        JsonObject jsonObjectArtists = gson.fromJson(topArtistsJson, JsonObject.class);
        JsonArray artistItems = jsonObjectArtists.getAsJsonArray("items");

        for (JsonElement item : artistItems) {
            JsonObject artistObject = item.getAsJsonObject();
            String name = artistObject.get("name").getAsString();
            String id = artistObject.get("id").getAsString();
            List<String> genres = extractGenres(artistObject.getAsJsonArray("genres"));

            artists.add(new Artist(id, name, genres));
        }

        return artists;
    }

    public List<String> generateStageHostNames(List<Artist> artists) {
        // Create a map to store genre occurrences
        Map<String, Integer> genreOccurrences = new HashMap<>();

        for (Artist artist : artists) {
            for (String genre : artist.genres()) {
                genreOccurrences.put(genre, genreOccurrences.getOrDefault(genre, 0) + 1);
            }
        }

        // Get the most recurring genres
        List<String> recurringGenres = getRecurringGenres(genreOccurrences);

        // Create stage host names based on recurring genres
        // Create dynamic stage host names using genre (e.g., "ElectroBeat Arena")

        return new ArrayList<>(recurringGenres);
    }

    private List<String> extractGenres(JsonArray genresArray) {
        List<String> genres = new ArrayList<>();

        for (JsonElement genreElement : genresArray) {
            genres.add(genreElement.getAsString());
        }

        return genres;
    }

    private List<String> getRecurringGenres(Map<String, Integer> genreOccurrences) {
        // Sort genres based on occurrences
        List<String> recurringGenres = new ArrayList<>(genreOccurrences.keySet());
        recurringGenres.sort((genre1, genre2) -> genreOccurrences.get(genre2) - genreOccurrences.get(genre1));

        // You can decide how many genres you want to consider as "recurring"
        int numRecurringGenres = 4; // Adjust this as needed
        return recurringGenres.subList(0, Math.min(numRecurringGenres, recurringGenres.size()));
    }
}
