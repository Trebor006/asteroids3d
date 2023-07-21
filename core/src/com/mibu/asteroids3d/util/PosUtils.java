package com.mibu.asteroids3d.util;

import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class PosUtils {

    public static Vector3 generarPosicionInicial() {
        Random random = new Random();

        float x = random.nextFloat() * (10 - (-10)) + (-10);
        float y = random.nextFloat() * (10 - (-10)) + (-10);
        float z = -10;

        return new Vector3(x, y, z);
    }

    public static Vector3 generarDireccion(Vector3 positionInitial, Vector3 positionFinal, float deltaVariacion) {
        Vector3 direccion = new Vector3();

        direccion.x = (positionFinal.x - positionInitial.x) > 0 ? 1 : 0;
        direccion.y = (positionFinal.y - positionInitial.y) > 0 ? 1 : 0;
        direccion.z = (positionFinal.z - positionInitial.z) > 0 ? 1 : 0;

        // Verifica que solo existan dos coordenadas o menos con valor 1.
        if (direccion.x + direccion.y + direccion.z > 2) {
            if (direccion.x == 1) direccion.x -= deltaVariacion;
            if (direccion.y == 1) direccion.y -= deltaVariacion;
            if (direccion.z == 1) direccion.z -= deltaVariacion;
        }

        // Verifica que al menos exista una coordenada con valor 1.
        if (direccion.x + direccion.y + direccion.z < 1) {
            if (direccion.x == 0 && (positionFinal.x - positionInitial.x) != 0) direccion.x += deltaVariacion;
            if (direccion.y == 0 && (positionFinal.y - positionInitial.y) != 0) direccion.y += deltaVariacion;
            if (direccion.z == 0 && (positionFinal.z - positionInitial.z) != 0) direccion.z += deltaVariacion;
        }

        return direccion;
    }


    public static Vector3 generarPosicionFinal() {
        Random random = new Random();

        float x = random.nextFloat() * (10 - (-10)) + (-10);
        float y = random.nextFloat() * (10 - (-10)) + (-10);
        float z = -10;

        return new Vector3(x, y, z);
    }
//    public Vector3 Avanzar() {
//        return new Vector3(
//                direccion.x * (posicionFinal.x - posicionInicial.x),
//                direccion.y * (posicionFinal.y - posicionInicial.y),
//                direccion.z * (posicionFinal.z - posicionInicial.z)
//        );
//    }
}
