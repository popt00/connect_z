package ca.parimal.connectz.controller;

import ca.parimal.connectz.controller.dto.controllerdto.NewUserDto;
import ca.parimal.connectz.controller.dto.graphqlhelper.UserEntryCollection;
import ca.parimal.connectz.controller.dto.graphqlhelper.UserEntryCollectionFactory;
import ca.parimal.connectz.model.dao.entites.User;
import ca.parimal.connectz.services.Convertor;
import ca.parimal.connectz.services.UserCompService;
import ca.parimal.connectz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    Convertor convertor;
    @Autowired
    UserService userService;
    @Autowired
    UserCompService userCompService;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        model.addAttribute("user", principal.getName());
        return "index";
    }
    @GetMapping("/login")
    public String getLogin(Model model) throws IOException {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignup(Model model) throws IOException {
        NewUserDto newUserDto = new NewUserDto();
        model.addAttribute("user", newUserDto);
        return "signup";
    }

    /*
    * TODO user already exist no signup
    *  bcrypt
    * */
    @PostMapping("/signup")
    public String addUser(@ModelAttribute("user") NewUserDto userDto){
        if(userService.findByUsername(userDto.getUsername())!=null){
            System.out.println("user already exists");
            return "redirect:/login";
        }
        UserEntryCollection userEntryCollection = new UserEntryCollectionFactory().build(userDto.getUsername());
        if(userEntryCollection==null){
            System.out.println("userrntrycollection is null in admincontroller:adduser");return "error";}
        User currentUser = convertor.getUser(userEntryCollection);
        currentUser.setPassword("{noop}"+userDto.getPassword());

        User savedUser = userService.saveUser(currentUser);
        userService.deleteAllAuthorities(savedUser);
        userService.saveRole(savedUser, "ROLE_USER");

        return "redirect:";
    }
}
