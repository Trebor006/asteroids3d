package com.mibu.asteroids3d.controller;

import com.badlogic.gdx.Gdx;
import com.mibu.asteroids3d.objects.Spaceship;
import com.mibu.asteroids3d.util.Movements;
import com.mibu.asteroids3d.util.RotateUtil;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SpaceshipController extends Thread {
    public static float SPEED = 0.00001F;
    private Spaceship spaceship;
    private boolean[] states;

    @Override
    public void run() {
        Movements[] movements = Movements.values();
        int i = 0;
        while (true) {
            if (states[i]) {
                moveSpaceship(i, movements);
            }

            i++;
            if (i == states.length) {
                i = 0;
            }
        }
    }

    private void moveSpaceship(int i, Movements[] movements) {
        Movements movement = movements[i];
        switch (movement) {
            case UP:
                spaceship.translate(0, getSpeed(), 0);
                break;
            case DOWN:
                spaceship.translate(0, getSpeed() * -1, 0);
                break;

            case LEFT:
                moveSide(getSpeed() * -1, RotateUtil.LEFT);
                break;
            case LEFT_MOVE:
                move(getSpeed() * -1, RotateUtil.LEFT);
                break;
            case RIGHT:
                moveSide(getSpeed(), RotateUtil.RIGHT);
                break;
            case RIGHT_MOVE:
                move(getSpeed(), RotateUtil.RIGHT);
                break;

            case FRONT:
                spaceship.translate(0, 0, getSpeed() * -1);
                break;
            case BACK:
                spaceship.translate(0, 0, getSpeed());
                break;
            default:
                break;
        }
    }

    private void moveSide(float x, int direction) {
        spaceship.translate(x, x, x);
        spaceship.rotate(direction);
    }

    private void move(float x, int direction) {
        spaceship.translate(x, 0, 0);
//        spaceship.rotate(direction);
    }

    private float getSpeed() {
        return SPEED * Gdx.graphics.getDeltaTime();
    }
}
