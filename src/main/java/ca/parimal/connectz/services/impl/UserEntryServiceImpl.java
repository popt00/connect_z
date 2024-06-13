package ca.parimal.connectz.services.impl;

import ca.parimal.connectz.controller.dto.graphqlhelper.UserEntryCollection;
import ca.parimal.connectz.controller.dto.graphqlentities.EntryGraphQl;
import ca.parimal.connectz.controller.dto.graphqlentities.MediaGraphQl;
import ca.parimal.connectz.controller.dto.graphqlentities.UserGraphql;
import ca.parimal.connectz.model.dao.EntryRepository;
import ca.parimal.connectz.model.dao.MediaRepository;
import ca.parimal.connectz.model.dao.RolesRepository;
import ca.parimal.connectz.model.dao.UserRepository;
import ca.parimal.connectz.model.dao.entites.Entry;
import ca.parimal.connectz.model.dao.entites.Media;
import ca.parimal.connectz.model.dao.entites.Role;
import ca.parimal.connectz.model.dao.entites.User;
import ca.parimal.connectz.services.UserEntryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserEntryServiceImpl implements UserEntryService {

    UserRepository userRepository;
    EntryRepository entryRepository;
    RolesRepository rolesRepository;

    @Autowired
    public UserEntryServiceImpl(UserRepository userRepository, EntryRepository entryRepository, MediaRepository mediaRepository, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.entryRepository = entryRepository;
        this.rolesRepository = rolesRepository;
    }


    @Override
    @Transactional
    public void save(UserEntryCollection userEntryCollection) {
        if(userEntryCollection == null) return;
        User user = getUser(userEntryCollection.getUser(),userEntryCollection.getEntries());
        Long userId=Long.valueOf(user.getUserId());
        System.out.println("(userentryservice)saving user: "+user);
        if(!userRepository.existsById(userId)){
            Role role = new Role(user,"ROLE_USER");
            userRepository.save(user);
            rolesRepository.save(role);
        }
        else userRepository.save(user);
        entryRepository.saveAll(user.getEntries());
        System.out.println("entries saved");
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    private User getUser(UserGraphql userGraphql, ArrayList<EntryGraphQl> entryGraphQls) {
        User user = new User();
        user.setUsername(userGraphql.getUsername());
        user.setUserId(userGraphql.getUserId());
        user.setEntries(getEntries(user,entryGraphQls));
        return user;
    }

    private List<Entry> getEntries(User user, ArrayList<EntryGraphQl> entryGraphQls) {
        List<Entry> entries = new ArrayList<>();

        for (EntryGraphQl entryGraphQl : entryGraphQls) {
            Media media = getMedia(entryGraphQl.getMedia());
            Entry entry = getEntry(user,media, entryGraphQl);
//            System.out.println("entrykey class: "+entry.getId().getClass());
//            System.out.println("entrykey: "+entry.getId());
//            System.out.println("entrykey hashcode: "+entry.getId().hashCode());
//            System.out.println("entrykey media: "+entry.getMedia());
//            System.out.println("entrykey user: "+entry.getUser());
            entries.add(entry);
        }
        return entries;
    }
    private Media getMedia(MediaGraphQl mediaGraphQl) {
        Media media = new Media();
        media.setMediaId(mediaGraphQl.getMediaId());
        media.setTitle(mediaGraphQl.getTitle());
        return media;
    }
    private Entry getEntry(User user, Media media, EntryGraphQl entryGraphQl) {
        Entry entry = new Entry(user,media);
        entry.setScore(entryGraphQl.getScore());
        entry.setStatus(entryGraphQl.getStatus());
        return entry;
    }
}
