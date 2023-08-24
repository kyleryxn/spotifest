package com.spotifest.model;

import java.util.List;

public record Artist(String id, String name, List<String> genres) {

    @Override
    public String toString() {
        return "Artist{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", genres=" + genres +
                '}';
    }

}
