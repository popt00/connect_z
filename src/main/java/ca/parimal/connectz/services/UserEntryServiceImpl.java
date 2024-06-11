package ca.parimal.connectz.services;

import ca.parimal.connectz.controller.dto.UserEntryCollection;
import ca.parimal.connectz.controller.dto.entities.EntryGraphQl;
import ca.parimal.connectz.controller.dto.entities.MediaGraphQl;
import ca.parimal.connectz.controller.dto.entities.UserGraphql;
import ca.parimal.connectz.model.dao.EntryRepository;
import ca.parimal.connectz.model.dao.MediaRepository;
import ca.parimal.connectz.model.dao.UserRepository;
import ca.parimal.connectz.model.dao.entites.Entry;
import ca.parimal.connectz.model.dao.entites.Media;
import ca.parimal.connectz.model.dao.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserEntryServiceImpl implements IUserEntryService{

    UserRepository userRepository;
    EntryRepository entryRepository;
    MediaRepository mediaRepository;

    @Autowired
    public UserEntryServiceImpl(UserRepository userRepository, EntryRepository entryRepository, MediaRepository mediaRepository) {
        this.userRepository = userRepository;
        this.entryRepository = entryRepository;
        this.mediaRepository = mediaRepository;
    }

    @Override

    public void save(UserEntryCollection userEntryCollection) {
        User user = getUser(userEntryCollection.getUser());
        System.out.println(user);
        userRepository.save(user);
        ArrayList<EntryGraphQl> entryGraphQls = userEntryCollection.getEntries();
        for (EntryGraphQl entryGraphQl : entryGraphQls) {
            Media media = getMedia(entryGraphQl.getMedia());
            System.out.println(media);
            System.out.println(media.getTitle()+"::"+media.getTitle().length());
            Entry entry = getEntry(user,media, entryGraphQl);
            try{
                System.out.println(entry);
                entryRepository.save(entry);
            }
            catch (Exception e){
                System.out.println("\n\n\n\n\n       ERRRRRRRRRRRRRRRO: "+media);
                System.out.println(e.getMessage());
            }
        }
        System.out.println("entries saved");
    }

    private Media getMedia(MediaGraphQl mediaGraphQl) {
        Media media = new Media();
//        Long mediaId= mediaGraphQl.getAnilistMediaId();
        media.setMediaId(mediaGraphQl.getAnilistMediaId());
        media.setTitle(mediaGraphQl.getTitle());
        return media;
    }

    private User getUser(UserGraphql userGraphql) {
        User user = new User();
        user.setName(userGraphql.getName());
        user.setAnilistUserId(userGraphql.getAnilistUserId());
        return user;
    }
    private Entry getEntry(User user, Media media, EntryGraphQl entryGraphQl) {
        Entry entry = new Entry(user,media);
        entry.setScore(entryGraphQl.getScore());
        entry.setStatus(entryGraphQl.getStatus());
        return entry;
    }
}
