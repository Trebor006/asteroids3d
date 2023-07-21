package com.mibu.asteroids3d.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mibu.asteroids3d.assets.AssetUtils;
import com.mibu.asteroids3d.assets.HealthAssets;
import com.mibu.asteroids3d.controller.GameController;
import com.mibu.asteroids3d.controller.HealthController;
import com.mibu.asteroids3d.controller.KeyController;
import com.mibu.asteroids3d.objects.Spaceship;
import com.mibu.asteroids3d.objects.Stage;
import com.mibu.asteroids3d.util.CameraUtil;

public class GameScreen extends BaseScreen {
    public static Stage stage;
    public static Texture textureHealth;
    public static Texture textureGameOver;
    public static TextureRegion region;
    public static TextureRegion regionHealth;
    public static TextureRegion regionGameOver;
    public static Spaceship naveActor;
    public static HealthController healthController;
    private KeyController inputController;
    private SpriteBatch batch;
    private Texture texture;


    public GameScreen() {
        GameController gameController = new GameController();
        healthController = new HealthController();

        this.stage = new Stage();
        this.inputController = new KeyController();

        naveActor = new Spaceship();
        stage.addActor(naveActor);

        inputController.setSpaceship(naveActor);

        Gdx.input.setInputProcessor(inputController);
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal(AssetUtils.fondo));
        region = new TextureRegion(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        textureHealth = new Texture(Gdx.files.internal(HealthAssets.getVidaAsset(100)));
        regionHealth = new TextureRegion(textureHealth);

        textureGameOver = new Texture(Gdx.files.internal(AssetUtils.empty));
        regionGameOver = new TextureRegion(textureGameOver);
        gameController.start();
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
        batch.draw(regionHealth, 10, Gdx.graphics.getHeight() - regionHealth.getRegionHeight() - 10);
        batch.end();

        stage.act();
        stage.draw();

        batch.begin();
        batch.draw(regionGameOver, (float) ((Gdx.graphics.getWidth() - regionGameOver.getRegionWidth()) / 2),
                (float) ((Gdx.graphics.getHeight() - regionGameOver.getRegionHeight()) / 2));
        batch.end();
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
