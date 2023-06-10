package com.mibu.asteroids3d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.mibu.asteroids3d.processor.MyInputProcessor;
import com.mibu.asteroids3d.processor.UpdatingInputProcessor;

public class Asteroids3D extends ApplicationAdapter {
  public PerspectiveCamera cam;
  public CameraInputController camController;
  public ModelBatch modelBatch;
  public Model model;
  public ModelInstance instance;

  @Override
  public void create() {
    modelBatch = new ModelBatch();
    cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    cam.position.set(10f, 10f, 10f);
    cam.lookAt(0, 0, 0);
    cam.near = 1f;
    cam.far = 300f;
    cam.update();

    camController = new CameraInputController(cam); // Inicialización del controlador de la cámara

    ObjLoader loader = new ObjLoader();
//    model = loader.loadModel(Gdx.files.internal("naves/Defiant/defiant.obj"));
//    model = loader.loadModel(Gdx.files.internal("naves/Constellation/Constellation.obj"));
    model = loader.loadModel(Gdx.files.internal("naves/NeghVarclass/neghvar.obj"));
//    model = loader.loadModel(Gdx.files.internal("naves/PrometheusNX59650/prometheus.obj"));
//    model = loader.loadModel(Gdx.files.internal("naves/SaberClass/saberncc61947.obj"));
//    model = loader.loadModel(Gdx.files.internal("naves/VoyagerNCC74656/voyager.obj"));
    instance = new ModelInstance(model);

    Gdx.input.setInputProcessor(new MyInputProcessor(instance)); // Input processor here
  }

  @Override
  public void render() {
    camController.update();
    ((UpdatingInputProcessor) Gdx.input.getInputProcessor()).update();

    Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

    modelBatch.begin(cam);
    modelBatch.render(instance);
    modelBatch.end();
  }

  @Override
  public void dispose() {
    modelBatch.dispose();
    model.dispose();
  }
}
