package com.pikupikusrv.services.interfaces;

import com.pikupikusrv.dto.RegisterDTO;
import com.pikupikusrv.entities.User;

public interface IUserService {

    boolean userExists(String username); // Pour vérifier avant l’inscription
    boolean emailExists(String email);
    User getUserById(int id);
    User createUser(RegisterDTO registerDTO);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
}
