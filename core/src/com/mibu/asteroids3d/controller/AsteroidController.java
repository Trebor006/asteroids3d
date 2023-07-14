package com.mibu.asteroids3d.controller;

import com.badlogic.gdx.Gdx;
import com.mibu.asteroids3d.objects.Asteroid;
import com.mibu.asteroids3d.objects.Projectil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.concurrent.CopyOnWriteArrayList;

public class AsteroidController extends Thread {
    public static float SPEED = 0.00001F;

    private volatile CopyOnWriteArrayList<Asteroid> asteroids;

    private static float maxValueEje = 4f;


    public volatile int indexLista;

    public AsteroidController(CopyOnWriteArrayList<Asteroid> asteroids) {
        this.asteroids = asteroids;
        this.indexLista = 0;
    }

    public void run() {
        indexLista = 0;
        while (true) {
            if (asteroids.size() == 0){
                continue;
            }

            Asteroid asteroid = asteroids.get(indexLista);
            if(asteroid.getPosition().z + getSpeed() >= maxValueEje) {
                asteroids.remove(indexLista);
            } else {
                asteroid.translate(0f, 0f, getSpeed());
                indexLista++;
            }

            if (indexLista >= asteroids.size()) {
                indexLista = 0;
            }
        }
    }

    private float getSpeed() {
        return SPEED * Gdx.graphics.getDeltaTime();
    }
}
