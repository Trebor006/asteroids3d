package com.mibu.asteroids3d.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.mibu.asteroids3d.controller.KeyController;
import com.mibu.asteroids3d.objects.Spaceship;
import com.mibu.asteroids3d.objects.Stage;
import com.mibu.asteroids3d.util.CameraUtil;

public class GameScreen extends BaseScreen {
    private Stage stage;
    private KeyController inputController;

//    CameraInputController camController;
//    ModelBatch modelBatch;
    Environment environment;

    public GameScreen() {
        this.stage = new Stage();
        this.inputController = new KeyController();

        Spaceship naveActor = new Spaceship();
        stage.addActor(naveActor);

        inputController.setSpaceship(naveActor);

        Gdx.input.setInputProcessor(inputController);

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        CameraUtil.getCamera().update();
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
