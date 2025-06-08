package com.pikupikusrv.repositories;

import com.pikupikusrv.entities.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MapRepository extends JpaRepository<Map, Integer> {
    Optional<Map> findByHash(String hash);
    boolean existsByHash(String hash);
}

