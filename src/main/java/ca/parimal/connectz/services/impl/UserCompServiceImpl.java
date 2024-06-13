package ca.parimal.connectz.services.impl;

import ca.parimal.connectz.model.dao.UserRepository;
import ca.parimal.connectz.model.dao.entites.Entry;
import ca.parimal.connectz.model.dao.entites.User;
import ca.parimal.connectz.services.UserCompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class UserCompServiceImpl implements UserCompService {
    @Autowired
    UserRepository userRepository;
    @Override
    public HashMap<User, Float> compatibilities(User currUser) {
        List<User> users = userRepository.findAll();
        HashMap<User, Float> compatibilities = new HashMap<>();
        for (User user : users) {
            Float compatibility = findComp(user,currUser);
            compatibilities.put(user,compatibility);
        }
        return compatibilities;
    }

    private Float findComp(User user, User currUser) {
        List<Entry> userEntries = user.getEntries();
        List<Entry> currEntries = currUser.getEntries();
//        System.out.println("User1: "+checkSorted(userEntries)+userEntries);
//        System.out.println("User2: "+checkSorted(currEntries)+currUser.getEntries()+"\n");
        int i=0,j=0,userLength = userEntries.size(),currLength = currEntries.size();
        int compatibility = 0,totalCompatibility = 0;
        while(i<userLength &&j<currLength) {
//            System.out.println(i+","+j);
            Entry entry = userEntries.get(i);
            Entry entry2 = currEntries.get(j);
            if(entry.getMedia().getMediaId()==entry2.getMedia().getMediaId()) {
//                System.out.println(entry.getScore()+","+entry2.getScore());
                compatibility+= matchScore(entry.getScore(),entry2.getScore());
                totalCompatibility+=10;
                i++;j++;
            } else if(entry.getMedia().getMediaId()<entry2.getMedia().getMediaId()) {
                i++;
            }
            else {
                j++;
            }
        }
        System.out.println(compatibility+","+totalCompatibility);
        return compatibility/(float)totalCompatibility;
    }

    private int matchScore(Integer score, Integer score1) {
        int diff = Math.abs(score - score1);
        if(diff==0){
            return 10;
        }
        else if(diff<=10){
            return 9;
        }
        else if(diff<=20){
            return 7;
        }
        else if(diff<=30){
            return 5;
        }
        else if(diff<=40){
            return 3;
        }
        else if(diff<=50){
            return 1;
        }
        return 0;
    }


    private String checkSorted(List<Entry> userEntries) {
        Integer min = -1;
        for (Entry entry : userEntries) {
            if(entry.getMedia().getMediaId()<min)return "NOT SORTED";
            min=entry.getMedia().getMediaId();
        }
        return "Sorted: ";
    }
}
