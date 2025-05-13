package com.pikupikusrv.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Map")
public class Map {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "hash", nullable = false, unique = true)
    private String hash;

    @Column(name = "hit_objects", nullable = false, unique = false)
    private int hitObjects;


    public int getHitObjects() {
        return hitObjects;
    }

    public void setHitObjects(int hitObjects) {
        this.hitObjects = hitObjects;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
