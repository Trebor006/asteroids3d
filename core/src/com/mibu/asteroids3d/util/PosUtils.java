package com.mibu.asteroids3d.util;

import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class PosUtils {
    public static Integer MAX = 20;

    public static Vector3 generarPosicionInicial() {
        Random random = new Random();

        float x = random.nextFloat() * (MAX - (-MAX)) + (-MAX);
        float y = random.nextFloat() * (MAX - (-MAX)) + (-MAX);
        float z = -MAX;

        return new Vector3(x, y, z);
    }

    public static Vector3 generarPosicionFinal() {
        Random random = new Random();

        float x = random.nextFloat() * (MAX - (-MAX)) + (-MAX);
        float y = random.nextFloat() * (MAX - (-MAX)) + (-MAX);
        float z = MAX;

        return new Vector3(x, y, z);
    }
}
