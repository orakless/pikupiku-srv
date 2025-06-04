package com.pikupikusrv.services;

import com.pikupikusrv.dto.RegisterDTO;
import com.pikupikusrv.entities.User;

public interface IUserService {
    User createUser(RegisterDTO registerDTO);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
}
