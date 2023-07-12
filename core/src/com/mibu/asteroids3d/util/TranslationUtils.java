package com.mibu.asteroids3d.util;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class TranslationUtils {
    public void setTranslate(ModelInstance model,
//                             Vector3 origin,
                             float x, float y, float z) {
        Matrix4 transform = model.transform;
//        origin.x = x;
//        origin.y = y;
//        origin.z = z;
        transform.setTranslation(new Vector3(x, y, z));
    }

//    public void translate(ModelInstance model, float dx, float dy, float dz) {
//        Matrix4 transform = model.transform;
////        position.x += dx;
////        position.y += dy;
////        position.z += dz;
//        transform.setTranslation(new Vector3(x, y, z));
//    }

    public void setScale(ModelInstance model, float x, float y, float z) {
        Matrix4 transform = model.transform;
        transform.scale(x, y, z);
    }
}
