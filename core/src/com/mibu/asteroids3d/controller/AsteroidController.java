package com.mibu.asteroids3d.controller;

import com.badlogic.gdx.Gdx;
import com.mibu.asteroids3d.objects.Asteroid;
import com.mibu.asteroids3d.objects.Stage;
import com.mibu.asteroids3d.util.PosUtils;

public class AsteroidController extends Thread {
    private static float maxValueEje = (float) PosUtils.MAX / 2;
    public volatile int indexAsteroid;

    public AsteroidController() {
        this.indexAsteroid = 0;
    }

    public void run() {
        while (true) {
            if (GameController.isGameOver()) {
                continue;
            }

            if (Stage.asteroids.size() == 0) {
                continue;
            }

            Asteroid asteroid = Stage.asteroids.get(indexAsteroid);
            if (asteroid.getPosition().z + getSpeed(asteroid) >= maxValueEje) {
                asteroid = null;
                Stage.asteroids.remove(indexAsteroid);
            } else {
                asteroid.move();
                indexAsteroid++;
            }

            if (indexAsteroid >= Stage.asteroids.size()) {
                indexAsteroid = 0;
            }
        }
    }

    public static float getSpeed(Asteroid asteroid) {
        return asteroid.getSpeed() * Gdx.graphics.getDeltaTime();
    }
}
