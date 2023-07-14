package com.mibu.asteroids3d.objects;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.mibu.asteroids3d.util.CameraUtil;


public class Projectil extends Actor{
    private Vector3 position;
    public Vector3 direction;
    private ModelInstance model;

    public volatile boolean isVisible;

    public Projectil(Vector3 position, Vector3 direction) {

        this.position = position;
        this.direction = direction;

        ModelBuilder modelBuilder = new ModelBuilder();
        Model modelData = modelBuilder.createSphere(0.1f, 0.1f, 0.1f, 50, 50,
                new Material(), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        model = new ModelInstance(modelData);
        model.transform.setToTranslation(position);
        isVisible = true;
    }

    public static Projectil createNew(Vector3 position, Vector3 direction) {
        return new Projectil(position, direction);
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

    public void destroy(){

    }
}


