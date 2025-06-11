package com.pikupikusrv.repositories;

import com.pikupikusrv.entities.Score;
import com.pikupikusrv.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.pikupikusrv.entities.Map;
import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {
    Optional<Score> findByIdAndUser(int id, User user);
    List<Score> findAllByUser(User user);
    List<Score> findAllByMap(Map map);
    List<Score> findAllByUserAndMap(User user, Map map);
}
