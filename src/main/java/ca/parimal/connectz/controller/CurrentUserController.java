package ca.parimal.connectz.controller;

import ca.parimal.connectz.model.dao.entites.User;
import ca.parimal.connectz.services.UserCompService;
import ca.parimal.connectz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;

@Controller

public class CurrentUserController {
    @Autowired
    UserService userService;
    @Autowired
    UserCompService userCompService;


    @GetMapping("/currentuser")
    public String getCurrentUser(Principal principal, Authentication auth, Model model) throws IOException {
        String currentUser = principal.getName();
        System.out.println(auth.getAuthorities().toString());
        User user = userService.findByUsername(currentUser);
        if (user == null) {
            System.out.println("user is null in currentUserController: currentUser");
            return "error";
        }
        model.addAttribute("user", user);
        HashMap<User, Float> compatibilities = userCompService.compatibilities(user);
        model.addAttribute("map", compatibilities.entrySet());
        return "currentuser";
    }

}
