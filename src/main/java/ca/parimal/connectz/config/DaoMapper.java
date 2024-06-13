package ca.parimal.connectz.config;

import ca.parimal.connectz.controller.dto.graphqlentities.EntryGraphQl;
import ca.parimal.connectz.controller.dto.graphqlentities.MediaGraphQl;
import ca.parimal.connectz.controller.dto.graphqlentities.UserGraphql;
import ca.parimal.connectz.model.dao.entites.Entry;
import ca.parimal.connectz.model.dao.entites.Media;
import ca.parimal.connectz.model.dao.entites.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DaoMapper {
    private final ModelMapper modelMapper;
    public DaoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public User convert(UserGraphql userGraphql){
        User map = modelMapper.map(userGraphql, User.class);
        return map;
    }
    public Media convert(MediaGraphQl mediaGraphQl){
        Media map = modelMapper.map(mediaGraphQl, Media.class);
        return map;
    }
    public Entry convert(EntryGraphQl entryGraphQl){
        return modelMapper.map(entryGraphQl, Entry.class);
    }
    public ArrayList<Entry> convert(ArrayList<EntryGraphQl> entryGraphQl){
        ArrayList<Entry> entries = new ArrayList<>();
        for (EntryGraphQl entry : entryGraphQl) {
            entries.add(convert(entry));
        }
        return entries;
    }
}
