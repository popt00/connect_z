package ca.parimal.connectz.model.dao;

import ca.parimal.connectz.model.entities.User;

import java.util.List;

public interface UserDao {
    List<User> fethAll();
    void save(User user);
    void save();
}
