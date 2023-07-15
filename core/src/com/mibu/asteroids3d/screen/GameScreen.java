package com.mibu.asteroids3d.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mibu.asteroids3d.controller.KeyController;
import com.mibu.asteroids3d.objects.Spaceship;
import com.mibu.asteroids3d.objects.Stage;
import com.mibu.asteroids3d.assets.AssetUtils;
import com.mibu.asteroids3d.util.CameraUtil;

public class GameScreen extends BaseScreen {
    private Stage stage;
    private KeyController inputController;
    private SpriteBatch batch;
    private Texture texture;
    private TextureRegion region;

    public GameScreen() {
        this.stage = new Stage();
        this.inputController = new KeyController();

        Spaceship naveActor = new Spaceship();
        stage.addActor(naveActor);

        inputController.setSpaceship(naveActor);

        Gdx.input.setInputProcessor(inputController);
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal(AssetUtils.fondo));
        region = new TextureRegion(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        CameraUtil.getCamera().update();
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        batch.begin();
        batch.draw(region, 0, 0);
        batch.end();

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
