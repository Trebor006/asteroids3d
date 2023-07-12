package com.mibu.asteroids3d.objects;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.badlogic.gdx.utils.Disposable;
import com.mibu.asteroids3d.util.MyMotionState;

public class Projectil extends ModelInstance implements Disposable {
    public final btRigidBody body;
    public final MyMotionState motionState;

    public Projectil(Model model, String node, btRigidBody.btRigidBodyConstructionInfo constructionInfo) {
        super(model, node);
        motionState = new MyMotionState();
        motionState.transform = transform;
        body = new btRigidBody(constructionInfo);
        body.setMotionState(motionState);
    }

    @Override
    public void dispose() {
        body.dispose();
        motionState.dispose();
    }
}
