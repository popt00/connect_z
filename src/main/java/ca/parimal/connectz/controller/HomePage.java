package ca.parimal.connectz.controller;
import ca.parimal.connectz.controller.dto.graphqlentities.UserGraphql;
import ca.parimal.connectz.controller.dto.graphqlhelper.UserEntryCollection;
import ca.parimal.connectz.controller.dto.graphqlhelper.UserEntryCollectionFactory;
import ca.parimal.connectz.model.dao.entites.User;
import ca.parimal.connectz.services.UserEntryService;
import ca.parimal.connectz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class HomePage {
    @Autowired
    UserEntryService userEntryService;
    @Autowired
    UserService userService;

    @GetMapping("/users/{username}")
    public String getUser(@PathVariable String username, Model model) throws IOException {
        User user = userService.findByUsername(username);
        if (user == null) {
            return "error";
        }
        model.addAttribute("user", user);
        return "home";
    }
    @GetMapping("/users")
    public String getAllUsers(Model model) throws IOException {
        List<User> users =userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }
    @GetMapping("/adduser")
    public String getAddUser(Model model) throws IOException {
        UserGraphql userGraphql = new UserGraphql();
        model.addAttribute("user", userGraphql);
        return "adduser";
    }

    @PostMapping("/adduser")
    public String addUser(@ModelAttribute("user") UserGraphql user){
        System.out.println(user.getUsername());
        UserEntryCollection userEntryCollection = new UserEntryCollectionFactory().build(user.getUsername());
        if(userEntryCollection==null)return "error";
        userEntryService.save(userEntryCollection);
        return "redirect:users";
    }

}
