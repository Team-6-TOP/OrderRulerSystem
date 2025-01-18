package services;

import models.UserModel;
import repositories.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel addUser(String username, String password) {
        return userRepository.createUser(username, password);
    }

    public boolean userExists(String username) {
        return userRepository.userExists(username);
    }

    public boolean authenticate(String username, String password) {
        return userRepository.authenticate(username, password);
    }
}