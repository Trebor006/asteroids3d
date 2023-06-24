package com.mibu.asteroids3d.util;

import com.badlogic.gdx.assets.AssetManager;
import lombok.Getter;

public class AssetManagerUtil {

    @Getter
    private static AssetManager assetManager;

    public static void initAsset() {
        assetManager = new AssetManager();
    }
}
