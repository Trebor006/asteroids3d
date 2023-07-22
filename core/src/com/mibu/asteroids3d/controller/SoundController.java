package com.mibu.asteroids3d.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.mibu.asteroids3d.assets.AssetUtils;

public class SoundController {
    public static Sound shootSound;
    public static Sound asteroidExplosionSound;
    public static Sound spaceshipCollisionSound;
    public static Sound gameOverSound;
    public static Sound startGameSound;

    public static void loadSounds() {
        shootSound = Gdx.audio.newSound(Gdx.files.internal(AssetUtils.disparo));
        asteroidExplosionSound = Gdx.audio.newSound(Gdx.files.internal(AssetUtils.asteroidExplosion));
        spaceshipCollisionSound = Gdx.audio.newSound(Gdx.files.internal(AssetUtils.spaceshipCollision));
        gameOverSound = Gdx.audio.newSound(Gdx.files.internal(AssetUtils.gameOverSound));
        startGameSound = Gdx.audio.newSound(Gdx.files.internal(AssetUtils.startGameSound));
    }
}
