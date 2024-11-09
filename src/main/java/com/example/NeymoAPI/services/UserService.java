package com.example.NeymoAPI.services;

import com.example.NeymoAPI.dtos.UpdateUserDto;
import com.example.NeymoAPI.models.users.Role;
import com.example.NeymoAPI.models.users.User;
import com.example.NeymoAPI.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User with username " + username + " not found"));
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found"));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {

        user.setPassword(user.getPassword()); // password encoder
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public User createAdmin(User user) {

        user.setPassword(user.getPassword()); // password encoder
        user.setRole(Role.ADMIN);

        return userRepository.save(user);
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else { return false; }
    }

    public User updateUser(Long id, UpdateUserDto userDto) {

        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser != null) {
            if (userDto.getUsername() != null && !userDto.getUsername().equals(userRepository.findByUsername(existingUser.getUsername()))){
                existingUser.setUsername(userDto.getUsername());
            }
            if (userDto.getEmail() != null && !userDto.getEmail().equals(userRepository.findByEmail(existingUser.getEmail()))){
                existingUser.setEmail(userDto.getEmail());
            }
            if (userDto.getPassword() != null){
                existingUser.setUsername(userDto.getUsername());
            }
        } else {
            throw new EntityNotFoundException("User with username " + existingUser.getUsername() + " not found");
        }

        return userRepository.save(existingUser);
    }
}
