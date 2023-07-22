package com.mibu.asteroids3d.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.mibu.asteroids3d.assets.AssetUtils;
import com.mibu.asteroids3d.objects.Asteroid;
import com.mibu.asteroids3d.objects.Projectil;
import com.mibu.asteroids3d.screen.GameScreen;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameController extends Thread {

    public static boolean allowGame;

    public GameController() {
        Bullet.init();
        allowGame = true;
    }

    public static boolean isGameOver() {
        return !allowGame;
    }

    @Override
    public void run() {
        System.out.println("Validando colisiones");
        while (true) {
            if (GameController.isGameOver()) {
                continue;
            }

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
                    SoundController.asteroidExplosionSound.play();
                    asteroids.remove(asteroid);
                    proyectiles.remove(otherObject);
                }
            }

            BoundingBox boundNave = calculateInitialBoundingBox(GameScreen.naveActor.getPosition());

            if (bounds.intersects(boundNave)) {
                SoundController.spaceshipCollisionSound.play();
                asteroids.remove(asteroid); // Elimina el objeto colisionado de la lista

                HealthController.quitarVida();

                if (HealthController.vida == 0) {
                    setGameOver();
                }
            }
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

    public void setGameOver() {
        allowGame = false;
        SoundController.gameOverSound.play();
//        SoundController.gameOverSound.play();

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                setTextureGameOver();
            }
        });
        Arrays.fill(GameScreen.naveActor.states, false);
    }

    private void setTextureGameOver() {
        GameScreen.textureGameOver.dispose();
        GameScreen.textureGameOver = new Texture(Gdx.files.internal(AssetUtils.gameOverBack));
        GameScreen.regionGameOver.setTexture(GameScreen.textureGameOver);
    }
}
