package com.mibu.asteroids3d.objects;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.mibu.asteroids3d.assets.AsteroidAssets;
import com.mibu.asteroids3d.controller.AsteroidController;
import com.mibu.asteroids3d.util.AssetManagerUtil;
import com.mibu.asteroids3d.util.CameraUtil;
import com.mibu.asteroids3d.util.PosUtils;
import com.mibu.asteroids3d.util.RandomUtil;
import lombok.Getter;

public class Asteroid extends Actor {

    private static final float DELTA_DEGREE = 0.00001f;
    float scaleModel = 0.01f;
    private ModelInstance model;
    private Vector3 position;
    private Vector3 finalPosition;

    @Getter
    private Float speed;

    public Asteroid() {
        float x = RandomUtil.getRandomPosition();
        System.out.println("random x " + x);
//        position = new Vector3(x, RandomUtil.getRandomPosition(), -3.99f);
        position = PosUtils.generarPosicionInicial();
        finalPosition = PosUtils.generarPosicionFinal();
        logVector("Posicion asteroide ", position);

        model = new ModelInstance(AssetManagerUtil.getAssetManager().get(AsteroidAssets.getDefault(), Model.class));
        speed = RandomUtil.randomSpeed();
        Matrix4 transform = model.transform;
        transform.setTranslation(position);
        transform.scale(scaleModel, scaleModel, scaleModel);

    }

    public static Asteroid createNew() {
        return new Asteroid();
    }

    public Vector3 getPosition() {
        return position;
    }

    public ModelInstance getModelInstance() {
        return model;
    }

    @Override
    public void draw(ModelBatch batch) {
        batch.begin(CameraUtil.getCamera());
        batch.render(model);
        batch.end();
    }

    @Override
    public void act(float delta) {

    }

    public void logVector(String titulo, Vector3 direction) {
        if (direction != null) {
            System.out.println(titulo + " => x=" + direction.x + ", y=" + direction.y + ", z=" + direction.z);
        }
    }

    @Override
    public void dispose() {
    }

    public void move() {
        avanzarHaciaDestino();
    }

    public void avanzarHaciaDestino() {
        position = calculateNewPos(AsteroidController.getSpeed(this));
        translate();
    }

    public Vector3 calculateNewPos(float speed) {
        Vector3 direction = new Vector3(finalPosition)
                .sub(position).nor();

        return new Vector3(position).add(new Vector3(direction).scl(speed));
    }

    public void translate() {
        Matrix4 transform = model.transform;
        transform.setTranslation(position);
        model.transform.set(transform);
    }

    public void actualizarPorColision() {
        finalPosition = PosUtils.generarPosicionFinal();
    }
}
