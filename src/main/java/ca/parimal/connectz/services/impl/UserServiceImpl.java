package ca.parimal.connectz.services.impl;

import ca.parimal.connectz.model.dao.RolesRepository;
import ca.parimal.connectz.model.dao.UserRepository;
import ca.parimal.connectz.model.dao.entites.Role;
import ca.parimal.connectz.model.dao.entites.User;
import ca.parimal.connectz.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<String> findAllAuthorities(User user){
        return rolesRepository.findAuthorities(user);
    }
    @Override
    public User saveUser(User user) {

        System.out.println("(userService)saving user: "+user);
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }


    @Override
    public void saveRole(User user,String authority) {
        Role role = new Role(user,authority);
        rolesRepository.save(role);
        return;
    }


    @Override
    @Transactional
    public void deleteAllAuthorities(User user){
        rolesRepository.deleteAuthorities(user);
    }
}
