package com.mibu.asteroids3d.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
}
