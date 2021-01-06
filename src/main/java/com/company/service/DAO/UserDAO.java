package com.company.service.DAO;

import com.company.model.User.User;
import com.company.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDAO {

    private UserRepository repository;

    public UserDAO (UserRepository userRepository){
        this.repository = userRepository;
    }

    public void saveUser(User user){
        repository.save(user);
    }

    public User getUserById(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public void updateUser(int age, String name, String password, String role, Long id){
        repository.updateUser(age, name, password, role, id);
    }

    public void deleteUserById(Long id){
        repository.deleteById(id);
    }

    public void truncateUser(){
        repository.deleteAll();
    }
}
