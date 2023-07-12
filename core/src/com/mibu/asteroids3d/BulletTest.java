package com.mibu.asteroids3d;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
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
import com.badlogic.gdx.utils.ArrayMap;
import com.mibu.asteroids3d.objects.Projectil;
import com.mibu.asteroids3d.util.MyContactListener;
import com.mibu.asteroids3d.util.ProjectilFactory;


public class BulletTest implements ApplicationListener {
    final static short GROUND_FLAG = 1 << 8;
    final static short OBJECT_FLAG = 1 << 9;
    final static short ALL_FLAG = -1;

    PerspectiveCamera cam;
    CameraInputController camController;
    ModelBatch modelBatch;
    Environment environment;
    Model model;
    public static Array<Projectil> instances;
    ArrayMap<String, ProjectilFactory> constructors;
    float spawnTimer;

    btCollisionConfiguration collisionConfig;
    btDispatcher dispatcher;
    MyContactListener contactListener;
    btBroadphaseInterface broadphase;
    btDynamicsWorld dynamicsWorld;
    btConstraintSolver constraintSolver;

    @Override
    public void create() {
        Bullet.init();

        modelBatch = new ModelBatch();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(3f, 7f, 10f);
        cam.lookAt(0, 4f, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);

        ModelBuilder mb = new ModelBuilder();
        mb.begin();
        mb.node().id = "sphere";
        mb.part("sphere", GL20.GL_TRIANGLES, Usage.Position | Usage.Normal, new Material(ColorAttribute.createDiffuse(Color.GREEN)))
                .sphere(1f, 1f, 1f, 10, 10);
        mb.node().id = "box";
        model = mb.end();

        constructors = new ArrayMap<String, ProjectilFactory>(String.class, ProjectilFactory.class);
        constructors.put("sphere", new ProjectilFactory(model, "sphere", new btSphereShape(0.5f), 1f));

        collisionConfig = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collisionConfig);
        broadphase = new btDbvtBroadphase();
        constraintSolver = new btSequentialImpulseConstraintSolver();
        dynamicsWorld = new btDiscreteDynamicsWorld(dispatcher, broadphase, constraintSolver, collisionConfig);
        dynamicsWorld.setGravity(new Vector3(0, -10f, 0));
        contactListener = new MyContactListener();

        instances = new Array<>();
    }

    public void spawn() {
        Projectil obj = constructors.values[MathUtils.random(constructors.size - 1)].construct();
        obj.transform.setFromEulerAngles(MathUtils.random(360f), MathUtils.random(360f), MathUtils.random(360f));
        obj.transform.trn(MathUtils.random(-2.5f, 2.5f), 9f, MathUtils.random(-2.5f, 2.5f));
        obj.body.proceedToTransform(obj.transform);
        obj.body.setUserValue(instances.size);
        obj.body.setCollisionFlags(obj.body.getCollisionFlags() | btCollisionObject.CollisionFlags.CF_CUSTOM_MATERIAL_CALLBACK);
        instances.add(obj);
        dynamicsWorld.addRigidBody(obj.body);
        obj.body.setContactCallbackFlag(OBJECT_FLAG);
        obj.body.setContactCallbackFilter(GROUND_FLAG);
    }

    float angle, speed = 90f;

    @Override
    public void render() {
        final float delta = Math.min(1f / 30f, Gdx.graphics.getDeltaTime());

        angle = (angle + delta * speed) % 360f;

        dynamicsWorld.stepSimulation(delta, 5, 1f / 60f);

        if ((spawnTimer -= delta) < 0) {
            spawn();
            spawnTimer = 1.5f;
        }

        camController.update();

        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(cam);
        modelBatch.render(instances, environment);
        modelBatch.end();
    }

    @Override
    public void dispose() {
        for (Projectil obj : instances)
            obj.dispose();
        instances.clear();

        for (ProjectilFactory ctor : constructors.values())
            ctor.dispose();
        constructors.clear();

        dynamicsWorld.dispose();
        constraintSolver.dispose();
        broadphase.dispose();
        dispatcher.dispose();
        collisionConfig.dispose();

        contactListener.dispose();

        modelBatch.dispose();
        model.dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void resize(int width, int height) {
    }
}

