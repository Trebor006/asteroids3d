package com.mibu.asteroids3d.util;

import java.util.Random;

public class RandomUtil {
    public static float SPEED = 0.000009F;
    public static float MAX_SPEED = 0.00001F;
    private static Random random;

    public static float getRandomPosition() {
        if (random == null) {
            random = new Random();
        }

        //return random.nextFloat(n);
        return random.nextFloat() * 8.0f - 4.0f;
    }

    public static float randomSpeed() {
        Random random = new Random();
        return SPEED;
//        return SPEED + random.nextFloat() * (MAX_SPEED - SPEED);
    }
}
