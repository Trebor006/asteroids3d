package com.mibu.asteroids3d.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.mibu.asteroids3d.controller.AsteroidController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Stage {
    private List<Actor> actors;
    public static CopyOnWriteArrayList<Asteroid> asteroids;
    private List<ModelInstance> asteroidsModelInstance;
    private ModelBatch modelBatch;
    private AsteroidController asteroidController;

    public Stage() {
        this.actors = new ArrayList<>();
        this.modelBatch = new ModelBatch();
        this.asteroids = new CopyOnWriteArrayList<>();

        asteroidController = new AsteroidController();
        asteroidController.start();
        crearAsteroides();
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }

    public void removeActor(Actor actor) {
        actors.remove(actor);
    }

    public void crearAsteroides() {
        Runnable myThread = new Runnable() {
            @Override
            public void run() {
                while (true) {
//                    if (asteroids.size() > 5) {
//                        continue;
//                    }
                    asteroids.add(Asteroid.createNew());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        Thread run = new Thread(myThread);

        run.start();
    }

    public void act() {
        for (Actor actor : actors) {
            actor.act(Gdx.graphics.getDeltaTime());
        }
    }

    public void draw() {
        for (Actor actor : actors) {
            actor.draw(modelBatch);
        }
        for (Asteroid asteroid : asteroids) {
            asteroid.draw(modelBatch);
        }
    }

    public CopyOnWriteArrayList<Asteroid> getAsteroids() {
        return this.asteroids;
    }

    public void dispose() {
        modelBatch.dispose();
        for (Actor actor : actors) {
            actor.dispose();
        }
        for (Asteroid asteroid : asteroids) {
            asteroid.dispose();
        }
    }
}
