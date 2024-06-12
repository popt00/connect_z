package ca.parimal.connectz.services;

import ca.parimal.connectz.controller.dto.UserEntryCollection;
import ca.parimal.connectz.model.dao.entites.User;


public interface UserEntryService {
    void save(UserEntryCollection userEntryCollection);
    User findByUsername(String username);
}
