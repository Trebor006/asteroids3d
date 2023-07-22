package com.mibu.asteroids3d.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    public static int puntos = 0;

    public GameController() {
        Bullet.init();
        allowGame = true;
    }

    public static boolean isGameOver() {
        return !allowGame;
    }

    private static void winGameTexturas() {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                GameScreen.textureGameOver = new Texture(Gdx.files.internal(AssetUtils.youwin));
                GameScreen.regionGameOver = new TextureRegion(GameScreen.textureGameOver);
            }
        });
        Arrays.fill(GameScreen.naveActor.states, false);
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


    private void checkCollisionsBetweenAsteroid(CopyOnWriteArrayList<Asteroid> asteroids) {
        for (Asteroid asteroid1 : asteroids) {
            BoundingBox boundsAsteroid1 = calculateInitialBoundingBox(asteroid1.getPosition(), asteroid1.getSize());
            for (Asteroid asteroid2 : asteroids) {
                if (!asteroid2.equals(asteroid1)) {
                    BoundingBox boundsAsteroide2 = calculateInitialBoundingBox(asteroid2.getPosition(), asteroid2.getSize());
                    if (boundsAsteroid1.intersects(boundsAsteroide2)) {
                        asteroid1.actualizarPorColision();
                        asteroid2.actualizarPorColision();
                    }
                }
            }
        }

    }

    private void checkCollisionsAndRemove(CopyOnWriteArrayList<Asteroid> asteroids,
                                          List<Projectil> proyectiles) {

        for (Asteroid asteroid : asteroids) {
            BoundingBox boundsAsteroid = calculateInitialBoundingBox(asteroid.getPosition(), asteroid.getSize());

            for (Projectil projectil : proyectiles) {
                BoundingBox boundsProjectil = new BoundingBox();
                projectil.getModelInstance().calculateBoundingBox(boundsProjectil);

                if (boundsAsteroid.intersects(boundsProjectil)) {
                    SoundController.asteroidExplosionSound.play();
                    actualizarPuntos();
                    asteroids.remove(asteroid);
                    proyectiles.remove(projectil);
                }
            }

            BoundingBox boundNave = calculateInitialBoundingBox(GameScreen.naveActor.getPosition(), GameScreen.naveActor.getSize());

            if (boundsAsteroid.intersects(boundNave)) {
                SoundController.spaceshipCollisionSound.play();
                asteroids.remove(asteroid); // Elimina el objeto colisionado de la lista

                HealthController.quitarVida();

                if (HealthController.vida == 0) {
                    setGameOver();
                }
            }
        }

        checkCollisionsBetweenAsteroid(asteroids);
    }

    private void actualizarPuntos() {
        puntos += 10;

        if (puntos >= 100) {
            allowGame = false;
            winGameTexturas();
        }
    }

    private BoundingBox calculateInitialBoundingBox(Vector3 center, Vector3 size) {
        // Tamaño del bounding box
        //Vector3 size = new Vector3(2f, 2f, 2f);

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
        Arrays.fill(GameScreen.naveActor.states, false);

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                setTextureGameOver();
            }
        });
    }

    private void setTextureGameOver() {
        GameScreen.textureGameOver.dispose();
        GameScreen.textureGameOver = new Texture(Gdx.files.internal(AssetUtils.gameOverBack));
        GameScreen.regionGameOver.setTexture(GameScreen.textureGameOver);
    }
}
