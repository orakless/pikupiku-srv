package com.pikupikusrv.services;

import com.pikupikusrv.entities.Map;
import com.pikupikusrv.entities.Score;
import com.pikupikusrv.entities.User;
import com.pikupikusrv.repositories.ScoreRepository;
import com.pikupikusrv.repositories.paginated.ScorePaginatedRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

public class ScoreService implements IScoreService {
    private final ScoreRepository scoreRepository;
    private final ScorePaginatedRepository scorePaginatedRepository;

    public ScoreService(ScoreRepository scoreRepository, ScorePaginatedRepository scorePaginatedRepository) {
        this.scoreRepository = scoreRepository;
        this.scorePaginatedRepository = scorePaginatedRepository;
    }

    @Override
    public Score createScore(Score score) {
        return null;
    }

    @Override
    public Score getHighscoreForUser(User user, Map map) throws NoSuchElementException {
        Optional<Score> score = scoreRepository.findByIdAndUser(map.getId(), user);

        if (score.isEmpty()) {
            throw new NoSuchElementException();
        }
        return score.get();
    }
}
