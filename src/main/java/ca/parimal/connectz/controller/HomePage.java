package ca.parimal.connectz.controller;

import ca.parimal.connectz.model.dao.UserDao;
import ca.parimal.connectz.model.entities.MediaListCollection;
import ca.parimal.connectz.model.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

//@Controller
public class HomePage {

    UserDao userDao;
    public HomePage(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/getUser/{username}")
    public String getUser(@PathVariable String username, Model model) throws IOException {
        //System.out.println(username);
        MediaListCollection mediaListCollection = new MediaListController().getMediaList(username);
        //User user = mediaListCollection.getUser();//new User("parimal", 200);
        userDao.save();

//        System.out.println(userData.getSeriesRatingData());
        model.addAttribute("userData",mediaListCollection);
        return "home";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) throws IOException {
        List<User> user = userDao.fethAll();
        model.addAttribute("user",user);
        return "alluser";
    }

}
