package com.mibu.asteroids3d.util;

import java.util.Random;

public class RandomUtil {
    private static Random random;

    public static float getRandomPosition() {
        if (random == null) {
            random = new Random();
        }

        //return random.nextFloat(n);
        return random.nextFloat() * 8.0f - 4.0f;
    }
}
