package com.pikupikusrv.services;

import com.pikupikusrv.dto.RegisterDTO;
import com.pikupikusrv.entities.User;
import com.pikupikusrv.repositories.UserRepository;
import com.pikupikusrv.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    //private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private void validatePassword(String password) {
        if (password.length() < 8 ||
                !password.matches(".*[A-Z].*") ||       // au moins une majuscule
                !password.matches(".*[a-z].*") ||       // au moins une minuscule
                !password.matches(".*\\d.*") ||         // au moins un chiffre
                !password.matches(".*[!@#$%^&*()].*"))  // au moins un caractère spécial
        {
            throw new IllegalArgumentException("Le mdp doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial. ex : Azerty123!");
        }
    }


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    public User createUser(RegisterDTO dto) {
        validatePassword(dto.getPassword());
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // important
        return userRepository.save(user);
    }


    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("Utilisateur non trouvé"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Email non trouvé"));
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ID non trouvé"));
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}

