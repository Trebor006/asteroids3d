package com.mibu.asteroids3d.screen;

import com.badlogic.gdx.Screen;
import com.mibu.asteroids3d.Asteroids3D;


public abstract class BaseScreen implements Screen {
    protected Asteroids3D asteroids3D;

    public BaseScreen(Asteroids3D asteroids3D) {
        this.asteroids3D = asteroids3D;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
