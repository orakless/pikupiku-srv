
package com.pikupikusrv.services.interfaces;

import com.pikupikusrv.entities.Map;

import java.util.List;

public interface IMapService {
    boolean mapExists(String hash); // utile avant ajout de score
    List<Map> getAllMaps();         // pr afficher tt les maps

    Map createMap(String hash, int hitObjects);
    Map getMap(String hash);
}
