package com.mibu.asteroids3d.actor;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

public abstract class Actor {
    protected AssetManager assetManager;

    public Actor(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    protected abstract void draw(ModelBatch batch, PerspectiveCamera camera);

    protected abstract void act(float delta);
}
