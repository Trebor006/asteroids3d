package com.mibu.asteroids3d.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.mibu.asteroids3d.assets.AimAssets;
import com.mibu.asteroids3d.assets.AsteroidAssets;
import com.mibu.asteroids3d.assets.SpaceshipAssets;
import com.mibu.asteroids3d.controller.AsteroidController;
import com.mibu.asteroids3d.controller.ProjectilController;
import com.mibu.asteroids3d.controller.SpaceshipController;
import com.mibu.asteroids3d.util.AssetManagerUtil;
import com.mibu.asteroids3d.util.CameraUtil;
import com.mibu.asteroids3d.util.Movements;

import java.util.concurrent.CopyOnWriteArrayList;

public class Asteroid  extends Actor{

    private static final float DELTA_DEGREE = 0.00001f;
    private ModelInstance model;
    private boolean[] states;
    private Vector3 position;
    float scaleModel = 0.3f;

    public Asteroid() {
        position = new Vector3(0f, 0f,-3.9f);

//        // Cargar la textura utilizando el AssetManager
//        Texture texture = new Texture(Gdx.files.internal(AsteroidAssets.getDefault()));
//
//        // Crear un material con la textura
//        Material material = new Material(TextureAttribute.createDiffuse(texture));
//
//        // Crear el modelo del proyectil utilizando un modelBuilder
//        ModelBuilder modelBuilder = new ModelBuilder();
//        modelBuilder.begin();
//        modelBuilder.part("box", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates, material)
//                .box(1f, 1f, 1f);
//        Model modelData = modelBuilder.end();
//
//        // Crear la instancia del modelo
//        model = new ModelInstance(modelData);
//
//        // Configurar la posición y la transformación del modelo
//
//        model.transform.setToTranslation(position);
//        model.transform.scale(0.5f, 0.5f, 0.5f);


        model = new ModelInstance(AssetManagerUtil.getAssetManager().get(AsteroidAssets.getDefault(), Model.class));

        position = new Vector3(0f, 0f,-3.99f);
        Matrix4 transform = model.transform;
        transform.getTranslation(position);
        //transform.setToRotation(0, 1, 0, 180);
        transform.scale(scaleModel, scaleModel, scaleModel);
        //transform.scale(0.3f, 0.3f, 0.3f);

        position.x = 0f;
        position.y = 0f;
        position.z = -3.9f;

        transform.setTranslation(position);
        model.transform.set(transform);


//        ModelBuilder modelBuilder = new ModelBuilder();
//        Model modelData = modelBuilder.createSphere(0.1f, 0.1f, 0.1f, 50, 50,
//                new Material(), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
//        model = new ModelInstance(modelData);
//        model.transform.setToTranslation(position);

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
}
