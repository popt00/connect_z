package ca.parimal.connectz.services;

import ca.parimal.connectz.controller.dto.UserEntryCollection;
import ca.parimal.connectz.controller.dto.entities.EntryGraphQl;
import ca.parimal.connectz.controller.dto.entities.MediaGraphQl;
import ca.parimal.connectz.controller.dto.entities.UserGraphql;
import ca.parimal.connectz.model.dao.EntryRepository;
import ca.parimal.connectz.model.dao.MediaRepository;
import ca.parimal.connectz.model.dao.RolesRepository;
import ca.parimal.connectz.model.dao.UserRepository;
import ca.parimal.connectz.model.dao.entites.Entry;
import ca.parimal.connectz.model.dao.entites.Media;
import ca.parimal.connectz.model.dao.entites.Role;
import ca.parimal.connectz.model.dao.entites.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserEntryServiceImpl implements IUserEntryService{

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
        if(userRepository.existsById(userId)) {
            List<Role> userRoles= userRepository.findById(userId).get().getRoles();
            userRepository.delete(user);
            user.setRoles(userRoles);
        }
        Role role = new Role(user,"ROLE_USER");
        System.out.println(user);
        userRepository.save(user);
        rolesRepository.save(role);
        entryRepository.saveAll(user.getEntries());
        System.out.println("entries saved");
    }


    private User getUser(UserGraphql userGraphql, ArrayList<EntryGraphQl> entryGraphQls) {
        User user = new User();
        user.setName(userGraphql.getName());
        user.setUserId(userGraphql.getAnilistUserId());
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
        media.setMediaId(mediaGraphQl.getAnilistMediaId());
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
