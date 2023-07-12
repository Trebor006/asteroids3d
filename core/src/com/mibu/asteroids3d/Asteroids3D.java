package com.mibu.asteroids3d;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.mibu.asteroids3d.assets.SpaceshipAssets;
import com.mibu.asteroids3d.screen.GameScreen;
import com.mibu.asteroids3d.util.AssetManagerUtil;

public class Asteroids3D extends Game {

    public Asteroids3D() {
        AssetManagerUtil.getAssetManager()
                .load(SpaceshipAssets.getDefault(), Model.class);
    }

    @Override
    public void create() {
        AssetManagerUtil.getAssetManager().finishLoading();
        setScreen(new GameScreen());
        Bullet.init();
    }

    @Override
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        super.render();
    }

    @Override
    public void dispose() {
        AssetManagerUtil.getAssetManager().dispose();
    }
}
