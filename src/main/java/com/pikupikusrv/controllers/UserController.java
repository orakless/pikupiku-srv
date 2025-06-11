package com.pikupikusrv.controllers;

import com.pikupikusrv.dto.UserDTO;
import com.pikupikusrv.entities.User;
import com.pikupikusrv.services.interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        try {
            User user = userService.getUserByUsername(username);
            UserDTO dto = new UserDTO(user.getId(), user.getUsername(), user.getEmail());
            return ResponseEntity.ok(dto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
