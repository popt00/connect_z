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
    public void saveRole(User user,String role) {
//        Role existingRole = rolesRepository.findByUserAndRole(user, role);
//        if(existingRole != null) {
//            System.out.println("role already exists");
//            return;
//        }
        Role role1 = new Role(user,role);
        try{
            rolesRepository.save(role1);
        }
        catch(DuplicateKeyException e){
            System.out.println("{userservide:saverole}already exist");
            return;
        }
    }
}
