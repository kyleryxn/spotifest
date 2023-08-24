package com.spotifest.service;

import com.spotifest.model.Artist;

import java.util.List;

public interface ArtistService {

    List<Artist> getTopArtists(String accessToken);

}
