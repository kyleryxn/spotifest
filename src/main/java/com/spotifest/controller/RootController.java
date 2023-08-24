package com.spotifest.controller;

import com.spotifest.api.SpotifyAuthUrlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    private final SpotifyAuthUrlBuilder authorizationUrl;

    @Autowired
    public RootController(SpotifyAuthUrlBuilder authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }

    @GetMapping("/")
    public String connect(Model model) {
        String spotifyUrl = authorizationUrl.createAuthorizationUrl();

        model.addAttribute("connection", spotifyUrl);

        return "index";
    }

}
