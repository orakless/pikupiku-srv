package com.pikupikusrv.services.interfaces;

import com.pikupikusrv.entities.Map;
import com.pikupikusrv.entities.Score;
import com.pikupikusrv.entities.User;

import java.util.List;

public interface IScoreService {
    Score createScore(Score score);
    Score createScore(String username, String hash, long timestamp);
    Score getHighscoreForUser(User user, Map map);
    List<Score> getScoresByUser(User user);
    List<Score> getScoresByMap(Map map);
}