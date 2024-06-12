package ca.parimal.connectz.controller;
import ca.parimal.connectz.controller.dto.UserEntryCollection;
import ca.parimal.connectz.controller.dto.UserEntryCollectionFactory;
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
    @PostMapping("/users")
    public String addUser(@ModelAttribute("username") String username){
        UserEntryCollection userEntryCollection = new UserEntryCollectionFactory().build(username);
        if(userEntryCollection==null)return "error";
        userEntryService.save(userEntryCollection);
        return "redirect:users";
    }

}
