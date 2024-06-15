package ca.parimal.connectz.controller;
import ca.parimal.connectz.controller.dto.controllerdto.NewUserDto;
import ca.parimal.connectz.controller.dto.graphqlhelper.UserEntryCollection;
import ca.parimal.connectz.controller.dto.graphqlhelper.UserEntryCollectionFactory;
import ca.parimal.connectz.model.dao.entites.Role;
import ca.parimal.connectz.model.dao.entites.User;
import ca.parimal.connectz.services.Convertor;
import ca.parimal.connectz.services.UserCompService;
import ca.parimal.connectz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


@Controller()
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    Convertor convertor;
    @Autowired
    UserService userService;
    @Autowired
    UserCompService userCompService;

    @GetMapping("/users/{username}")
    public String getUser(@PathVariable String username, Model model) throws IOException {
        User user = userService.findByUsername(username);
        if (user == null) {
            return "error";
        }
        model.addAttribute("user", user);
        HashMap<User, Float> compatibilities = userCompService.compatibilities(user);
        model.addAttribute("map", compatibilities.entrySet());
        return "currentuser";
    }
    @GetMapping("/users")
    public String getAllUsers(Model model) throws IOException {
        List<User> users =userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }
    @GetMapping("/adduser")
    public String getAddUser(Model model) throws IOException {
        NewUserDto newUserDto = new NewUserDto();
        model.addAttribute("user", newUserDto);
        return "adduser";
    }

    /* TODO password bcrypt
     */
    @PostMapping("/adduser")
    public String addUser(@ModelAttribute("user") NewUserDto userDto){
        System.out.println(userDto.getUsername());
        UserEntryCollection userEntryCollection = new UserEntryCollectionFactory().build(userDto.getUsername());
        if(userEntryCollection==null)return "error";
        User currentUser = convertor.getUser(userEntryCollection);//userEntryService.save(userEntryCollection);
        currentUser.setPassword("{noop}"+userDto.getPassword());
        userService.saveUser(currentUser);
        Role role = new Role(currentUser,userDto.getRole());
        userService.saveRole(role);
        return "redirect:users";
    }
    /*TODO
    *  compatiblity: similar interests in animes
    *  opposite: opposite of you
    *  highdiff: high difference in rating of individual animes 1x(4 - 9)> 2x(5 - 9)*/
    @GetMapping("/computation/{username}")
    public String getCompatibility(@PathVariable String username, Model model) throws IOException {
        User user = userService.findByUsername(username);
        HashMap<User, Float> compatibilities = userCompService.compatibilities(user);
        model.addAttribute("map", compatibilities.entrySet());
        return "computation";
    }



}
