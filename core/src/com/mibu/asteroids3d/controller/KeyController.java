package com.mibu.asteroids3d.controller;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.mibu.asteroids3d.objects.Spaceship;
import com.mibu.asteroids3d.util.Movements;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class KeyController extends InputAdapter {

    @Setter
    private Spaceship spaceship;

    @Override
    public boolean keyDown(int keycode) {

        if (GameController.isGameOver()) {
            return false;
        }

        switch (keycode) {
            case Keys.UP:
                spaceship.changeValue(Movements.UP);
                break;
            case Keys.DOWN:
                spaceship.changeValue(Movements.DOWN);
                break;
            case Keys.LEFT:
//                spaceship.changeValue(Movements.LEFT);
                spaceship.changeValue(Movements.LEFT_MOVE);
                break;
            case Keys.RIGHT:
//                spaceship.changeValue(Movements.RIGHT);
                spaceship.changeValue(Movements.RIGHT_MOVE);
                break;
            case Keys.A:
                spaceship.changeValue(Movements.LEFT_MOVE);
                break;
            case Keys.D:
                spaceship.changeValue(Movements.RIGHT_MOVE);
                break;
            case Keys.W:
                spaceship.changeValue(Movements.FRONT);
                break;
            case Keys.S:
                spaceship.changeValue(Movements.BACK);
                break;
            case Keys.TAB:
                spaceship.changeSpaceship();
                break;
//            case Keys.SPACE:
//                spaceship.shoot();
//                break;

//            case Keys.E:
//                spaceship.changeValue(Movements.BACK);
//                break;
//            case Keys.Q:
//                spaceship.changeValue(Movements.BACK);
//                break;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (GameController.isGameOver()) {
            return false;
        }

        switch (keycode) {
            case Keys.UP:
                spaceship.changeValue(Movements.UP);
                break;
            case Keys.DOWN:
                spaceship.changeValue(Movements.DOWN);
                break;
            case Keys.LEFT:
                spaceship.changeValue(Movements.LEFT_MOVE);
//                spaceship.changeValue(Movements.LEFT);
                break;
            case Keys.RIGHT:
                spaceship.changeValue(Movements.RIGHT_MOVE);
//                spaceship.changeValue(Movements.RIGHT);
                break;
            case Keys.W:
                spaceship.changeValue(Movements.FRONT);
                break;
            case Keys.S:
                spaceship.changeValue(Movements.BACK);
                break;
            case Keys.A:
                spaceship.changeValue(Movements.LEFT_MOVE);
                break;
            case Keys.D:
                spaceship.changeValue(Movements.RIGHT_MOVE);
                break;
            case Keys.SPACE:
                spaceship.shoot();
                break;

        }

        return true;
    }

}
