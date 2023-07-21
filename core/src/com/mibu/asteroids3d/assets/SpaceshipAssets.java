package com.mibu.asteroids3d.assets;

import java.util.Arrays;
import java.util.List;

public class SpaceshipAssets {
    public static final String naveNeghvar = "naves/NeghVarclass/neghvar.obj";
    public static final String naveDefiant = "naves/Defiant/defiant.obj";
    public static final String naveConstellation = "naves/Constellation/Constellation.obj";
    public static final String navePrometheus = "naves/PrometheusNX59650/prometheus.obj";
    public static final String naveSaberncc61947 = "assets/naves/SaberClass/saberncc61947.obj";//this
    public static final String naveVoyager = "naves/VoyagerNCC74656/voyager.obj";
    public static final String naveTridentA10 = "naves/space-fighter-trident/obj/Trident-A10.obj";

    private static String currentAsset = naveSaberncc61947;

    public static String getDefault() {
        return naveSaberncc61947;
    }

    public static List<String> getList() {

        return Arrays.asList(
                naveNeghvar,
                naveDefiant,
                naveConstellation,
                navePrometheus,
                naveSaberncc61947,
                naveVoyager);
    }

    public static String switchSpaceshipAsset() {
        int i = getList().indexOf(currentAsset) + 1;
        if (i == getList().size()) {
            i = 0;
        }

        String currentAsset = getList().get(i);
        return currentAsset;
    }
}
