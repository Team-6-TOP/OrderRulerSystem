package repositories;

import models.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private final String fileName;

    public UserRepository(String fileName) {
        this.fileName = fileName;
    }

    public void save(UserModel user) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            String userData = user.getId() +
                    ";" + user.getUsername()
                    + ";" + user.getPassword()
                    + System.lineSeparator();
            writer.write(userData);
            logger.info("Пользователь сохранен: {}", user.getUsername());
        } catch (IOException e) {
            logger.error("Ошибка при сохранении пользователя: {}", e.getMessage());
            throw new RuntimeException("Ошибка при сохранении пользователя: " + e.getMessage());
        }
    }

    public List<UserModel> loadAll() {
        List<UserModel> users = new ArrayList<>();
        try {
            Path path = Paths.get(fileName);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String username = parts[1];
                    String password = parts[2];
                    users.add(new UserModel(id, username, password));
                }
            }
            logger.info("Загружено пользователей: {}", users.size());
        } catch (IOException e) {
            logger.error("Ошибка при загрузке пользователей: {}", e.getMessage());
            throw new RuntimeException("Ошибка при загрузке пользователей: " + e.getMessage());
        }
        return users;
    }

    private int generateNextId() {
        return loadAll().size() + 1;
    }

    public boolean userExists(String username) {
        return loadAll().stream().anyMatch(user -> user.getUsername().equals(username));
    }

    public boolean authenticate(String username, String password) {
        return loadAll().stream()
                .anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }

    public UserModel createUser(String username, String password) {
        int id = generateNextId();
        UserModel newUser = new UserModel(id, username, password);
        save(newUser);
        return newUser;
    }
}