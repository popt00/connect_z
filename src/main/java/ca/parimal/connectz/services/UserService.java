package ca.parimal.connectz.services;

import ca.parimal.connectz.model.dao.UserRepository;
import ca.parimal.connectz.model.dao.entites.Role;
import ca.parimal.connectz.model.dao.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    List<User> findAll();
    List<String> findAllAuthorities(User user);
    User saveUser(User user);
    void deleteUser(User user);
    User findByUsername(String username);
    void saveRole(User user, String authority);
    void deleteAllAuthorities(User user);

}
