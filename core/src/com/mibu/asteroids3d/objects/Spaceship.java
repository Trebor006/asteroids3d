package com.mibu.asteroids3d.objects;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.mibu.asteroids3d.assets.SpaceshipAssets;
import com.mibu.asteroids3d.controller.SpaceshipController;
import com.mibu.asteroids3d.util.AssetManagerUtil;
import com.mibu.asteroids3d.util.CameraUtil;
import com.mibu.asteroids3d.util.Movements;

import java.util.ArrayList;
import java.util.List;

public class Spaceship extends Actor {
    private static final float DELTA_DEGREE = 0.00001f;
    private ModelInstance model;
    private boolean[] states;
    private SpaceshipController stateNaveController;
    private SpaceshipBullet spaceshipBullet;
    private Vector3 position;

    public Spaceship() {
        model = new ModelInstance(AssetManagerUtil.getAssetManager().get(SpaceshipAssets.getDefault(), Model.class));
        states = new boolean[Movements.values().length];

        position = new Vector3(0, 0, 0);

        stateNaveController = new SpaceshipController(this, states);
        stateNaveController.start();

        spaceshipBullet = new SpaceshipBullet();

        Matrix4 transform = model.transform;
        transform.getTranslation(position);
        transform.setToRotation(0, 1, 0, 180);
        transform.scale(0.4f, 0.4f, 0.4f);

        position.x = 0f;
        position.y = -4f;
        position.z = -1f;
        transform.setTranslation(position);
        model.transform.set(transform);
    }

    @Override
    public void act(float delta) {
    }

    @Override
    public void draw(ModelBatch batch) {
        batch.begin(CameraUtil.getCamera());
        batch.render(model);
        batch.end();

        spaceshipBullet.draw(batch);
    }

    public void changeValue(Movements motion) {
        states[motion.ordinal()] = !states[motion.ordinal()];
        System.out.println(motion.name() + " :" + states[motion.ordinal()]);
    }

    public void translate(float x, float y, float z) {
        Matrix4 transform = model.transform;
        position.x += x;
        position.y += y;
        position.z += z;
        transform.setTranslation(position);
        model.transform.set(transform);
    }

    public void rotate(float z) {
        Matrix4 transform = model.transform;
        transform.rotate(0, 0, z, DELTA_DEGREE);
        model.transform.set(transform);
    }

    public void changeSpaceship(int pos) {
//        model.get
//        assetManager.get
//        model = new ModelInstance(assetManager.get(SpaceshipAssets.switchSpaceshipAsset(pos), Model.class));
    }

    public void shoot() {
        spaceshipBullet.createNew();
    }
}
