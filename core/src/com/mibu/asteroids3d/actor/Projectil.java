package com.mibu.asteroids3d.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.mibu.asteroids3d.assets.AimAssets;
import com.mibu.asteroids3d.controller.ProjectilController;

public class Projectil {
    private Vector3 position;
    private ModelInstance model;

    public Projectil(Vector3 position) {
        this.position = position;

        // Cargar la textura utilizando el AssetManager
        Texture texture = new Texture(Gdx.files.internal(AimAssets.getDefault()));

        // Crear un material con la textura
        Material material = new Material(TextureAttribute.createDiffuse(texture));

        // Crear el modelo del proyectil utilizando un modelBuilder
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        modelBuilder.part("box", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates, material)
                .box(1f, 1f, 1f);
        Model modelData = modelBuilder.end();

        // Crear la instancia del modelo
        model = new ModelInstance(modelData);

        // Configurar la posición y la transformación del modelo
        model.transform.setToTranslation(position);
        model.transform.scale(0.5f, 0.5f, 0.5f);


        ProjectilController projectilController = new ProjectilController(this);
        projectilController.start();

    }

    public static Projectil createNew(Vector3 position) {
        return new Projectil(position);
    }

    public void translate(float x, float y, float z) {
        position.add(x, y, z);
        model.transform.setTranslation(position);
    }

    public Vector3 getPosition() {
        return position;
    }

    public ModelInstance getModelInstance() {
        return model;
    }
}


