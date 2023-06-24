package com.mibu.asteroids3d;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("Asteroids3D");

        // Obtener el ancho y alto del monitor principal
        DisplayMode displayMode = Lwjgl3ApplicationConfiguration.getDisplayMode();
        int screenWidth = displayMode.width;
        int screenHeight = displayMode.height;

        // Establecer el ancho y alto de la ventana para que coincida con el monitor principal
        config.setWindowedMode(screenWidth, screenHeight);
        config.setMaximized(true);

        new Lwjgl3Application(new Asteroids3D(), config);
    }
}
