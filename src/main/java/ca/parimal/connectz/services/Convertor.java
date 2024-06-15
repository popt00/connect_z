package ca.parimal.connectz.services;

import ca.parimal.connectz.controller.dto.graphqlentities.EntryGraphQl;
import ca.parimal.connectz.controller.dto.graphqlentities.MediaGraphQl;
import ca.parimal.connectz.controller.dto.graphqlentities.UserGraphql;
import ca.parimal.connectz.controller.dto.graphqlhelper.UserEntryCollection;
import ca.parimal.connectz.model.dao.entites.Entry;
import ca.parimal.connectz.model.dao.entites.Media;
import ca.parimal.connectz.model.dao.entites.User;

import java.util.ArrayList;
import java.util.List;

public interface Convertor {
    public User getUser(UserEntryCollection userEntryCollection);
    public Media getMedia(MediaGraphQl mediaGraphQl);
    public List<Entry> getEntries(User user, ArrayList<EntryGraphQl> entryGraphQls);
}
