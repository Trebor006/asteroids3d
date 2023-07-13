package com.mibu.asteroids3d.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.mibu.asteroids3d.assets.SpaceshipAssets;
import com.mibu.asteroids3d.controller.ProjectilController;
import com.mibu.asteroids3d.controller.SpaceshipStateController;
import com.mibu.asteroids3d.util.Movements;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Spaceship extends Actor {
    private static final float DELTA_DEGREE = 0.00001f;
    private ModelInstance model;
    private Map<Movements, Boolean> state;
    private Vector3 position;
    private Vector3 direction;
    private SpaceshipStateController stateNaveController;
    private CopyOnWriteArrayList<Projectil> projectiles;
    private ProjectilController projectilController;

    public Spaceship(AssetManager assetManager) {
        super(assetManager);
        model = new ModelInstance(assetManager.get(SpaceshipAssets.getDefault(), Model.class));
        state = new ConcurrentHashMap<>();

        stateNaveController = new SpaceshipStateController(this, state);
        stateNaveController.start();

        position = new Vector3(0, 0, 0);
        Matrix4 transform = model.transform;
        transform.getTranslation(position);
        transform.setToRotation(0, 1, 0, 180);
        transform.scale(0.3f, 0.3f, 0.3f);

        position.x = 0f;
        position.y = -4f;
        position.z = -1f;
        logPosition();
        transform.setTranslation(position);
        model.transform.set(transform);

        direction = null;

        initializeProjectiles();
    }

    private void initializeProjectiles(){
        projectiles = new CopyOnWriteArrayList<>();
        projectilController = new ProjectilController(projectiles);
        projectilController.start();
    }

    @Override
    protected void act(float delta) {
    }

    @Override
    protected void draw(ModelBatch batch, PerspectiveCamera camera) {
        batch.begin(camera);
        batch.render(model);
        batch.end();
        for (Projectil projectil : projectiles) {
            projectil.draw(batch, camera);
//            batch.begin(camera);
//            batch.render(projectil.getModelInstance());
//            batch.end();
        }
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
        //logPosition();
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
        calculateDirection();
        logPosition();
        logDirection();
        Vector3 positionProjectile = new Vector3(position.x, position.y, position.z);
        Vector3 directionProjectile = new Vector3(direction.x, direction.y, direction.z);
        projectiles.add(Projectil.createNew(positionProjectile, directionProjectile));
    }

    public void updateDirection(float x, float y, float z){
        if(direction == null) {
            direction = new Vector3(x, y, z);
        } else {
            direction.x = x;
            direction.y = y;
            direction.z = z;
        }
        //System.out.println("direction => x="+direction.x+", y="+direction.y+", z="+direction.z);
    }

    public void calculateDirection(){
        if(position.x == 0f){
            updateDirection(0f, SpaceshipStateController.getSpeed(), 0f);
            return;
        }
        // todo mostrar mira o hacer esto variable
        float alturaMira= 8f;

        //calcular angulo
        float opuesto = Math.abs(position.x);
        float adyacente = alturaMira;
//        double angulo = Math.tan(adyacente / opuesto);
        float opuestodif = SpaceshipStateController.getSpeed();
        float adyacentedif = opuestodif * adyacente / opuesto;
        if(position.x > 0){
            adyacentedif = adyacentedif * -1;
        }

        updateDirection(SpaceshipStateController.getSpeed(), adyacentedif, 0f);
    }

    public void logPosition(){
        if(position != null){
            System.out.println("position => x="+position.x+", y="+position.y+", z="+position.z);
        }
    }
    public void logDirection(){
        if(direction != null){
            System.out.println("direction => x="+direction.x+", y="+direction.y+", z="+direction.z);
        }
    }

}
