package com.pikupikusrv.controllers;

import com.pikupikusrv.entities.Map;
import com.pikupikusrv.entities.Score;
import com.pikupikusrv.entities.User;
import com.pikupikusrv.services.interfaces.IMapService;
import com.pikupikusrv.services.interfaces.IScoreService;
import com.pikupikusrv.services.interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Contrôleur pour gérer les opérations liées aux scores.
 */
@RestController
@RequestMapping("/scores")
public class ScoreController {

    private final IScoreService scoreService;
    private final IUserService userService;
    private final IMapService mapService;

    /**
     * Constructeur du contrôleur ScoreController.
     *
     * @param scoreService Service pour gérer les opérations liées aux scores.
     * @param userService Service pour gérer les opérations liées aux utilisateurs.
     * @param mapService Service pour gérer les opérations liées aux cartes.
     */
    public ScoreController(IScoreService scoreService, IUserService userService, IMapService mapService) {
        this.scoreService = scoreService;
        this.userService = userService;
        this.mapService = mapService;
    }

    /**
     * Endpoint pour créer un nouveau score.
     *
     * @param username Le nom d'utilisateur associé au score.
     * @param hash Le hash unique de la carte associée au score.
     * @param timestamp Le timestamp du score.
     * @return Une réponse HTTP contenant le score créé ou une réponse 404 si l'utilisateur ou la carte n'existe pas.
     */
    @PostMapping
    public ResponseEntity<Score> createScore(
            @RequestParam String username,
            @RequestParam String hash,
            @RequestParam long timestamp
    ) {
        try {
            Score score = scoreService.createScore(username, hash, timestamp);
            return ResponseEntity.ok(score);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint pour récupérer les scores d'un utilisateur.
     *
     * @param username Le nom d'utilisateur.
     * @return Une réponse HTTP contenant la liste des scores de l'utilisateur ou une réponse 404 si l'utilisateur n'existe pas.
     */
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Score>> getScoresByUser(@PathVariable String username) {
        try {
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok(scoreService.getScoresByUser(user));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint pour récupérer les scores d'une carte.
     *
     * @param hash Le hash unique de la carte.
     * @return Une réponse HTTP contenant la liste des scores de la carte ou une réponse 404 si la carte n'existe pas.
     */
    @GetMapping("/map/{hash}")
    public ResponseEntity<List<Score>> getScoresByMap(@PathVariable String hash) {
        try {
            Map map = mapService.getMap(hash);
            return ResponseEntity.ok(scoreService.getScoresByMap(map));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint pour récupérer le meilleur score d'un utilisateur sur une carte.
     *
     * @param username Le nom d'utilisateur.
     * @param hash Le hash unique de la carte.
     * @return Une réponse HTTP contenant le meilleur score ou une réponse 404 si l'utilisateur ou la carte n'existe pas.
     */
    @GetMapping("/highscore")
    public ResponseEntity<Score> getHighscore(@RequestParam String username, @RequestParam String hash) {
        try {
            User user = userService.getUserByUsername(username);
            Map map = mapService.getMap(hash);
            Score score = scoreService.getHighscoreForUser(user, map);
            return ResponseEntity.ok(score);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}