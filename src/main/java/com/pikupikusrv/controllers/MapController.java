package com.pikupikusrv.controllers;

import com.pikupikusrv.entities.Map;
import com.pikupikusrv.services.interfaces.IMapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur pour gérer les opérations liées aux cartes (maps).
 */
@RestController
@RequestMapping("/maps")
public class MapController {

    private final IMapService mapService;

    public MapController(IMapService mapService) {
        this.mapService = mapService;
    }

    /**
     * Endpoint pour créer une nouvelle carte.
     *
     * @param hash Le hash unique de la carte.
     * @param hitObjects Le nombre d'objets frappés dans la carte.
     * @return Une réponse HTTP contenant la carte créée.
     */
    @PostMapping
    public ResponseEntity<Map> createMap(@RequestParam String hash, @RequestParam int hitObjects) {
        return ResponseEntity.ok(mapService.createMap(hash, hitObjects));
    }


    /**
     * Endpoint pour récupérer une carte par son hash.
     *
     * @param hash Le hash unique de la carte.
     * @return Une réponse HTTP contenant la carte correspondante.
     */
    @GetMapping("/{hash}")
    public ResponseEntity<Map> getMap(@PathVariable String hash) {
        return ResponseEntity.ok(mapService.getMap(hash));
    }
}
