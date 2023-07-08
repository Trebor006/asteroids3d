package com.mibu.asteroids3d.objects;

import com.badlogic.gdx.graphics.g3d.ModelBatch;

public abstract class Actor {

    public abstract void draw(ModelBatch batch);

    public abstract void act(float delta);
}
