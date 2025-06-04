package com.pikupikusrv.services;

import com.pikupikusrv.entities.Map;

public interface IMapService {
    Map createMap(String hash, int hitObjects);
    Map getMap(String hash);
}
