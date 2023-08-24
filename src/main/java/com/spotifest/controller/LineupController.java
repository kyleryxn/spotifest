package com.spotifest.controller;

import com.spotifest.api.SpotifyTokenExchange;
import com.spotifest.model.Artist;
import com.spotifest.model.DateInfo;
import com.spotifest.service.SpotifyArtistService;
import com.spotifest.service.SpotifyUserService;
import com.spotifest.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LineupController {
    private final SpotifyTokenExchange tokenExchange;
    private final SpotifyUserService userService;
    private final SpotifyArtistService artistService;

    @Autowired
    public LineupController(SpotifyTokenExchange tokenExchange, SpotifyUserService userService, SpotifyArtistService artistService) {
        this.tokenExchange = tokenExchange;
        this.userService = userService;
        this.artistService = artistService;
    }

    @GetMapping("/lineup")
    public String createLineup(@RequestParam(value = "code") String authCode, Model model) {
        String accessToken = tokenExchange.exchangeCodeForAccessToken(authCode, "http://localhost:8080/lineup");

        String username = userService.getUsername(accessToken);
        List<Artist> artists = artistService.getTopArtists(accessToken);
        List<String> stageHosts = artistService.generateStageHostNames(artists);

        DateInfo dateInfo = DateUtil.getCurrentDateInfo();

        model.addAttribute("dayOne", dateInfo.dayOne());
        model.addAttribute("dayTwo", dateInfo.dayTwo());
        model.addAttribute("dayThree", dateInfo.dayThree());
        model.addAttribute("dateOne", dateInfo.dayOneDate());
        model.addAttribute("dateTwo", dateInfo.dayTwoDate());
        model.addAttribute("dateThree", dateInfo.dayThreeDate());
        model.addAttribute("month", dateInfo.month());
        model.addAttribute("year", dateInfo.year());

        model.addAttribute("username", username);
        model.addAttribute("artists", artists);
        model.addAttribute("stageHosts", stageHosts);

        System.out.println();
        for (Artist artist : artists) {
            System.out.println(artist);
        }

        return "lineup";
    }

}
