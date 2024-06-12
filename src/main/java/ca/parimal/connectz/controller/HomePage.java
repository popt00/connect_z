package ca.parimal.connectz.controller;

import ca.parimal.connectz.config.DaoMapper;
import ca.parimal.connectz.model.dao.EntryRepository;
import ca.parimal.connectz.model.dao.UserRepository;
import ca.parimal.connectz.model.dao.entites.Entry;
import ca.parimal.connectz.model.dao.entites.User;
import ca.parimal.connectz.controller.dto.UserEntryCollection;
import ca.parimal.connectz.controller.dto.UserEntryCollectionFactory;
import ca.parimal.connectz.services.IUserEntryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class HomePage {
    @Autowired
    IUserEntryService userEntryService;

    @Autowired
    private final DaoMapper daoMapper;

    public HomePage(DaoMapper daoMapper) {
        this.daoMapper = daoMapper;
    }

    @GetMapping("/getUser/{username}")
    public String getUser(@PathVariable String username, Model model) throws IOException {
        //System.out.println(username);
        UserEntryCollection userEntryCollection = new UserEntryCollectionFactory().build(username);
        if(userEntryCollection==null)return "errorpage";
        userEntryService.save(userEntryCollection);
//        System.out.println(userData.getSeriesRatingData());
        model.addAttribute("userData", userEntryCollection);
        return "home";
    }
    
//    private ArrayList<Entry> convertToDao


}
