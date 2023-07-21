package com.mibu.asteroids3d.objects;

import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.utils.Disposable;

public abstract class Actor implements Disposable {

    public abstract void draw(ModelBatch batch);

    public abstract void act(float delta);
}
