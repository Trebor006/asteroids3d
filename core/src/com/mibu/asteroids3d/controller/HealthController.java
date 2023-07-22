package com.mibu.asteroids3d.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mibu.asteroids3d.assets.AssetUtils;
import com.mibu.asteroids3d.assets.HealthAssets;
import com.mibu.asteroids3d.screen.GameScreen;

public class HealthController {
    public static Integer vida;

    public HealthController() {
        vida = 100;
    }

    public static void quitarVida() {
        vida -= 20;
        if (vida < 0) {
            vida = 0;
        }

        actualizarTexturas();
    }

    private static void actualizarTexturas() {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                changeTexture(vida);
            }
        });
    }

    private static void changeTexture(Integer vida) {
        GameScreen.textureHealth.dispose();
        GameScreen.textureHealth = new Texture(Gdx.files.internal(HealthAssets.getVidaAsset(vida)));
        GameScreen.regionHealth.setTexture(GameScreen.textureHealth);
    }

    public static void reiniciarJuego() {
        vida = 100;
        GameController.allowGame = true;
//        GameScreen.naveActor.reiniciarPosicion();
        reiniciarTexturas();
        SoundController.startGameSound.play();
        GameController.puntos = 0;
    }

    private static void reiniciarTexturas() {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                GameScreen.textureGameOver = new Texture(Gdx.files.internal(AssetUtils.empty));
                GameScreen.regionGameOver = new TextureRegion(GameScreen.textureGameOver);
                changeTexture(vida);
            }
        });
    }
}
