package com.mibu.asteroids3d.processor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class MyInputProcessor implements UpdatingInputProcessor {

  private ModelInstance spaceship;
  private float speed = 5f;

  public MyInputProcessor(ModelInstance spaceship) {
    this.spaceship = spaceship;
  }

  @Override
  public void update() {
    Vector3 translation = new Vector3();
    if (Gdx.input.isKeyPressed(Keys.UP)) {
      spaceship.transform.trn(translation.set(0, 0, -speed * Gdx.graphics.getDeltaTime()));
    } else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
      spaceship.transform.trn(translation.set(0, 0, speed * Gdx.graphics.getDeltaTime()));
    } else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
      spaceship.transform.trn(translation.set(-speed * Gdx.graphics.getDeltaTime(), 0, 0));
    } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
      spaceship.transform.trn(translation.set(speed * Gdx.graphics.getDeltaTime(), 0, 0));
    }
  }

  @Override
  public boolean keyDown(int keycode) {
    Vector3 translation = new Vector3();

    switch (keycode) {
      case Keys.LEFT:
        System.out.println("left");
        spaceship.transform.trn(translation.set(-speed * Gdx.graphics.getDeltaTime(), 0, 0));
        break;
      case Keys.RIGHT:
        System.out.println("right");
        spaceship.transform.trn(translation.set(speed * Gdx.graphics.getDeltaTime(), 0, 0));
        break;
      case Keys.UP:
        System.out.println("up");
        spaceship.transform.trn(translation.set(0, 0, -speed * Gdx.graphics.getDeltaTime()));
        break;
      case Keys.DOWN:
        System.out.println("down");
        spaceship.transform.trn(translation.set(0, 0, speed * Gdx.graphics.getDeltaTime()));
        break;
    }

    return true;
  }

  public boolean keyUp(int keycode) {
    return false;
  }

  public boolean keyTyped(char character) {
    return false;
  }

  public boolean touchDown(int x, int y, int pointer, int button) {
    return false;
  }

  public boolean touchUp(int x, int y, int pointer, int button) {
    return false;
  }

  public boolean touchDragged(int x, int y, int pointer) {
    return false;
  }

  public boolean mouseMoved(int x, int y) {
    return false;
  }

  public boolean scrolled(float amountX, float amountY) {
    return false;
  }
}
