package com.mibu.asteroids3d.controller;

import com.badlogic.gdx.Gdx;
import com.mibu.asteroids3d.actor.Projectil;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProjectilController extends Thread {
    public static float SPEED = 0.00001F;

    private Projectil projectil;

    @Override
    public void run() {
        while (true) {
//            for (Projectil projectil : projectils) {
                projectil.translate(0, 0, getSpeed());
//            }
        }
    }

    private float getSpeed() {
        return SPEED * Gdx.graphics.getDeltaTime();
    }
}
