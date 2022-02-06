package com.dmonsters.main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        EntityRegistry.registerModEntity(resLocation, EntityMutantSteve.class, "mutant_steve", id++, DeadlyMonsters.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_MUTANT_STEVE.mutantSteveDisabled)
            EntityRegistry.addSpawn(EntityMutantSteve.class, ModConfig.CATEGORY_MUTANT_STEVE.mutantSteveSpawnRate, 1, 1, EnumCreatureType.MONSTER, BiomesProvider.getCommonBiomes());
        LootTableList.register(EntityMutantSteve.LOOT);

        //Freezer
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":freezer");
        EntityRegistry.registerModEntity(resLocation, EntityFreezer.class, "freezer", id++, DeadlyMonsters.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_FREEZER.freezerDisabled)
            EntityRegistry.addSpawn(EntityFreezer.class, ModConfig.CATEGORY_FREEZER.freezerSpawnRate, 1, 1, EnumCreatureType.MONSTER, BiomesProvider.getCommonBiomes());
        LootTableList.register(EntityFreezer.LOOT);

        //Climber
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":climber");
        EntityRegistry.registerModEntity(resLocation, EntityClimber.class, "climber", id++, DeadlyMonsters.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_CLIMBER.climberDisabled)
            EntityRegistry.addSpawn(EntityClimber.class, ModConfig.CATEGORY_CLIMBER.climberSpawnRate, 1, 5, EnumCreatureType.MONSTER, BiomesProvider.getCommonBiomes());
        LootTableList.register(EntityClimber.LOOT);

        //Zombie Chicken
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":zombie_chicken");
        EntityRegistry.registerModEntity(resLocation, EntityZombieChicken.class, "zombie_chicken", id++, DeadlyMonsters.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_ZOMBIE_CHICKEN.zombieChickenDisabled)
            EntityRegistry.addSpawn(EntityZombieChicken.class, ModConfig.CATEGORY_ZOMBIE_CHICKEN.zombieChickenSpawnRate, 1, 8, EnumCreatureType.MONSTER, BiomesProvider.getCommonBiomes());
        LootTableList.register(EntityZombieChicken.LOOT);

        //Baby
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":unborn_baby");
        EntityRegistry.registerModEntity(resLocation, EntityUnbornBaby.class, "unborn_baby", id++, DeadlyMonsters.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_UNBORN_BABY.babyDisabled)
            EntityRegistry.addSpawn(EntityUnbornBaby.class, ModConfig.CATEGORY_UNBORN_BABY.babySpawnRate, 1, 8, EnumCreatureType.MONSTER, BiomesProvider.getCommonBiomes());
        LootTableList.register(EntityUnbornBaby.LOOT);

        //Wideman
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":fallen_leader");
        EntityRegistry.registerModEntity(resLocation, EntityFallenLeader.class, "fallen_leader", id++, DeadlyMonsters.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_FALLEN_LEADER.fallenLeaderDisabled)
            EntityRegistry.addSpawn(EntityFallenLeader.class, ModConfig.CATEGORY_FALLEN_LEADER.fallenLeaderSpawnRate, 1, 8, EnumCreatureType.MONSTER, BiomesProvider.getCommonBiomes());
        LootTableList.register(EntityFallenLeader.LOOT);

        //Woman
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":bloody_maiden");
        EntityRegistry.registerModEntity(resLocation, EntityBloodyMaiden.class, "bloody_maiden", id++, DeadlyMonsters.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_BLOODY_MAIDEN.bloodyMaidenDisabled)
            EntityRegistry.addSpawn(EntityBloodyMaiden.class, ModConfig.CATEGORY_BLOODY_MAIDEN.bloodyMaidenSpawnRate, 1, 8, EnumCreatureType.MONSTER, BiomesProvider.getCommonBiomes());
        LootTableList.register(EntityBloodyMaiden.LOOT);

        //Entrail
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":entrail");
        EntityRegistry.registerModEntity(resLocation, EntityEntrail.class, "entrail", id++, DeadlyMonsters.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_ENTRAIL.entrailDisabled)
            EntityRegistry.addSpawn(EntityEntrail.class, ModConfig.CATEGORY_ENTRAIL.entrailSpawnRate, 1, 8, EnumCreatureType.MONSTER, BiomesProvider.getCommonBiomes());
        LootTableList.register(EntityEntrail.LOOT);

        //Present
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":present");
        EntityRegistry.registerModEntity(resLocation, EntityPresent.class, "present", id++, DeadlyMonsters.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_PRESENT.presentDisabled)
            EntityRegistry.addSpawn(EntityPresent.class, ModConfig.CATEGORY_PRESENT.presentSpawnRate, 1, 8, EnumCreatureType.MONSTER, BiomesProvider.getSnowBiomes());
        LootTableList.register(EntityPresent.LOOT);

        //Stranger
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":stranger");
        EntityRegistry.registerModEntity(resLocation, EntityStranger.class, "stranger", id++, DeadlyMonsters.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_STRANGER.strangerDisabled)
            EntityRegistry.addSpawn(EntityStranger.class, ModConfig.CATEGORY_STRANGER.strangerSpawnRate, 1, 8, EnumCreatureType.MONSTER, BiomesProvider.getCommonBiomes());
        LootTableList.register(EntityStranger.LOOT);

        //Haunted Cow
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":haunted_cow");
        EntityRegistry.registerModEntity(resLocation, EntityHauntedCow.class, "haunted_cow", id++, DeadlyMonsters.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_HAUNTED_COW.hauntedCowDisabled)
            EntityRegistry.addSpawn(EntityHauntedCow.class, ModConfig.CATEGORY_HAUNTED_COW.hauntedCowSpawnRate, 1, 8, EnumCreatureType.MONSTER, BiomesProvider.getCommonBiomes());
        LootTableList.register(EntityHauntedCow.LOOT);

        //Topielec
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":topielec");
        EntityRegistry.registerModEntity(resLocation, EntityTopielec.class, "topielec", id++, DeadlyMonsters.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_TOPIELEC.topielecDisabled)
            EntityRegistry.addSpawn(EntityTopielec.class, ModConfig.CATEGORY_TOPIELEC.topielecSpawnRate, 1, 1, EnumCreatureType.MONSTER, BiomesProvider.getWaterBiomes());
        LootTableList.register(EntityTopielec.LOOT);

        //Lucky Egg
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":lucky_egg");
        EntityRegistry.registerModEntity(resLocation, EntityLuckyEgg.class, "lucky_egg", id++, DeadlyMonsters.instance, 64, 3, true);

        //Dagon
        resLocation = new ResourceLocation(DeadlyMonsters.MOD_ID + ":dagon");
        EntityRegistry.registerModEntity(resLocation, EntityDagon.class, "dagon", id++, DeadlyMonsters.instance, 64, 3, true);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityMutantSteve.class, RenderMutantSteve.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityFreezer.class, RenderFreezer.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityClimber.class, RenderClimber.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityZombieChicken.class, RenderZombieChicken.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityUnbornBaby.class, RenderUnbornBaby.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityFallenLeader.class, RenderFallenLeader.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityBloodyMaiden.class, RenderBloodyMaiden.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityEntrail.class, RenderEntrail.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPresent.class, RenderPresent.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityStranger.class, RenderStranger.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityHauntedCow.class, RenderHauntedCow.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTopielec.class, RenderTopielec.FACTORY);
    }

    @SideOnly(Side.CLIENT)
    public static void initHackModels()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityLuckyEgg.class, new RenderSnowball<EntityLuckyEgg>(Minecraft.getMinecraft().getRenderManager(), ModItems.lucky_egg, Minecraft.getMinecraft().getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(EntityDagon.class, new RenderSnowball<EntityDagon>(Minecraft.getMinecraft().getRenderManager(), ModItems.flying_dagon, Minecraft.getMinecraft().getRenderItem()));
    }
}