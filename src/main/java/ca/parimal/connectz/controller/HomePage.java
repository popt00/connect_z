package ca.parimal.connectz.controller;

import ca.parimal.connectz.model.entities.graphql.MediaListCollection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class HomePage {


    @GetMapping("/getUser/{username}")
    public String getUser(@PathVariable String username, Model model) throws IOException {
        //System.out.println(username);
        MediaListCollection mediaListCollection = new MediaListController().getMediaList(username);
        //User user = mediaListCollection.getUser();//new User("parimal", 200);
//        userDao.save();

//        System.out.println(userData.getSeriesRatingData());
        model.addAttribute("userData",mediaListCollection);
        return "home";
    }


}
