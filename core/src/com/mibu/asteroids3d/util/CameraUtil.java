package com.mibu.asteroids3d.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;

public class CameraUtil {
    private static PerspectiveCamera camera;

    public static PerspectiveCamera getCamera() {
        if (camera == null) {
            camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            camera.position.set(0f, 8f, 10f);
            camera.lookAt(0, 4f, 4f);

            camera.near = 1f;
            camera.far = 10000f;
            camera.update();
        }

        return camera;
    }
}
