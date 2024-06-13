package ca.parimal.connectz.services;

import ca.parimal.connectz.model.dao.entites.User;

import java.util.HashMap;

public interface UserCompService {
    HashMap<User, Float> compatibilities(User user);
}
