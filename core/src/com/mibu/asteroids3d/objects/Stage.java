package com.mibu.asteroids3d.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

import java.util.ArrayList;
import java.util.List;

public class Stage {
    private List<Actor> actors;
    private ModelBatch modelBatch;

    public Stage() {
        this.actors = new ArrayList<>();
        this.modelBatch = new ModelBatch();
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
            actor.draw(modelBatch);
        }
    }

    public void dispose() {
        modelBatch.dispose();
    }
}
