package com.mibu.asteroids3d.controller;

import com.badlogic.gdx.Gdx;
import com.mibu.asteroids3d.objects.Projectil;
import lombok.AllArgsConstructor;

import java.util.concurrent.CopyOnWriteArrayList;

@AllArgsConstructor
public class ProjectilController extends Thread {
    public static float SPEED = 0.00001F;

    private volatile CopyOnWriteArrayList<Projectil> projectiles;

    private static float maxValueEje = -4f;

    @Override
    public void run() {
        int i = 0;
        while (true) {
            if (projectiles.size() == 0){
                continue;
            }

            Projectil projectil = projectiles.get(i);
            if(projectil.getPosition().z + projectil.direction.z <= maxValueEje) {
                projectiles.remove(i);
            } else {
                projectil.translate(projectil.direction.x, projectil.direction.y, projectil.direction.z);
                i++;
            }

            if (i >= projectiles.size()) {
                i = 0;
            }
        }
    }

    private float getSpeed() {
        return SPEED * Gdx.graphics.getDeltaTime();
    }
}
