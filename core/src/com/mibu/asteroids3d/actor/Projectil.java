package com.mibu.asteroids3d.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.mibu.asteroids3d.assets.AimAssets;
import com.mibu.asteroids3d.controller.ProjectilController;


public class Projectil extends Actor{
    private Vector3 position;
    public Vector3 direction;
    private ModelInstance model;

    ShapeRenderer shapeRenderer;

    public Projectil(Vector3 position, Vector3 direction) {
        super(null);

        this.position = position;
        this.direction = direction;

        ModelBuilder modelBuilder = new ModelBuilder();
        Model modelData = modelBuilder.createSphere(0.1f, 0.1f, 0.1f, 50, 50,
                new Material(), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        model = new ModelInstance(modelData);
        model.transform.setToTranslation(position);
        //model.transform.scale(0.5f, 0.5f, 0.5f);

        // Cargar la textura utilizando el AssetManager
        /*Texture texture = new Texture(Gdx.files.internal(AimAssets.getDefault()));

        // Crear un material con la textura
        Material material = new Material(TextureAttribute.createDiffuse(texture));

        // Crear el modelo del proyectil utilizando un modelBuilder
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        modelBuilder.part("circle", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates, material)
                .box(0.3f, 0.3f, 0.3f);
        Model modelData = modelBuilder.end();

        // Crear la instancia del modelo
        model = new ModelInstance(modelData);

        // Configurar la posición y la transformación del modelo
        model.transform.setToTranslation(position);
        model.transform.scale(0.5f, 0.5f, 0.5f);
         */
    }

    public static Projectil createNew(Vector3 position, Vector3 direction) {
        return new Projectil(position, direction);
    }

    public void translate(float x, float y, float z) {
        Matrix4 transform = model.transform;
        position.x += x;
        position.y += y;
        position.z += z;
        transform.setTranslation(position);
        model.transform.set(transform);
    }

    public Vector3 getPosition() {
        return position;
    }

    public ModelInstance getModelInstance() {
        return model;
    }

    @Override
    protected void draw(ModelBatch batch, PerspectiveCamera camera) {
        batch.begin(camera);
        batch.render(model);
        batch.end();
    }

    @Override
    protected void act(float delta) {

    }
}


