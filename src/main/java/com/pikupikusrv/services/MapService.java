package com.pikupikusrv.services;

import com.pikupikusrv.entities.Map;
import com.pikupikusrv.repositories.MapRepository;
import com.pikupikusrv.services.interfaces.IMapService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MapService implements IMapService {

    private final MapRepository mapRepository;

    public MapService(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    @Override
    public Map createMap(String hash, int hitObjects) {
        if (mapRepository.existsByHash(hash)) {
            throw new IllegalArgumentException("Map déjà existante");
        }
        Map map = new Map();
        map.setHash(hash);
        map.setHitObjects(hitObjects);
        return mapRepository.save(map);
    }

    @Override
    public Map getMap(String hash) {
        return mapRepository.findByHash(hash)
                .orElseThrow(() -> new NoSuchElementException("Map introuvable"));
    }

    @Override
    public boolean mapExists(String hash) {
        return mapRepository.existsByHash(hash);
    }

    @Override
    public List<Map> getAllMaps() {
        return List.of();
    }
}

