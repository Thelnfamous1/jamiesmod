package com.jamiedev.mod.blocks;

import com.jamiedev.mod.init.JamiesModBlocks;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

public class JamiesModStrippableBlocks
{
    public static void registerStrippables() {
        StrippableBlockRegistry.register(JamiesModBlocks.ANCIENT_LOG, JamiesModBlocks.STRIPPED_ANCIENT_LOG);
    }
}
