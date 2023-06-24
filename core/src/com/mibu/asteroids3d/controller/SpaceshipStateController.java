package com.mibu.asteroids3d.controller;

import com.badlogic.gdx.Gdx;
import com.mibu.asteroids3d.actor.Spaceship;
import com.mibu.asteroids3d.util.Movements;
import com.mibu.asteroids3d.util.RotateUtil;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class SpaceshipStateController extends Thread {
    public static float SPEED = 0.00001F;
    private Spaceship spaceship;
    private Map<Movements, Boolean> states;

    @Override
    public void run() {
        while (true) {
            for (Map.Entry<Movements, Boolean> state : states.entrySet()) {
                if (state.getKey().equals(Movements.UP) && state.getValue()) {
                    spaceship.translate(0, getSpeed(), 0);
                }
                if (state.getKey().equals(Movements.DOWN) && state.getValue()) {
                    spaceship.translate(0, getSpeed() * -1, 0);
                }

                if (state.getKey().equals(Movements.LEFT) && state.getValue()) {
                    moveSide(getSpeed() * -1, RotateUtil.LEFT);
                }
                if (state.getKey().equals(Movements.RIGHT) && state.getValue()) {
                    moveSide(getSpeed(), RotateUtil.RIGHT);
                }

                if (state.getKey().equals(Movements.FRONT) && state.getValue()) {
                    spaceship.translate(0, 0, getSpeed() * -1);
                }
                if (state.getKey().equals(Movements.BACK) && state.getValue()) {
                    spaceship.translate(0, 0, getSpeed());
                }
            }
        }
    }

    private void moveSide(float x, int direction) {
        spaceship.translate(x, 0, 0);
        spaceship.rotate(direction);
    }

    private float getSpeed() {
        return SPEED * Gdx.graphics.getDeltaTime();
    }
}
