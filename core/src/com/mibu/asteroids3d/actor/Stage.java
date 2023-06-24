package com.mibu.asteroids3d.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

import java.util.ArrayList;
import java.util.List;

public class Stage {
    private List<Actor> actors;
    private ModelBatch modelBatch;
    private PerspectiveCamera camera;

    public Stage() {
        this.actors = new ArrayList<>();
        this.modelBatch = new ModelBatch();
        this.camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        camera.position.set(0f, 1f, 2f);
        camera.lookAt(0, 0, 0);
        camera.near = 0f;
        camera.far = 300f;
        camera.update();
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }

    public void removeActor(Actor actor) {
        actors.remove(actor);
    }

    public void act() {
        for (Actor actor : actors) {
            actor.act(Gdx.graphics.getDeltaTime());
        }
    }

    public void draw() {
        for (Actor actor : actors) {
            actor.draw(modelBatch, camera);
        }
    }

    public void dispose() {
        modelBatch.dispose();
    }
}
