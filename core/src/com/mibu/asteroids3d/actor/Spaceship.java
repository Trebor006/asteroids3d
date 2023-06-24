package com.mibu.asteroids3d.actor;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.mibu.asteroids3d.assets.SpaceshipAssets;
import com.mibu.asteroids3d.controller.SpaceshipStateController;
import com.mibu.asteroids3d.util.Movements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Spaceship extends Actor {
    private static final float DELTA_DEGREE = 0.00001f;
    private ModelInstance model;
    private Map<Movements, Boolean> state;
    private List<Projectil> projectiles;
    private SpaceshipStateController stateNaveController;
    private Vector3 position;

    public Spaceship(AssetManager assetManager) {
        super(assetManager);
        model = new ModelInstance(assetManager.get(SpaceshipAssets.getDefault(), Model.class));
        state = new ConcurrentHashMap<>();

        projectiles = new ArrayList<>();

        position = new Vector3(0, 0, 0);

        stateNaveController = new SpaceshipStateController(this, state);
        stateNaveController.start();

        Matrix4 transform = model.transform;
        transform.getTranslation(position);
        transform.setToRotation(0, 1, 0, 180);
        transform.scale(0.5f, 0.5f, 0.5f);

        position.x = 0f;
        position.y = -4f;
        position.z = -1f;
        transform.setTranslation(position);
        model.transform.set(transform);
    }

    @Override
    protected void act(float delta) {
    }

    @Override
    protected void draw(ModelBatch batch, PerspectiveCamera camera) {
        batch.begin(camera);
        batch.render(model);
        batch.end();
    }

    public void changeValue(Movements motion) {
        if (!state.containsKey(motion)) {
            state.put(motion, Boolean.TRUE);
        } else {
            state.put(motion, !state.get(motion));
        }
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
        projectiles.add(Projectil.createNew(position));
    }
}
