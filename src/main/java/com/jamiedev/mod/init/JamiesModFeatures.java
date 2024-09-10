package com.jamiedev.mod.init;
import com.jamiedev.mod.worldgen.structure.AncientForestVegetationFeature;
import com.jamiedev.mod.worldgen.structure.AncientForestVegetationFeatureConfig;
import com.jamiedev.mod.worldgen.structure.AncientTreeFeature;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.feature.*;

import com.jamiedev.mod.*;

public class JamiesModFeatures
{
    public static final Feature<DefaultFeatureConfig> ANCIENT_TREE = register("ancient_tree", new AncientTreeFeature(DefaultFeatureConfig.CODEC));


    public static final Feature<AncientForestVegetationFeatureConfig> ANCIENT_FOREST_VEGATATION = register("ancient_forest_vegetation", new AncientForestVegetationFeature(AncientForestVegetationFeatureConfig.VEGETATION_CODEC));

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registries.FEATURE, JamiesMod.getModId(name), feature);
    }

    public static void init() {
        Registry.register(Registries.FEATURE, JamiesMod.getModId("ancient_tree"), ANCIENT_TREE);
        Registry.register(Registries.FEATURE, JamiesMod.getModId("ancient_forest_vegetation"), ANCIENT_FOREST_VEGATATION);
    }
}

