package com.mibu.asteroids3d.objects;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.mibu.asteroids3d.assets.AsteroidAssets;
import com.mibu.asteroids3d.util.AssetManagerUtil;
import com.mibu.asteroids3d.util.CameraUtil;
import com.mibu.asteroids3d.util.RandomUtil;

public class Asteroid extends Actor {

    private static final float DELTA_DEGREE = 0.00001f;
    private ModelInstance model;
    private boolean[] states;
    private Vector3 position;
    float scaleModel = 0.01f;

    public Asteroid() {
        float x = RandomUtil.getRandomPosition();
        System.out.println("random x " + x);
        position = new Vector3(x, RandomUtil.getRandomPosition(), -3.99f);
        logVector("Posicion asteroide ", position);

        model = new ModelInstance(AssetManagerUtil.getAssetManager().get(AsteroidAssets.getDefault(), Model.class));

        Matrix4 transform = model.transform;
        transform.setTranslation(position);
        transform.scale(scaleModel, scaleModel, scaleModel);

    }

    public static Asteroid createNew() {
        return new Asteroid();
    }

    public void translate(float x, float y, float z) {
        Matrix4 transform = model.transform;
        position.x += x;
        position.y += y;
        position.z += z;
        transform.setTranslation(position);
        model.transform.set(transform);
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
}
