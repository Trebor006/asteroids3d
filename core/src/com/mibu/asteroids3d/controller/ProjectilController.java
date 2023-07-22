package com.mibu.asteroids3d.controller;

import com.badlogic.gdx.Gdx;
import com.mibu.asteroids3d.objects.Projectil;
import com.mibu.asteroids3d.util.PosUtils;

import java.util.concurrent.CopyOnWriteArrayList;

public class ProjectilController extends Thread {
    public static float SPEED = 0.00009F;
    private static float maxValueEje = -PosUtils.MAX - 5;
    private volatile CopyOnWriteArrayList<Projectil> projectiles;
    private volatile int indexProyectil;

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
        if (projectiles.size() == 0) {
            return null;
        }

        Projectil projectil = projectiles.get(indexProyectil);
        if (projectil.getPosition().z + (getSpeed() * -1) <= maxValueEje) {
            projectil = null;
            projectiles.remove(indexProyectil);
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
