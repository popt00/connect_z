package ca.parimal.connectz.services;

import ca.parimal.connectz.controller.dto.graphqlhelper.UserEntryCollection;
import ca.parimal.connectz.model.dao.entites.User;


public interface UserEntryService {
    void save(UserEntryCollection userEntryCollection);
    User findByUsername(String username);
}
