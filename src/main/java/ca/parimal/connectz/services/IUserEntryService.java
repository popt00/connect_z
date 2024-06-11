package ca.parimal.connectz.services;

import ca.parimal.connectz.controller.dto.UserEntryCollection;
import org.springframework.stereotype.Service;


public interface IUserEntryService {
    void save(UserEntryCollection userEntryCollection);
}
