package com.mibu.asteroids3d.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.badlogic.gdx.physics.bullet.dynamics.btConstraintSolver;
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btSequentialImpulseConstraintSolver;
import com.badlogic.gdx.utils.Array;
import com.mibu.asteroids3d.controller.SpaceshipController;
import com.mibu.asteroids3d.util.CameraUtil;
import com.mibu.asteroids3d.util.MyContactListener;
import com.mibu.asteroids3d.util.ProjectilFactory;

public class SpaceshipBullet extends Actor {
    final static short GROUND_FLAG = 1 << 8;
    final static short OBJECT_FLAG = 1 << 9;
    final static short ALL_FLAG = -1;
    float angle, speed = 90f;

    private static final float DELTA_DEGREE = 0.00001f;
    private Model model;
    //    private boolean[] states;
    public static Array<Projectil> instances;


    private SpaceshipController stateNaveController;
    private Vector3 position;








    CameraInputController camController;
    ModelBatch modelBatch;
    Environment environment;
//    Model model;
//    public static Array<Projectil> instances;
    float spawnTimer;

    btCollisionConfiguration collisionConfig;
    btDispatcher dispatcher;
    MyContactListener contactListener;
    btBroadphaseInterface broadphase;
    btDynamicsWorld dynamicsWorld;
    btConstraintSolver constraintSolver;


    public SpaceshipBullet() {

        ModelBuilder mb = new ModelBuilder();
        mb.begin();
        mb.node().id = "sphere";
        mb.part("sphere",
                        GL20.GL_TRIANGLES,
                        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal,
                        new Material(ColorAttribute.createDiffuse(Color.BLACK)))
                .sphere(0.1f, 0.1f, 0.1f, 10, 10);
        model = mb.end();

        position = Vector3.Zero;







//        modelBatch = new ModelBatch();
//        environment = new Environment();
//        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
//        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

//        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        cam.position.set(0f, 7f, 10f);
//        cam.lookAt(0, 4f, 0);
//
//        cam.near = 1f;
//        cam.far = 10000f;
//        cam.update();

        camController = new CameraInputController(CameraUtil.getCamera());
//        Gdx.input.setInputProcessor(camController);

//        ModelBuilder mb = new ModelBuilder();
//        mb.begin();
//        mb.node().id = "sphere";
//        mb.part("sphere",
//                        GL20.GL_TRIANGLES,
//                        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal, new Material(ColorAttribute.createDiffuse(Color.GREEN)))
//                .sphere(1f, 1f, 1f, 10, 10);
//        model = mb.end();

        collisionConfig = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collisionConfig);

        broadphase = new btDbvtBroadphase();
        constraintSolver = new btSequentialImpulseConstraintSolver();
        dynamicsWorld = new btDiscreteDynamicsWorld(dispatcher, broadphase, constraintSolver, collisionConfig);
        dynamicsWorld.setGravity(new Vector3(0, -10f, 0));

        contactListener = new MyContactListener();

        instances = new Array<>();

    }

    @Override
    public void draw(ModelBatch batch) {
        camController.update();

        batch.begin(CameraUtil.getCamera());
        for (Projectil instance : instances) {
            batch.render(instance);
        }
        batch.end();
    }

    @Override
    public void act(float delta) {

    }

    public void createNew() {
        Projectil obj = new ProjectilFactory(model, "sphere", new btSphereShape(0.001f), 1f).construct();
        obj.transform.setFromEulerAngles(MathUtils.random(360f), MathUtils.random(360f), MathUtils.random(360f));
        obj.transform.trn(MathUtils.random(-2.5f, 2.5f), 9f, MathUtils.random(-2.5f, 2.5f));
        obj.body.proceedToTransform(obj.transform);
        obj.body.setUserValue(instances.size);
        obj.body.setCollisionFlags(obj.body.getCollisionFlags() | btCollisionObject.CollisionFlags.CF_CUSTOM_MATERIAL_CALLBACK);
        instances.add(obj);
//        dynamicsWorld.addRigidBody(obj.body);
        obj.body.setContactCallbackFlag(OBJECT_FLAG);
        obj.body.setContactCallbackFilter(GROUND_FLAG);
    }
}
