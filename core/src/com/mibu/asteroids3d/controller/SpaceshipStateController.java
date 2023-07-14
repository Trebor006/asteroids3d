package com.mibu.asteroids3d.controller;

import com.badlogic.gdx.Gdx;
import com.mibu.asteroids3d.objects.Spaceship;
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
                    spaceship.updateDirection(0, getSpeed(), 0);
                    spaceship.translate(0, getSpeed(), 0);
                }
                if (state.getKey().equals(Movements.DOWN) && state.getValue()) {
                    spaceship.updateDirection(0, getSpeed() * -1, 0);
                    spaceship.translate(0, getSpeed() * -1, 0);
                }

                if (state.getKey().equals(Movements.LEFT) && state.getValue()) {
                    spaceship.updateDirection(getSpeed() * -1, 0,0);
                    //moveSide(getSpeed() * -1, RotateUtil.LEFT);
                    spaceship.translate(getSpeed() * -1, 0, 0);
                }
                if (state.getKey().equals(Movements.RIGHT) && state.getValue()) {
                    spaceship.updateDirection(getSpeed(), 0,0);
                    spaceship.translate(getSpeed(), 0, 0);
//                    moveSide(getSpeed(), RotateUtil.RIGHT);
                }

                if (state.getKey().equals(Movements.FRONT) && state.getValue()) {
                    spaceship.updateDirection(0, 0, getSpeed() * -1);
                    spaceship.translate(0, 0, getSpeed() * -1);
                }
                if (state.getKey().equals(Movements.BACK) && state.getValue()) {
                    spaceship.updateDirection(0, 0, getSpeed());
                    spaceship.translate(0, 0, getSpeed());
                }
            }
        }
    }



    private void moveSide(float x, int direction) {
        spaceship.translate(x, 0, 0);
        spaceship.rotate(direction);
    }

    public static float getSpeed() {
        return SPEED * Gdx.graphics.getDeltaTime();
    }
}
