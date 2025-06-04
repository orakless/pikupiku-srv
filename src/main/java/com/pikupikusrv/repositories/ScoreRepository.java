package com.pikupikusrv.repositories;

import com.pikupikusrv.entities.Score;
import com.pikupikusrv.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {
    Optional<Score> findByIdAndUser(int id, User user);
}
