package com.mibu.asteroids3d.controller;

import com.mibu.asteroids3d.objects.Projectil;
import com.mibu.asteroids3d.objects.Stage;

public class GameController extends Thread{

    private volatile int prioridad = 0;
    // 0 : Nave
    // 1 : Proyectil
    // 2 : Asteroide

    private Stage stage;

    public GameController(Stage stage) {
        this.stage = stage;
        prioridad = 0;
    }

    public void run() {
        prioridad = 0;
        while (true) {


        }
    }
}
