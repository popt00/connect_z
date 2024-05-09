package ca.parimal.connectz.controller;

import ca.parimal.connectz.model.FetchUser;
import ca.parimal.connectz.model.entities.MediaListCollection;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomePage {
    @GetMapping("/getUser/{username}")
    public String getUser(@PathVariable String username, Model model) throws IOException {
        //System.out.println(username);
        MediaListCollection mediaListCollection = new MediaListController().getMediaList(username);
        //userData.scrape();
//        System.out.println(userData.getSeriesRatingData());
        model.addAttribute("userData",mediaListCollection);
        return "home";
    }

}
