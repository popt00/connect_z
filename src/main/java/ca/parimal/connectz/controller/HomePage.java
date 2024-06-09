package ca.parimal.connectz.controller;

import ca.parimal.connectz.model.dao.UserRepository;
import ca.parimal.connectz.model.dao.entites.User;
import ca.parimal.connectz.model.graphql.UserEntryCollection;
import ca.parimal.connectz.model.graphql.UserEntryCollectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class HomePage {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/getUser/{username}")
    public String getUser(@PathVariable String username, Model model) throws IOException {
        //System.out.println(username);
        UserEntryCollection userEntryCollection = new UserEntryCollectionFactory().build(username);
        User user = userEntryCollection.getUser();//new User("parimal", 200);
        userRepository.save((User) user);

//        System.out.println(userData.getSeriesRatingData());
        model.addAttribute("userData", userEntryCollection);
        return "home";
    }


}
