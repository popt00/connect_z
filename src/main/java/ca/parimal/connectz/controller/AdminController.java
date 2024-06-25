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
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    Convertor convertor;
    @Autowired
    UserService userService;
    @Autowired
    UserCompService userCompService;
    @Value("${userroles}")
    private List<String> static_roles;

    @GetMapping("/user")
    public String getUser(@RequestParam("username") String username, Model model) throws IOException {
        User user = userService.findByUsername(username);
        if (user == null) {
            System.out.println("user is null in admincontroller:user");
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
        return "/admin/users";
    }
    @GetMapping("/adduser")
    public String getAddUser(Model model) throws IOException {
        NewUserDto newUserDto = new NewUserDto(static_roles);
//        System.out.println(newUserDto.getAuthorities());
        model.addAttribute("user", newUserDto);
        return "/admin/adduser";
    }


    /* TODO password bcrypt
     */
    @PostMapping("/adduser")
    public String addUser(@ModelAttribute("user") NewUserDto userDto){

        UserEntryCollection userEntryCollection = new UserEntryCollectionFactory().build(userDto.getUsername());
        if(userEntryCollection==null){
            System.out.println("userrntrycollection is null in admincontroller:adduser");return "error";}
        User currentUser = convertor.getUser(userEntryCollection);//userEntryService.save(userEntryCollection);
        currentUser.setPassword("{noop}"+userDto.getPassword());
        User savedUser = userService.saveUser(currentUser);
        userService.deleteAllAuthorities(savedUser);
        for(String role: userDto.getAuthorities()){
            System.out.println(role);
            userService.saveRole(savedUser, role);
        }
        return "redirect:users";
    }

    /*
    * TODO add non-existing authoriteis by dropdown static_roles
    * */
    @GetMapping("/updateuser")
    public String getUpdateUser( @RequestParam("username") String username,Model model) throws IOException {
        User user = userService.findByUsername(username);
        NewUserDto newUserDto = new NewUserDto(static_roles);
        newUserDto.setAuthorities(userService.findAllAuthorities(user));
        newUserDto.setUsername(username);
        newUserDto.setPassword(user.getPassword());
        model.addAttribute("user", newUserDto);
        return "/admin/adduser";
    }

    @GetMapping("deleteuser")
    public String deleteUser(@RequestParam("username") String username, Model model) throws IOException {
        User user = userService.findByUsername(username);
        userService.deleteUser(user);
        return "redirect:users";
    }

    /*TODO
    *  compatiblity: similar interests in animes
    *  opposite: opposite of you
    *  highdiff: high difference in rating of individual animes 1x(4 - 9)> 2x(5 - 9)
    *  Some other parameter of checking like one anime favourites etc...*/
    @GetMapping("/computation/{username}")
    public String getCompatibility(@PathVariable String username, Model model) throws IOException {
        User user = userService.findByUsername(username);
        HashMap<User, Float> compatibilities = userCompService.compatibilities(user);
        model.addAttribute("map", compatibilities.entrySet());
        return "/admin/computation";
    }


}
