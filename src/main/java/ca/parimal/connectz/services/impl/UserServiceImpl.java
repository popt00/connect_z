package ca.parimal.connectz.services.impl;

import ca.parimal.connectz.model.dao.RolesRepository;
import ca.parimal.connectz.model.dao.UserRepository;
import ca.parimal.connectz.model.dao.entites.Role;
import ca.parimal.connectz.model.dao.entites.User;
import ca.parimal.connectz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        User savedUser;
        Long userId=Long.valueOf(user.getUserId());
        if(!userRepository.existsById(userId)){
            Role role = new Role(user,"ROLE_USER");
            savedUser = userRepository.save(user);
            rolesRepository.save(role);
        }
        else savedUser = userRepository.save(user);
//        entryRepository.saveAll(user.getEntries());
        return savedUser;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveRole(Role role) {
        List<Role> roles = rolesRepository.findAllByUserId(role.getUserId());

        for(Role roleTemp: roles){
            rolesRepository.delete(roleTemp);
        }
        rolesRepository.save(role);
    }
}
