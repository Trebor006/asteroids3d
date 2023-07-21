package com.mibu.asteroids3d.assets;

public class HealthAssets {

    public static String vida100 = "assets/vida/1.png";
    public static String vida80 = "assets/vida/2.png";
    public static String vida60 = "assets/vida/3.png";
    public static String vida40 = "assets/vida/4.png";
    public static String vida20 = "assets/vida/5.png";
    public static String vida0 = "assets/vida/6.png";


    public static String getVidaAsset(Integer vida) {
        if (vida == 100) {
            return vida100;
        }
        if (vida == 80) {
            return vida80;
        }

        if (vida == 60) {
            return vida60;
        }
        if (vida == 40) {
            return vida40;
        }
        if (vida == 20) {
            return vida20;
        }

        return vida0;
    }
}
