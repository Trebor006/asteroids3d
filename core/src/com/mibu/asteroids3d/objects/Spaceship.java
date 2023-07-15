package com.mibu.asteroids3d.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelCache;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Disposable;
import com.mibu.asteroids3d.assets.SpaceshipAssets;
import com.mibu.asteroids3d.controller.SpaceshipController;
import com.mibu.asteroids3d.util.AssetManagerUtil;
import com.mibu.asteroids3d.assets.AssetUtils;
import com.mibu.asteroids3d.util.CameraUtil;
import com.mibu.asteroids3d.controller.ProjectilController;
import com.mibu.asteroids3d.util.Movements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Spaceship extends Actor {
    private static final float DELTA_DEGREE = 0.00001f;
    private ModelInstance model;
    private ModelCache cache;

    private boolean[] states;
    private Vector3 position;
    private Vector3 direction;
    private SpaceshipController stateNaveController;
    private CopyOnWriteArrayList<Projectil> projectiles;
    private List<ModelInstance> cacheInstances;

    private ProjectilController projectilController;
    private Sound sound;


    float scaleModel = 0.3f;

    public Spaceship() {
        model = new ModelInstance(AssetManagerUtil.getAssetManager().get(SpaceshipAssets.getDefault(), Model.class));
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

        direction = null;

        sound = Gdx.audio.newSound(Gdx.files.internal(AssetUtils.disparo));
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
    }

    public void rotate(float z) {
        Matrix4 transform = model.transform;
        transform.rotate(0, 0, z, DELTA_DEGREE);
        model.transform.set(transform);
    }

    public void changeSpaceship() {
//        model.get
//        assetManager.get
        // Buscar la clave del modelo en el AssetManager

        model = new ModelInstance(AssetManagerUtil.getAssetManager()
                .get(SpaceshipAssets.switchSpaceshipAsset(), Model.class));
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
        newProyectil.getModelInstance();
        projectiles.add(newProyectil);

        sound.play();
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

    @Override
    public void dispose() {
        // dispose models and such
        cache.dispose();
    }
}
