package com.pikupikusrv.services;

import com.pikupikusrv.entities.Map;
import com.pikupikusrv.entities.Score;
import com.pikupikusrv.entities.User;

public interface IScoreService {
    Score createScore(Score score);
    Score getHighscoreForUser(User user, Map map);
}