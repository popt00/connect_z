package ca.parimal.connectz.controller;

import ca.parimal.connectz.model.graphql.UserEntryCollection;
import ca.parimal.connectz.model.graphql.UserEntryCollectionFactory;
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
        UserEntryCollection userEntryCollection = new UserEntryCollectionFactory().build(username);
        //User user = mediaListCollection.getUser();//new User("parimal", 200);
//        userDao.save();

//        System.out.println(userData.getSeriesRatingData());
        model.addAttribute("userData", userEntryCollection);
        return "home";
    }


}
