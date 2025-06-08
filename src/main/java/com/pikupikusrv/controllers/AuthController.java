package com.pikupikusrv.controllers;

import com.pikupikusrv.dto.LoginDTO;
import com.pikupikusrv.dto.RegisterDTO;
import com.pikupikusrv.entities.User;
import com.pikupikusrv.services.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


/**
 * Contrôleur pour gérer les opérations liées à l'authentification,
 * telles que l'inscription et la connexion des utilisateurs.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IUserService userService;
    private final AuthenticationManager authenticationManager;


    /**
     * Constructeur du contrôleur AuthController.
     *
     * @param userService Service utilisateur pour gérer les opérations liées aux utilisateurs.
     * @param authenticationManager Gestionnaire d'authentification pour valider les connexions.
     */
    public AuthController(IUserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }


    /**
     * Endpoint pour inscrire un nouvel utilisateur.
     *
     * @param dto Objet contenant les informations nécessaires à l'inscription.
     * @return Une réponse HTTP contenant l'utilisateur créé.
     */
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterDTO dto) {
        User user = userService.createUser(dto);
        return ResponseEntity.ok(user);
    }


    /**
     * Endpoint pour connecter un utilisateur.
     *
     * @param dto Objet contenant les informations de connexion (nom d'utilisateur et mot de passe).
     * @return Une réponse HTTP contenant un message de succès ou d'échec de l'authentification.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO dto) {
        try {
            System.out.println("Tentative de login pour : " + dto.getUsername());

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            return ResponseEntity.ok("Connexion réussie !");
        } catch (Exception e) {
            e.printStackTrace(); // 👈 Affiche l'erreur en console
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Échec d'authentification");
        }
    }
    // TODO: Ajouter un endpoint pour déconnecter un utilisateur


}
