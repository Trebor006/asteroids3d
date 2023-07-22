package com.mibu.asteroids3d.util;

import java.util.Random;

public class RandomUtil {
    public static float SPEED = 0.000009F;
    public static float MAX_SPEED = 0.0001F;
    private static Random random;

    public static float getRandomPosition() {
        if (random == null) {
            random = new Random();
        }

        //return random.nextFloat(n);
        return random.nextFloat() * 8.0f - 4.0f;
    }

    public static float randomSpeed() {
        return getRandomBoolean() ? SPEED : MAX_SPEED;
    }

    private static boolean getRandomBoolean() {
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        return randomNumber == 1;
    }
}
