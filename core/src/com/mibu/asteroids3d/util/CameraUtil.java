package com.mibu.asteroids3d.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;

public class CameraUtil {
    public static final float CAM_NEAR = 1f; // Distancia mínima que la cámara puede ver
    public static final float CAM_FAR = 300f; // Distancia máxima que la cámara puede ver
    private static PerspectiveCamera camera;

    public static PerspectiveCamera getCamera() {
        if (camera == null) {
            camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            camera.position.set(0, 0, 15);
            camera.lookAt(0, 0, 0);

            camera.near = CAM_NEAR;
            camera.far = CAM_FAR;
            camera.update();
        }

        return camera;
    }
}
