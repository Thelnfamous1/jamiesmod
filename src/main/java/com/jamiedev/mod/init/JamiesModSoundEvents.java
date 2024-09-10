package com.jamiedev.mod.init;

import com.jamiedev.mod.JamiesMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
public class JamiesModSoundEvents
{
    public static final SoundEvent BLOCK_JAMIESBLOCK_BREAK = register("block.jamiesblock.break");

    private static SoundEvent register(String id) {
        return Registry.register(Registries.SOUND_EVENT, JamiesMod.getModId(id), SoundEvent.of(JamiesMod.getModId(id)));
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(String id) {
        return registerReference(JamiesMod.getModId(id), JamiesMod.getModId(id));
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(Identifier id, Identifier soundId) {
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(soundId));
    }

    public static void init(){

    }
}
