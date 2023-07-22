package com.mibu.asteroids3d.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.mibu.asteroids3d.assets.SpaceshipAssets;
import com.mibu.asteroids3d.controller.ProjectilController;
import com.mibu.asteroids3d.controller.SoundController;
import com.mibu.asteroids3d.controller.SpaceshipController;
import com.mibu.asteroids3d.util.AssetManagerUtil;
import com.mibu.asteroids3d.util.CameraUtil;
import com.mibu.asteroids3d.util.Movements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Spaceship extends Actor {
    private static final float DELTA_DEGREE = 0.00001f;
    public boolean[] states;
    float scaleModel = 0.3f;
    private ModelInstance model;
    private ModelInstance model2;
    private ModelCache cache;
    private Vector3 position;
    private Vector3 size;
    private Vector3 position2;

    private Vector3 direction;
    private SpaceshipController stateNaveController;
    private CopyOnWriteArrayList<Projectil> projectiles;
    private List<ModelInstance> cacheInstances;
    private ProjectilController projectilController;

    public Spaceship() {
        model = new ModelInstance(AssetManagerUtil.getAssetManager().get(SpaceshipAssets.nave, Model.class));

        calculateSize();

        states = new boolean[Movements.values().length];

        position = new Vector3(0, 0, 0);

        stateNaveController = new SpaceshipController(this, states);
        stateNaveController.start();

        Matrix4 transform = model.transform;
        transform.getTranslation(position);
        transform.setToRotation(0, 1, 0, 180);
        transform.scale(scaleModel, scaleModel, scaleModel);
        //transform.scale(0.3f, 0.3f, 0.3f);

        position.x = 0f;
        position.y = -4f;
        position.z = 4f;
        logPosition();
        transform.setTranslation(position);
        model.transform.set(transform);

        position2 = new Vector3(position.x, position.y, position.z - 1f);

        ModelBuilder modelBuilder = new ModelBuilder();
        Material material = new Material(ColorAttribute.createDiffuse(Color.RED));
        Model modelData = modelBuilder.createSphere(size.x * scaleModel, size.y * scaleModel, size.z * scaleModel, 50, 50,
                material, Usage.Position | Usage.Normal);
        model2 = new ModelInstance(modelData);
        model2.transform.setToTranslation(position2);

        direction = null;
        cache = new ModelCache();

        initializeProjectiles();
        cacheInstances = new ArrayList<>();
    }

    private void initializeProjectiles() {
        projectiles = new CopyOnWriteArrayList<>();
        projectilController = new ProjectilController(projectiles);
        projectilController.start();
    }

    @Override
    public void act(float delta) {
    }

    @Override
    public void draw(ModelBatch batch) {
        batch.begin(CameraUtil.getCamera());
        batch.render(model);
        batch.render(model2);
        batch.end();

        cache.begin();
        cache.add(cacheInstances);
        cache.end();

        for (Projectil projectil : projectiles) {
            projectil.draw(batch);
        }
    }

    public void changeValue(Movements motion) {
        states[motion.ordinal()] = !states[motion.ordinal()];
    }

    public void translate(float x, float y, float z) {
        Matrix4 transform = model.transform;
        position.x += x;
        position.y += y;
        position.z += z;
        transform.setTranslation(position);
        model.transform.set(transform);

        Matrix4 transform1 = model2.transform;
        position2.x += x;
        position2.y += y;
        position2.z += z;
        transform1.setTranslation(position);
        model2.transform.set(transform1);
    }

    public void rotate(float z) {
        Matrix4 transform = model.transform;
        transform.rotate(0, 0, z, DELTA_DEGREE);
        model.transform.set(transform);
    }

    public void shoot() {
        BoundingBox boundingBox = new BoundingBox();
        model.calculateBoundingBox(boundingBox);
        Vector3 dimensions = new Vector3();
        boundingBox.getDimensions(dimensions);

        // Obtener las dimensiones del modelo
        float width = dimensions.x * scaleModel;
        float height = dimensions.y * scaleModel;
        float depth = dimensions.z * scaleModel;

        System.out.println("Ancho del modelo: " + width);
        System.out.println("Altura del modelo: " + height);
        System.out.println("Profundidad del modelo: " + depth);

        Vector3 positionProjectile = new Vector3(position.x, position.y + (height / 2), position.z - depth);
//        Vector3 directionProjectile = new Vector3(direction.x, direction.y, direction.z);
//        Vector3 directionProjectile = new Vector3(0f, 0, SpaceshipStateController.getSpeed() * -1);
        logVector("Posision Nave", position);
        logVector("Posision Proyectil", positionProjectile);
//        logVector("Direccion Proyectil", directionProjectile);

        Projectil newProyectil = Projectil.createNew(positionProjectile);
        projectiles.add(newProyectil);

        SoundController.shootSound.play();
    }

    public void logPosition() {
        if (position != null) {
            System.out.println("position => x=" + position.x + ", y=" + position.y + ", z=" + position.z);
        }
    }

    public void logVector(String titulo, Vector3 direction) {
        if (direction != null) {
            System.out.println(titulo + " => x=" + direction.x + ", y=" + direction.y + ", z=" + direction.z);
        }
    }

    public ModelInstance getModel() {
        return model;
    }

    public Vector3 getPosition() {
        return position;
    }

    public List<ModelInstance> getProyectilesModelInstances() {
        return cacheInstances;
    }

    @Override
    public void dispose() {
        // dispose models and such
        cache.dispose();
    }

    public List<Projectil> getProyectiles() {
        return projectiles;
    }

    public void reiniciarPosicion() {
        position = new Vector3(0, 0, 0);
    }

    public void calculateSize(){
        BoundingBox bounds = new BoundingBox();
        model.calculateBoundingBox(bounds);

        Vector3 min = new Vector3();
        Vector3 max = new Vector3();
        bounds.getMin(min);
        bounds.getMax(max);

        this.size = new Vector3(Math.abs(max.x - min.x) * scaleModel, Math.abs(max.y - min.y) * scaleModel, Math.abs(max.z - min.z) * scaleModel);

    }
}
