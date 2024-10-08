package com.jamiedev.mod.client;

import com.jamiedev.mod.JamiesMod;
import com.jamiedev.mod.client.particles.RafflesiaSporeParticle;
import com.jamiedev.mod.init.*;
import com.jamiedev.mod.network.SyncPlayerHookS2C;
import com.jamiedev.mod.client.network.SyncPlayerHookPacketHandler;
import com.jamiedev.mod.util.PlayerWithHook;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import com.jamiedev.mod.client.renderer.*;
import com.jamiedev.mod.client.models.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

public class JamiesModClient implements ClientModInitializer {
    public static Identifier BYGONE = JamiesMod.getModId("bygone");
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.CLOUD, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.ANCIENT_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.ANCIENT_ROOTS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.ANCIENT_VINE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.GOURD_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.RAFFLESIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.CAVE_VINES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.CAVE_VINES_PLANT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.MONTSECHIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.SAGARIA, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.ANCIENT_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(JamiesModBlocks.ANCIENT_TRAPDOOR, RenderLayer.getCutout());

        EntityRendererRegistry.register(JamiesModEntityTypes.DUCK, DuckieRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(JamiesModModelLayers.DUCKIE, DuckieModel::getTexturedModelData);

        EntityRendererRegistry.register(JamiesModEntityTypes.HOOK, HookRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(JamiesModModelLayers.HOOK, DuckieModel::getTexturedModelData);

        ParticleFactoryRegistry.getInstance().register(JamiesModParticleTypes.RAFFLESIA_SPORES, RafflesiaSporeParticle.Factory::new);

        DimensionRenderingRegistry.registerDimensionEffects(BYGONE, BygoneDimensionEffects.INSTANCE);


        registerModelPredicateProviders();

        ClientPlayNetworking.registerGlobalReceiver(SyncPlayerHookS2C.PACkET_ID, (payload, context) -> {
            context.client().execute(() -> SyncPlayerHookPacketHandler.handle(payload, context));
        });
    }
    public static void registerModelPredicateProviders() {
        ModelPredicateProviderRegistry.register(JamiesModItems.HOOK, JamiesMod.getModId("deployed"), (itemStack, clientWorld, livingEntity, seed) -> {
            if (livingEntity instanceof PlayerEntity) {
                for (Hand value : Hand.values())
                {
                    ItemStack heldStack = livingEntity.getStackInHand(value);

                    if (heldStack == itemStack && (((PlayerWithHook)livingEntity).jamiesmod$getHook()  != null && !((PlayerWithHook)livingEntity).jamiesmod$getHook().isRemoved()))
                    {
                        return 1;
                    }
                }
            }
            if (livingEntity == null)
            {
                return 0.0F;
            }
            return 0;
        });
    }


}
