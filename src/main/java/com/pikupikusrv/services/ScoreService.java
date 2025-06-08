package com.pikupikusrv.services;

import com.pikupikusrv.entities.Map;
import com.pikupikusrv.entities.Score;
import com.pikupikusrv.entities.User;
import com.pikupikusrv.repositories.MapRepository;
import com.pikupikusrv.repositories.ScoreRepository;
import com.pikupikusrv.repositories.UserRepository;
import com.pikupikusrv.repositories.paginated.ScorePaginatedRepository;
import com.pikupikusrv.services.interfaces.IScoreService;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;

import java.util.List;
import java.util.NoSuchElementException;

import java.util.Optional;

@Service
public class ScoreService implements IScoreService {
    private final ScoreRepository scoreRepository;
    private final ScorePaginatedRepository scorePaginatedRepository;
    private final UserRepository userRepository;
    private final MapRepository mapRepository;

    public ScoreService(ScoreRepository scoreRepository, ScorePaginatedRepository scorePaginatedRepository,UserRepository userRepository,
                        MapRepository mapRepository) {
        this.scoreRepository = scoreRepository;
        this.scorePaginatedRepository = scorePaginatedRepository;
        this.userRepository = userRepository;
        this.mapRepository = mapRepository;
    }

    @Override
    public Score createScore(Score score) {
        return scoreRepository.save(score);
    }

    //méthode pour créer un score à partir du nom d'utilisateur, du hash de la map et du timestamp
    public Score createScore(String username, String hash, long timestamp) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        Optional<Map> mapOpt = mapRepository.findByHash(hash);

        if (userOpt.isEmpty() || mapOpt.isEmpty()) {
            throw new NoSuchElementException("User or Map not found");
        }

        Score score = new Score();
        score.setUser(userOpt.get());
        score.setMap(mapOpt.get());
        score.setDate(new Timestamp(timestamp));

        return scoreRepository.save(score);
    }

    @Override
    public Score getHighscoreForUser(User user, Map map) throws NoSuchElementException {
        List<Score> scores = scoreRepository.findAllByUserAndMap(user, map);

        Optional<Score> score = scores.stream()
                .max((a, b) -> a.getDate().compareTo(b.getDate())); // Le plus récent

        if (score.isEmpty()) {
            throw new NoSuchElementException("Aucun score trouvé pour cet utilisateur et cette map.");
        }

        return score.get();
    }


    // Ajoute à la fin de la classe ScoreService
    @Override
    public List<Score> getScoresByUser(User user) {
        return scoreRepository.findAllByUser(user);
    }

    @Override
    public List<Score> getScoresByMap(Map map) {
        return scoreRepository.findAllByMap(map);
    }



}
