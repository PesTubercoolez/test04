package com.company.service.EntitiesService;

import com.company.model.Role;
import com.company.model.User.User;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserEntityService implements UserDetailsService {

    @Autowired
    private final UserRepository repository;

    public UserEntityService(UserRepository userRepository){
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

    public void updateUser(int age, String name, String password, Long id){
        repository.updateUser(age, name, password, id);
    }

    public User getUserByName(String name) {
        return repository.findUserByName(name);
    }

    public void deleteUserById(Long id){
        repository.deleteById(id);
    }

    public void truncateUser(){
        repository.deleteAll();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = repository.findUserByName(name);

        return new org.springframework.security.core.userdetails.User(user.getName(),
                                                                      user.getPassword(),
                                                                      rolesToAuthorityMap(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorityMap(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
