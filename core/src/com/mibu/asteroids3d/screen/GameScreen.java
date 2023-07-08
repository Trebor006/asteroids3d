package com.mibu.asteroids3d.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mibu.asteroids3d.Asteroids3D;
import com.mibu.asteroids3d.controller.KeyController;
import com.mibu.asteroids3d.objects.Spaceship;
import com.mibu.asteroids3d.objects.Stage;

public class GameScreen extends BaseScreen {
    private Stage stage;
    private KeyController inputController;

    public GameScreen(Asteroids3D asteroids3D) {
        super(asteroids3D);
        this.stage = new Stage();
        this.inputController = new KeyController();

        Spaceship naveActor = new Spaceship();
        stage.addActor(naveActor);

        inputController.setSpaceship(naveActor);

        Gdx.input.setInputProcessor(inputController);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
