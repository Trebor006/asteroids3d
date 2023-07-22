package com.mibu.asteroids3d.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.mibu.asteroids3d.assets.AsteroidAssets;
import com.mibu.asteroids3d.controller.AsteroidController;
import com.mibu.asteroids3d.util.AssetManagerUtil;
import com.mibu.asteroids3d.util.CameraUtil;
import com.mibu.asteroids3d.util.PosUtils;
import com.mibu.asteroids3d.util.RandomUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Asteroid extends Actor {

    private static final float DELTA_DEGREE = 0.00001f;
    float scaleModel = 0.01f;
    private ModelInstance model;
    private ModelInstance model2 = null;

    private ModelCache cache;
    private Vector3 position;
    private Vector3 finalPosition;

    @Getter
    private Float speed;
    private Vector3 position2;
    private Vector3 size;

    private List<ModelInstance> cacheInstances;

    public Asteroid() {
        float x = RandomUtil.getRandomPosition();
        float y = 0f;
        //float y = RandomUtil.getRandomPosition();
        System.out.println("random x " + x);
//        position = new Vector3(x, RandomUtil.getRandomPosition(), -3.99f);
        position = PosUtils.generarPosicionInicial();
        finalPosition = PosUtils.generarPosicionFinal();
        logVector("Posicion asteroide ", position);

        model = new ModelInstance(AssetManagerUtil.getAssetManager().get(AsteroidAssets.getDefault(), Model.class));
        speed = RandomUtil.randomSpeed();

        calculateSize();

        Matrix4 transform = model.transform;
        transform.getTranslation(position);
        transform.scale(scaleModel, scaleModel, scaleModel);

        position.x = x;
        position.y = y;
        position.z = -3.99f;
        transform.setTranslation(position);
        model.transform.set(transform);


        //position2 = new Vector3(position.x - (size.x / 2), position.y + (size.y / 2), position.z + (size.z / 2));
        position2 = new Vector3(position.x, position.y, position.z);
        model2 = null;

        cache = new ModelCache();
        cacheInstances = new ArrayList<>();

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

        position2.x += x;
        position2.y += y;
        position2.z += z;
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
        setModel2();
        batch.render(model2);
        batch.end();

        cache.begin();
        cache.add(cacheInstances);
        cache.end();
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
        cache.dispose();
    }

    public void calculateSize(){
        BoundingBox bounds = new BoundingBox();
        model.calculateBoundingBox(bounds);

        Vector3 min = new Vector3();
        Vector3 max = new Vector3();
        bounds.getMin(min);
        bounds.getMax(max);

        this.size = new Vector3((Math.abs(max.x - min.x) * scaleModel) + 0.3f, (Math.abs(max.y - min.y) * scaleModel) + 0.3f, (Math.abs(max.z - min.z) * scaleModel) + 0.3f);

    }

    public void setModel2(){
        if(model2 == null) {
            ModelBuilder modelBuilder = new ModelBuilder();
            Material material = new Material(ColorAttribute.createDiffuse(Color.BLUE));
            Model modelData = modelBuilder.createSphere(size.x, size.y, size.z, 50, 50,
                    material, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
            model2 = new ModelInstance(modelData);
        }
        model2.transform.setToTranslation(position2);
    }

    public void move() {
        avanzarHaciaDestino();
    }

    public void avanzarHaciaDestino() {
        Vector3 positionF = calculateNewPos(AsteroidController.getSpeed(this));

        position2.x += positionF.x - position.x;
        position2.y += positionF.y - position.y;
        position2.z += positionF.z - position.z;

        position = positionF;
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

    public Vector3 getSize() {
        return size;
    }
}
