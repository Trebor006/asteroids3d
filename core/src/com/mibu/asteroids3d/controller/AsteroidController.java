package com.mibu.asteroids3d.controller;

import com.badlogic.gdx.Gdx;
import com.mibu.asteroids3d.objects.Asteroid;

import java.util.concurrent.CopyOnWriteArrayList;

public class AsteroidController extends Thread {
    private static float maxValueEje = 4f;
    public volatile int indexAsteroid;
    private volatile CopyOnWriteArrayList<Asteroid> asteroids;

    public AsteroidController(CopyOnWriteArrayList<Asteroid> asteroids) {
        this.asteroids = asteroids;
        this.indexAsteroid = 0;
    }

    public void run() {
        while (true) {
            if (GameController.isGameOver()) {
                continue;
            }

            if (asteroids.size() == 0) {
                continue;
            }

            Asteroid asteroid = asteroids.get(indexAsteroid);
            if (asteroid.getPosition().z + getSpeed(asteroid) >= maxValueEje) {
                asteroid = null;
                asteroids.remove(indexAsteroid);
            } else {
                asteroid.translate(0f, 0f, getSpeed(asteroid));
                indexAsteroid++;
            }

            if (indexAsteroid >= asteroids.size()) {
                indexAsteroid = 0;
            }
        }
    }

    private float getSpeed(Asteroid asteroid) {
        return asteroid.getSpeed() * Gdx.graphics.getDeltaTime();
    }
}
