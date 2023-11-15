package com.dmonsters.main;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.ResourceLocation;
import com.dmonsters.DeadlyMonsters;
import net.minecraftforge.client.event.sound.SoundEvent;

public class ModSounds
{
    public static SoundEvent MUTANT_DEATH;
    public static SoundEvent MUTANT_HURT;
    public static SoundEvent MUTANT_AMBIENT;
    public static SoundEvent MUTANT_ATTACK;
    public static SoundEvent FREEZER_DEATH;
    public static SoundEvent FREEZER_HURT;
    public static SoundEvent FREEZER_AMBIENT;
    public static SoundEvent FREEZER_ATTACK;
    public static SoundEvent CLIMBER_DEATH;
    public static SoundEvent CLIMBER_HURT;
    public static SoundEvent CLIMBER_AMBIENT;
    public static SoundEvent CLIMBER_ATTACK;
    public static SoundEvent BABY_DEATH;
    public static SoundEvent BABY_HURT;
    public static SoundEvent BABY_AMBIENT;
    public static SoundEvent BABY_ATTACK;
    public static SoundEvent WIDEMAN_DEATH;
    public static SoundEvent WIDEMAN_HURT;
    public static SoundEvent WIDEMAN_AMBIENT;
    public static SoundEvent WIDEMAN_ATTACK;
    public static SoundEvent MAIDEN_DEATH;
    public static SoundEvent MAIDEN_HURT;
    public static SoundEvent MAIDEN_AMBIENT;
    public static SoundEvent MAIDEN_ATTACK;
    public static SoundEvent ENTRAIL_DEATH;
    public static SoundEvent ENTRAIL_HURT;
    public static SoundEvent ENTRAIL_AMBIENT;
    public static SoundEvent ENTRAIL_ATTACK;
    public static SoundEvent PRESENT_DEATH;
    public static SoundEvent PRESENT_HURT;
    public static SoundEvent PRESENT_AMBIENT;
    public static SoundEvent PRESENT_ATTACK;
    public static SoundEvent STRANGER_DEATH;
    public static SoundEvent STRANGER_HURT;
    public static SoundEvent STRANGER_AMBIENT;
    public static SoundEvent STRANGER_ATTACK;
    public static SoundEvent STRANGER_IMPACT;
    public static SoundEvent HAUNTEDCOW_STEP;
    public static SoundEvent HAUNTEDCOW_HURT;
    public static SoundEvent HAUNTEDCOW_AMBIENT;
    public static SoundEvent HAUNTEDCOW_DEATH;
    public static SoundEvent TOPIELEC_HURT;
    public static SoundEvent TOPIELEC_AMBIENT;
    public static SoundEvent TOPIELEC_DEATH;

    public static SoundEvent DUMP_MAKE;
    public static SoundEvent BLOCK_SOULEYE_KILL;
    public static SoundEvent SUNLIGHTDROP_USE;
    public static SoundEvent HAUNTEDCOW_TIMECHANGE;

