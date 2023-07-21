package com.mibu.asteroids3d.controller;

import com.badlogic.gdx.Gdx;
import com.mibu.asteroids3d.objects.Projectil;

import java.util.concurrent.CopyOnWriteArrayList;

public class ProjectilController extends Thread {
    public static float SPEED = 0.00005F;

    private volatile CopyOnWriteArrayList<Projectil> projectiles;
    private volatile int indexProyectil;

    private static float maxValueEje = -4f;

    public ProjectilController(CopyOnWriteArrayList<Projectil> projectiles) {
        this.projectiles = projectiles;
        this.indexProyectil = 0;
    }


    @Override
    public void run() {
        while (true) {
            moveProyectil();
        }
    }

    private synchronized Projectil moveProyectil() {
        if (projectiles.size() == 0){
            return null;
        }

        Projectil projectil = projectiles.get(indexProyectil);
        if(projectil.getPosition().z + (getSpeed() * -1) <= maxValueEje) {
            projectiles.remove(indexProyectil);
            projectil = null;
        } else {
            projectil.translate(0f, 0f, (getSpeed() * -1));
            indexProyectil++;
        }

        if (indexProyectil >= projectiles.size()) {
            indexProyectil = 0;
        }

        return projectil;
    }

    private float getSpeed() {
        return SPEED * Gdx.graphics.getDeltaTime();
    }
}
