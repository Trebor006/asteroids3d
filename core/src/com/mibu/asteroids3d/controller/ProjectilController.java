package com.mibu.asteroids3d.controller;

import com.badlogic.gdx.Gdx;
import com.mibu.asteroids3d.actor.Projectil;
import lombok.AllArgsConstructor;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@AllArgsConstructor
public class ProjectilController extends Thread {
    public static float SPEED = 0.00001F;

    private CopyOnWriteArrayList<Projectil> projectiles;

    @Override
    public void run() {
        int i = 0;
        while (true) {
            Projectil projectil = projectiles.get(i);
            projectil.translate(projectil.direction.x, projectil.direction.y, projectil.direction.z);

            i++;
            if (i >= projectiles.size()) {
                i = 0;
            }
        }
    }

    private float getSpeed() {
        return SPEED * Gdx.graphics.getDeltaTime();
    }
}
