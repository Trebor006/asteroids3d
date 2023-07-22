package com.mibu.asteroids3d.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.mibu.asteroids3d.util.CameraUtil;


public class Projectil extends Actor {
    public volatile boolean isVisible;
    private Vector3 position;
    private Vector3 position2;
    private Vector3 size;
    private ModelInstance model;
    private ModelInstance model2;

    public Projectil(Vector3 position) {

        this.position = position;

        ModelBuilder modelBuilder = new ModelBuilder();
        Material material = new Material(ColorAttribute.createDiffuse(Color.YELLOW));
        Model modelData = modelBuilder.createSphere(0.3f, 0.3f, 0.3f, 50, 50,
                material, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        model = new ModelInstance(modelData);
        model.transform.setToTranslation(position);

        position2 = new Vector3(position.x, position.y, position.z - 1f);

//        ModelBuilder modelBuilder1 = new ModelBuilder();
//        Material material1 = new Material(ColorAttribute.createDiffuse(Color.RED));
//        Model modelData1 = modelBuilder.createSphere(size.x, size.y, size.z, 50, 50,
//                material, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
//        model2 = new ModelInstance(modelData);
//        model2.transform.setToTranslation(position2);

        isVisible = true;
    }

    public static Projectil createNew(Vector3 position) {
        return new Projectil(position);
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

    public void destroy() {

    }

    @Override
    public void dispose() {

    }
}


