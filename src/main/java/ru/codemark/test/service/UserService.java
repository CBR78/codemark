package ru.codemark.test.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.codemark.test.model.User;
import ru.codemark.test.model.dto.UserDtoForGetAll;
import ru.codemark.test.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private EntityManager entityManager;

    @Autowired
    public UserService(UserRepository userRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public List<UserDtoForGetAll> getAll() {
        entityManager.clear();
        List<User> users = userRepository.findAll();

        List<UserDtoForGetAll> usersDto = new ArrayList<>();
        for (User user : users) {
            usersDto.add(new UserDtoForGetAll(user.getLogin(), user.getPassword(), user.getName()));
        }
        return usersDto;
    }

    public User getById(String login) {
        return userRepository.findById(login).get();
    }

    public boolean existsById(String login) {
        return userRepository.existsById(login);
    }
}
