package com.mibu.asteroids3d.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.mibu.asteroids3d.assets.AssetUtils;
import com.mibu.asteroids3d.objects.Asteroid;
import com.mibu.asteroids3d.objects.Projectil;
import com.mibu.asteroids3d.objects.Spaceship;
import com.mibu.asteroids3d.objects.Stage;
import com.mibu.asteroids3d.screen.GameScreen;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameController extends Thread {

    private Sound sound;
    private Sound loseSound;

    public GameController() {
        Bullet.init();
        sound = Gdx.audio.newSound(Gdx.files.internal(AssetUtils.explosion));
        loseSound = Gdx.audio.newSound(Gdx.files.internal(AssetUtils.lose));
    }

    @Override
    public void run() {
        System.out.println("Validando colisiones");
        while (true) {
            checkCollisionsAndRemove(GameScreen.stage.getAsteroids(), GameScreen.naveActor.getProyectiles());
        }
    }

    private void checkCollisionsAndRemove(CopyOnWriteArrayList<Asteroid> asteroids,
                                          List<Projectil> proyectiles) {

        for (Asteroid asteroid : asteroids) {
            BoundingBox bounds = calculateInitialBoundingBox(asteroid.getPosition());

            for (Projectil otherObject : proyectiles) {
                BoundingBox otherBounds = new BoundingBox();
                otherObject.getModelInstance().calculateBoundingBox(otherBounds);

                if (bounds.intersects(otherBounds)) {
                    sound.play();
                    asteroids.remove(asteroid);
                    proyectiles.remove(otherObject);
                }
            }

//            BoundingBox boundNave = calculateInitialBoundingBox(GameScreen.naveActor.getPosition());
//
//            if (bounds.intersects(boundNave)) {
////                loseSound.play();
////                asteroids.remove(asteroid); // Elimina el objeto colisionado de la lista
//            }
        }
    }

    private BoundingBox calculateInitialBoundingBox(Vector3 center) {
        // Tamaño del bounding box
        Vector3 size = new Vector3(2f, 2f, 2f);

        // Calcular los límites del bounding box
        Vector3 halfExtents = size.cpy().scl(1f);
        Vector3 min = center.cpy().sub(halfExtents);
        Vector3 max = center.cpy().add(halfExtents);

        // Crear el bounding box
        return new BoundingBox(min, max);
    }
}
