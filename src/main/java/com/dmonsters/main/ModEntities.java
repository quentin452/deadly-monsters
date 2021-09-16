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

import com.dmonsters.entity.*;
import com.dmonsters.entityProjectile.EntityDagon;
import com.dmonsters.entityProjectile.EntityLuckyEgg;
import com.dmonsters.render.*;

public class ModEntities
{
    public static void init()
    {
        int id = 1;
        ResourceLocation resLocation;
        //Mutant Steve
        resLocation = new ResourceLocation(MainMod.MODID + ":mutant_steve");
        EntityRegistry.registerModEntity(resLocation, EntityMutantSteve.class, "mutant_steve", id++, MainMod.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_MUTANT_STEVE.mutantSteveDisabled)
            EntityRegistry.addSpawn(EntityMutantSteve.class, ModConfig.CATEGORY_MUTANT_STEVE.mutantSteveSpawnRate, 1, 1, EnumCreatureType.MONSTER, BiomesProvider.getBiomes());
        LootTableList.register(EntityMutantSteve.LOOT);

        //Freezer
        resLocation = new ResourceLocation(MainMod.MODID + ":freezer");
        EntityRegistry.registerModEntity(resLocation, EntityFreezer.class, "freezer", id++, MainMod.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_FREEZER.freezerDisabled)
            EntityRegistry.addSpawn(EntityFreezer.class, ModConfig.CATEGORY_FREEZER.freezerSpawnRate, 1, 1, EnumCreatureType.MONSTER, BiomesProvider.getBiomes());
        LootTableList.register(EntityFreezer.LOOT);

        //Climber
        resLocation = new ResourceLocation(MainMod.MODID + ":climber");
        EntityRegistry.registerModEntity(resLocation, EntityClimber.class, "climber", id++, MainMod.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_CLIMBER.climberDisabled)
            EntityRegistry.addSpawn(EntityClimber.class, ModConfig.CATEGORY_CLIMBER.climberSpawnRate, 1, 5, EnumCreatureType.MONSTER, BiomesProvider.getBiomes());
        LootTableList.register(EntityClimber.LOOT);

        //Zombie Chicken
        resLocation = new ResourceLocation(MainMod.MODID + ":zombie_chicken");
        EntityRegistry.registerModEntity(resLocation, EntityZombieChicken.class, "zombie_chicken", id++, MainMod.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_ZOMBIE_CHICKEN.zombieChickenDisabled)
            EntityRegistry.addSpawn(EntityZombieChicken.class, ModConfig.CATEGORY_ZOMBIE_CHICKEN.zombieChickenSpawnRate, 1, 8, EnumCreatureType.MONSTER, BiomesProvider.getBiomes());
        LootTableList.register(EntityZombieChicken.LOOT);

        //Baby
        resLocation = new ResourceLocation(MainMod.MODID + ":unborn_baby");
        EntityRegistry.registerModEntity(resLocation, EntityUnbornBaby.class, "unborn_baby", id++, MainMod.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_UNBORN_BABY.babyDisabled)
            EntityRegistry.addSpawn(EntityUnbornBaby.class, ModConfig.CATEGORY_UNBORN_BABY.babySpawnRate, 1, 8, EnumCreatureType.MONSTER, BiomesProvider.getBiomes());
        LootTableList.register(EntityUnbornBaby.LOOT);

        //Wideman
        resLocation = new ResourceLocation(MainMod.MODID + ":fallen_leader");
        EntityRegistry.registerModEntity(resLocation, EntityFallenLeader.class, "fallen_leader", id++, MainMod.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_FALLEN_LEADER.fallenLeaderDisabled)
            EntityRegistry.addSpawn(EntityFallenLeader.class, ModConfig.CATEGORY_FALLEN_LEADER.fallenLeaderSpawnRate, 1, 8, EnumCreatureType.MONSTER, BiomesProvider.getBiomes());
        LootTableList.register(EntityFallenLeader.LOOT);

        //Woman
        resLocation = new ResourceLocation(MainMod.MODID + ":bloody_maiden");
        EntityRegistry.registerModEntity(resLocation, EntityBloodyMaiden.class, "bloody_maiden", id++, MainMod.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_BLOODY_MAIDEN.bloodyMaidenDisabled)
            EntityRegistry.addSpawn(EntityBloodyMaiden.class, ModConfig.CATEGORY_BLOODY_MAIDEN.bloodyMaidenSpawnRate, 1, 8, EnumCreatureType.MONSTER, BiomesProvider.getBiomes());
        LootTableList.register(EntityBloodyMaiden.LOOT);

        //Entrail
        resLocation = new ResourceLocation(MainMod.MODID + ":entrail");
        EntityRegistry.registerModEntity(resLocation, EntityEntrail.class, "entrail", id++, MainMod.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_ENTRAIL.entrailDisabled)
            EntityRegistry.addSpawn(EntityEntrail.class, ModConfig.CATEGORY_ENTRAIL.entrailSpawnRate, 1, 8, EnumCreatureType.MONSTER, BiomesProvider.getBiomes());
        LootTableList.register(EntityEntrail.LOOT);

        //Present
        resLocation = new ResourceLocation(MainMod.MODID + ":present");
        EntityRegistry.registerModEntity(resLocation, EntityPresent.class, "present", id++, MainMod.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_PRESENT.presentDisabled)
            EntityRegistry.addSpawn(EntityPresent.class, ModConfig.CATEGORY_PRESENT.presentSpawnRate, 1, 8, EnumCreatureType.MONSTER, BiomesProvider.getSnowBiomes());
        LootTableList.register(EntityPresent.LOOT);

        //Stranger
        resLocation = new ResourceLocation(MainMod.MODID + ":stranger");
        EntityRegistry.registerModEntity(resLocation, EntityStranger.class, "stranger", id++, MainMod.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_STRANGER.strangerDisabled)
            EntityRegistry.addSpawn(EntityStranger.class, ModConfig.CATEGORY_STRANGER.strangerSpawnRate, 1, 8, EnumCreatureType.MONSTER, BiomesProvider.getBiomes());
        LootTableList.register(EntityStranger.LOOT);

        //Haunted Cow
        resLocation = new ResourceLocation(MainMod.MODID + ":haunted_cow");
        EntityRegistry.registerModEntity(resLocation, EntityHauntedCow.class, "haunted_cow", id++, MainMod.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_HAUNTED_COW.hauntedCowDisabled)
            EntityRegistry.addSpawn(EntityHauntedCow.class, ModConfig.CATEGORY_HAUNTED_COW.hauntedCowSpawnRate, 1, 8, EnumCreatureType.MONSTER, BiomesProvider.getBiomes());
        LootTableList.register(EntityHauntedCow.LOOT);

        //Topielec
        resLocation = new ResourceLocation(MainMod.MODID + ":topielec");
        EntityRegistry.registerModEntity(resLocation, EntityTopielec.class, "topielec", id++, MainMod.instance, 64, 3, true);
        if (!ModConfig.CATEGORY_TOPIELEC.topielecDisabled)
            EntityRegistry.addSpawn(EntityTopielec.class, ModConfig.CATEGORY_TOPIELEC.topielecSpawnRate, 1, 1, EnumCreatureType.MONSTER, BiomesProvider.getWaterBiomes());
        LootTableList.register(EntityTopielec.LOOT);

        //Lucky Egg
        resLocation = new ResourceLocation(MainMod.MODID + ":lucky_egg");
        EntityRegistry.registerModEntity(resLocation, EntityLuckyEgg.class, "lucky_egg", id++, MainMod.instance, 64, 3, true);

        //Dagon
        resLocation = new ResourceLocation(MainMod.MODID + ":dagon");
        EntityRegistry.registerModEntity(resLocation, EntityDagon.class, "dagon", id++, MainMod.instance, 64, 3, true);
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