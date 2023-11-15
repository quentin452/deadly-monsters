package com.dmonsters.main;

import com.dmonsters.model.*;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.*;
import com.dmonsters.projectile.EntityDagon;
import com.dmonsters.projectile.EntityLuckyEgg;
import com.dmonsters.render.*;

public class ModEntities
{
    public static void init()
    {
        int id = 1;
        ResourceLocation resLocation;
        //Mutant Steve
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":mutant_steve");
        EntityRegistry.registerModEntity(EntityMutantSteve.class, "mutant_steve", id++, DeadlyMonsters.instance, 64, 3,true);
        if (!ModConfig.mutantSteveDisabled)
            EntityRegistry.addSpawn(EntityMutantSteve.class, ModConfig.mutantSteveSpawnRate, 1, 1, EnumCreatureType.monster, BiomesProvider.getCommonBiomes());

        //Freezer
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":freezer");
        EntityRegistry.registerModEntity(EntityFreezer.class, "freezer", id++, DeadlyMonsters.instance, 64, 3,true);
        if (!ModConfig.freezerDisabled)
            EntityRegistry.addSpawn(EntityFreezer.class, ModConfig.freezerSpawnRate, 1, 1, EnumCreatureType.monster, BiomesProvider.getCommonBiomes());

        //Climber
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":climber");
        EntityRegistry.registerModEntity(EntityClimber.class, "climber", id++, DeadlyMonsters.instance, 64, 3,true);
        if (!ModConfig.climberDisabled)
            EntityRegistry.addSpawn(EntityClimber.class, ModConfig.climberSpawnRate, 1, 5, EnumCreatureType.monster, BiomesProvider.getCommonBiomes());

        //Zombie Chicken
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":zombie_chicken");
        EntityRegistry.registerModEntity(EntityZombieChicken.class, "zombie_chicken", id++, DeadlyMonsters.instance, 64,3,true);
        if (!ModConfig.zombieChickenDisabled)
            EntityRegistry.addSpawn(EntityZombieChicken.class, ModConfig.zombieChickenSpawnRate, 1, 8, EnumCreatureType.monster, BiomesProvider.getCommonBiomes());

        //Baby
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":unborn_baby");
        EntityRegistry.registerModEntity(EntityUnbornBaby.class, "unborn_baby", id++, DeadlyMonsters.instance, 64, 3,true);
        if (!ModConfig.babyDisabled)
            EntityRegistry.addSpawn(EntityUnbornBaby.class, ModConfig.babySpawnRate, 1, 8, EnumCreatureType.monster, BiomesProvider.getCommonBiomes());

        //Wideman
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":fallen_leader");
        EntityRegistry.registerModEntity(EntityFallenLeader.class, "fallen_leader", id++, DeadlyMonsters.instance, 64,3, true);
        if (!ModConfig.fallenLeaderDisabled)
            EntityRegistry.addSpawn(EntityFallenLeader.class, ModConfig.fallenLeaderSpawnRate, 1, 8, EnumCreatureType.monster, BiomesProvider.getCommonBiomes());

        //Woman
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":bloody_maiden");
        EntityRegistry.registerModEntity(EntityBloodyMaiden.class, "bloody_maiden", id++, DeadlyMonsters.instance, 64,3, true);
        if (!ModConfig.bloodyMaidenDisabled)
            EntityRegistry.addSpawn(EntityBloodyMaiden.class, ModConfig.bloodyMaidenSpawnRate, 1, 8, EnumCreatureType.monster, BiomesProvider.getCommonBiomes());

        //Entrail
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":entrail");
        EntityRegistry.registerModEntity(EntityEntrail.class, "entrail", id++, DeadlyMonsters.instance, 64, 3,true);
        if (!ModConfig.entrailDisabled)
            EntityRegistry.addSpawn(EntityEntrail.class, ModConfig.entrailSpawnRate, 1, 8, EnumCreatureType.monster, BiomesProvider.getCommonBiomes());

        //Present
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":present");
        EntityRegistry.registerModEntity(EntityPresent.class, "present", id++, DeadlyMonsters.instance, 64, 3,true);
        if (!ModConfig.presentDisabled)
            EntityRegistry.addSpawn(EntityPresent.class, ModConfig.presentSpawnRate, 1, 8, EnumCreatureType.monster, BiomesProvider.getSnowBiomes());

        //Stranger
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":stranger");
        EntityRegistry.registerModEntity(EntityStranger.class, "stranger", id++, DeadlyMonsters.instance, 64, 3,true);
        if (!ModConfig.strangerDisabled)
            EntityRegistry.addSpawn(EntityStranger.class, ModConfig.strangerSpawnRate, 1, 8, EnumCreatureType.monster, BiomesProvider.getCommonBiomes());

        //Haunted Cow
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":haunted_cow");
        EntityRegistry.registerModEntity(EntityHauntedCow.class, "haunted_cow", id++, DeadlyMonsters.instance, 64, 3,true);
        if (!ModConfig.hauntedCowDisabled)
            EntityRegistry.addSpawn(EntityHauntedCow.class, ModConfig.hauntedCowSpawnRate, 1, 8, EnumCreatureType.monster, BiomesProvider.getCommonBiomes());

        //Topielec
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":topielec");
        EntityRegistry.registerModEntity(EntityTopielec.class, "topielec", id++, DeadlyMonsters.instance, 64, 3,true);
        if (!ModConfig.topielecDisabled)
            EntityRegistry.addSpawn(EntityTopielec.class, ModConfig.topielecSpawnRate, 1, 1, EnumCreatureType.monster, BiomesProvider.getWaterBiomes());

        //Lucky Egg
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":lucky_egg");
        EntityRegistry.registerModEntity(EntityLuckyEgg.class, "lucky_egg", id++, DeadlyMonsters.instance, 64,3, true);

        //Dagon
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":dagon");
        EntityRegistry.registerModEntity(EntityDagon.class, "dagon", id++, DeadlyMonsters.instance, 64,3, true);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityMutantSteve.class, new RenderMutantSteve(new ModelMutantSteve(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityFreezer.class,new RenderFreezer(new ModelFreezer(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityClimber.class,new RenderClimber(new ModelClimber(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityZombieChicken.class,new RenderZombieChicken(new ModelZombieChicken(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityUnbornBaby.class,new RenderUnbornBaby(new ModelUnbornBaby(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityFallenLeader.class,new RenderFallenLeader(new ModelFallenLeader(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityBloodyMaiden.class,new RenderBloodyMaiden(new ModelBloodyMaiden(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityEntrail.class,new RenderEntrail(new ModelEntrail(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityPresent.class, new RenderPresent(new ModelPresent(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityStranger.class,new RenderStranger(new ModelStranger(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityHauntedCow.class, new RenderHauntedCow(new ModelHauntedCow(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityTopielec.class, new RenderTopielec(new ModelTopielec(), 0.5f));
    }

    @SideOnly(Side.CLIENT)
    public static void initHackModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityLuckyEgg.class, new RenderSnowball(ModItems.lucky_egg, 1));
        RenderingRegistry.registerEntityRenderingHandler(EntityDagon.class, new RenderSnowball(ModItems.flying_dagon, 1));
    }
}
