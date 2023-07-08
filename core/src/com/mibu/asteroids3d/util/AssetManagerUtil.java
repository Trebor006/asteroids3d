package com.mibu.asteroids3d.util;

import com.badlogic.gdx.assets.AssetManager;

public class AssetManagerUtil {

    private static AssetManager assetManager;

    public static AssetManager getAssetManager() {
        if (assetManager == null) {
            assetManager = new AssetManager();
        }
        return assetManager;
    }
}
