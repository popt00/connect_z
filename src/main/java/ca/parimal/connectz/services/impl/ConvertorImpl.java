package ca.parimal.connectz.services.impl;

import ca.parimal.connectz.controller.dto.graphqlentities.EntryGraphQl;
import ca.parimal.connectz.controller.dto.graphqlentities.MediaGraphQl;
import ca.parimal.connectz.controller.dto.graphqlentities.UserGraphql;
import ca.parimal.connectz.controller.dto.graphqlhelper.UserEntryCollection;
import ca.parimal.connectz.model.dao.entites.Entry;
import ca.parimal.connectz.model.dao.entites.Media;
import ca.parimal.connectz.model.dao.entites.User;
import ca.parimal.connectz.services.Convertor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConvertorImpl implements Convertor {
    @Override
    public User getUser(UserEntryCollection userEntryCollection) {
        if(userEntryCollection == null) return null;
        User user = getUser(userEntryCollection.getUser(),userEntryCollection.getEntries());
        return user;
    }
    private User getUser(UserGraphql userGraphql, ArrayList<EntryGraphQl> entryGraphQls) {
        User user = new User();
        user.setUsername(userGraphql.getUsername());
        user.setUserId(userGraphql.getUserId());
        user.setEntries(getEntries(user,entryGraphQls));
        return user;
    }

    @Override
    public List<Entry> getEntries(User user, ArrayList<EntryGraphQl> entryGraphQls) {
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

    @Override
    public Media getMedia(MediaGraphQl mediaGraphQl) {
        Media media = new Media();
        media.setMediaId(mediaGraphQl.getMediaId());
        media.setTitle(mediaGraphQl.getTitle());
        return media;
    }

    public Entry getEntry(User user, Media media, EntryGraphQl entryGraphQl) {
        Entry entry = new Entry(user,media);
        entry.setScore(entryGraphQl.getScore());
        entry.setStatus(entryGraphQl.getStatus());
        return entry;
    }
}
