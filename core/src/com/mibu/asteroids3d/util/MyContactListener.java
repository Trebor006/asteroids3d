package com.mibu.asteroids3d.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.physics.bullet.collision.ContactListener;
import com.mibu.asteroids3d.BulletTest;

public class MyContactListener extends ContactListener {
    @Override
    public boolean onContactAdded(int userValue0,
                                  int partId0,
                                  int index0,
                                  boolean match0,
                                  int userValue1,
                                  int partId1,
                                  int index1,
                                  boolean match1) {
        if (match0)
            ((ColorAttribute) BulletTest.instances.get(userValue0).materials.get(0).get(ColorAttribute.Diffuse)).color.set(Color.WHITE);
        if (match1)
            ((ColorAttribute) BulletTest.instances.get(userValue1).materials.get(0).get(ColorAttribute.Diffuse)).color.set(Color.WHITE);
        return true;
    }
}