    public static void init()
    {
        DUMP_MAKE = registerSound("block.dump.make");
        BLOCK_SOULEYE_KILL = registerSound("block.souleye.kill");
        STRANGER_IMPACT = registerSound("mob.stranger.impact");

        SUNLIGHTDROP_USE = registerSound("item.sunlightdrop.use");
        HAUNTEDCOW_TIMECHANGE = registerSound("mob.hauntedcow.timechange");

        TOPIELEC_HURT = registerSound("mob.topielec.hurt");
        TOPIELEC_AMBIENT = registerSound("mob.topielec.ambient");
        TOPIELEC_DEATH = registerSound("mob.topielec.death");

        HAUNTEDCOW_DEATH = registerSound("mob.hauntedcow.death");
        HAUNTEDCOW_HURT = registerSound("mob.hauntedcow.hurt");
        HAUNTEDCOW_AMBIENT = registerSound("mob.hauntedcow.ambient");
        HAUNTEDCOW_STEP = registerSound("mob.hauntedcow.step");

        MUTANT_DEATH = registerSound("mob.mutant.death");
        MUTANT_HURT = registerSound("mob.mutant.hurt");
        MUTANT_AMBIENT = registerSound("mob.mutant.idle");
        MUTANT_ATTACK = registerSound("mob.mutant.attack");

        FREEZER_DEATH = registerSound("mob.freezer.death");
        FREEZER_HURT = registerSound("mob.freezer.hurt");
        FREEZER_AMBIENT = registerSound("mob.freezer.idle");
        FREEZER_ATTACK = registerSound("mob.freezer.attack");

        CLIMBER_DEATH = registerSound("mob.climber.death");
        CLIMBER_HURT = registerSound("mob.climber.hurt");
        CLIMBER_AMBIENT = registerSound("mob.climber.idle");
        CLIMBER_ATTACK = registerSound("mob.climber.attack");

        BABY_DEATH = registerSound("mob.baby.death");
        BABY_HURT = registerSound("mob.baby.hurt");
        BABY_AMBIENT = registerSound("mob.baby.idle");
        BABY_ATTACK = registerSound("mob.baby.attack");

        WIDEMAN_DEATH = registerSound("mob.wideman.death");
        WIDEMAN_HURT = registerSound("mob.wideman.hurt");
        WIDEMAN_AMBIENT = registerSound("mob.wideman.idle");
        WIDEMAN_ATTACK = registerSound("mob.wideman.attack");

        MAIDEN_DEATH = registerSound("mob.maiden.death");
        MAIDEN_HURT = registerSound("mob.maiden.hurt");
        MAIDEN_AMBIENT = registerSound("mob.maiden.idle");
        MAIDEN_ATTACK = registerSound("mob.maiden.attack");

        ENTRAIL_DEATH = registerSound("mob.entrail.death");
        ENTRAIL_HURT = registerSound("mob.entrail.hurt");
        ENTRAIL_AMBIENT = registerSound("mob.entrail.idle");
        ENTRAIL_ATTACK = registerSound("mob.entrail.attack");

        PRESENT_DEATH = registerSound("mob.present.death");
        PRESENT_HURT = registerSound("mob.present.hurt");
        PRESENT_AMBIENT = registerSound("mob.present.idle");
        PRESENT_ATTACK = registerSound("mob.present.attack");

        STRANGER_DEATH = registerSound("mob.stranger.death");
        STRANGER_HURT = registerSound("mob.stranger.hurt");
        STRANGER_AMBIENT = registerSound("mob.stranger.idle");
        STRANGER_ATTACK = registerSound("mob.stranger.attack");

        STRANGER_DEATH = registerSound("mob.stranger.death");
        STRANGER_HURT = registerSound("mob.stranger.hurt");
        STRANGER_AMBIENT = registerSound("mob.stranger.idle");
        STRANGER_ATTACK = registerSound("mob.stranger.attack");
    }

    private static SoundEvent registerSound(String id)
    {
      /*  ResourceLocation soundID = new ResourceLocation(DeadlyMonsters.MOD_ID, id);
        return new SoundEvent(soundID).setRegistryName(soundID);

       */
        return null;
    }

    public static class RegistrationHandler
    {
     /*   public static void registerSound(FMLInitializationEvent e) {
            registerSound();
        }
        public static void registerSound()
        {
            event.getRegistry().registerAll(
                DUMP_MAKE,
                BLOCK_SOULEYE_KILL,
                STRANGER_IMPACT,

                SUNLIGHTDROP_USE,
                HAUNTEDCOW_TIMECHANGE,

                TOPIELEC_HURT,
                TOPIELEC_AMBIENT,
                TOPIELEC_DEATH,

                HAUNTEDCOW_DEATH,
                HAUNTEDCOW_HURT,
                HAUNTEDCOW_AMBIENT,
                HAUNTEDCOW_STEP,

                MUTANT_DEATH,
                MUTANT_HURT,
                MUTANT_AMBIENT,
                MUTANT_ATTACK,

                FREEZER_DEATH,
                FREEZER_HURT,
                FREEZER_AMBIENT,
                FREEZER_ATTACK,

                CLIMBER_DEATH,
                CLIMBER_HURT,
                CLIMBER_AMBIENT,
                CLIMBER_ATTACK,

                BABY_DEATH,
                BABY_HURT,
                BABY_AMBIENT,
                BABY_ATTACK,

                WIDEMAN_DEATH,
                WIDEMAN_HURT,
                WIDEMAN_AMBIENT,
                WIDEMAN_ATTACK,

                MAIDEN_DEATH,
                MAIDEN_HURT,
                MAIDEN_AMBIENT,
                MAIDEN_ATTACK,

                ENTRAIL_DEATH,
                ENTRAIL_HURT,
                ENTRAIL_AMBIENT,
                ENTRAIL_ATTACK,

                PRESENT_DEATH,
                PRESENT_HURT,
                PRESENT_AMBIENT,
                PRESENT_ATTACK,

                STRANGER_DEATH,
                STRANGER_HURT,
                STRANGER_AMBIENT,
                STRANGER_ATTACK
            );
        }

      */
    }
}
