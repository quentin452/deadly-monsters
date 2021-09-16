package com.dmonsters.main;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import com.dmonsters.item.*;

public class ModItems
{
    public static Rebar rebar;
    public static LuckyEgg lucky_egg;
    public static UnbornBabyEye unborn_baby_eye;
    public static BloodyMaidenHeart bloody_maiden_heart;
    public static FallenLeaderSpine fallen_leader_spine;
    public static EntrailFlesh entrail_flesh;
    public static PoopooPill poopoo_pill;
    public static Dagon dagon;
    public static FlyingDagon flying_dagon;
    public static SunlightDrop sunlight_drop;
    public static ModItem mod_item;
    public static MobSpawnerItem mob_spawner_item_unborn_baby;
    public static MobSpawnerItem mob_spawner_item_climber;
    public static MobSpawnerItem mob_spawner_item_entrail;
    public static MobSpawnerItem mob_spawner_item_freezer;
    public static MobSpawnerItem mob_spawner_item_mutant_steve;
    public static MobSpawnerItem mob_spawner_item_fallen_leader;
    public static MobSpawnerItem mob_spawner_item_bloody_maiden;
    public static MobSpawnerItem mob_spawner_item_zombie_chicken;
    public static MobSpawnerItem mob_spawner_item_present;
    public static MobSpawnerItem mob_spawner_item_stranger;
    public static MobSpawnerItem mob_spawner_item_haunted_cow;
    public static MobSpawnerItem mob_spawner_item_topielec;
    public static Harpoon harpoon_stone;
    public static Harpoon harpoon_iron;
    public static Harpoon harpoon_diamond;
    public static Harpoon harpoon_obsidian;

    public static void init()
    {
        rebar = new Rebar();
        lucky_egg = new LuckyEgg();
        unborn_baby_eye = new UnbornBabyEye();
        bloody_maiden_heart = new BloodyMaidenHeart();
        fallen_leader_spine = new FallenLeaderSpine();
        entrail_flesh = new EntrailFlesh();
        poopoo_pill = new PoopooPill();
        dagon = new Dagon();
        flying_dagon = new FlyingDagon();
        sunlight_drop = new SunlightDrop();
        mod_item = new ModItem();
        mob_spawner_item_unborn_baby = new MobSpawnerItem("unborn_baby");
        mob_spawner_item_climber = new MobSpawnerItem("climber");
        mob_spawner_item_entrail = new MobSpawnerItem("entrail");
        mob_spawner_item_freezer = new MobSpawnerItem("freezer");
        mob_spawner_item_mutant_steve = new MobSpawnerItem("mutant_steve");
        mob_spawner_item_fallen_leader = new MobSpawnerItem("fallen_leader");
        mob_spawner_item_bloody_maiden = new MobSpawnerItem("bloody_maiden");
        mob_spawner_item_zombie_chicken = new MobSpawnerItem("zombie_chicken");
        mob_spawner_item_present = new MobSpawnerItem("present");
        mob_spawner_item_stranger = new MobSpawnerItem("stranger");
        mob_spawner_item_haunted_cow = new MobSpawnerItem("haunted_cow");
        mob_spawner_item_topielec = new MobSpawnerItem("topielec");
        harpoon_stone = new Harpoon("stone", 10, 3);
        harpoon_iron = new Harpoon("iron", 40, 6);
        harpoon_diamond = new Harpoon("diamond", 160, 10);
        harpoon_obsidian = new Harpoon("obsidian", 80, 6);
    }

    @Mod.EventBusSubscriber(modid = MainMod.MODID)
    public static class RegistrationHandler
    {
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event)
        {
            final IForgeRegistry<Item> registry = event.getRegistry();
            init();
            registry.register(rebar);
            registry.register(lucky_egg);
            registry.register(unborn_baby_eye);
            registry.register(bloody_maiden_heart);
            registry.register(fallen_leader_spine);
            registry.register(entrail_flesh);
            registry.register(poopoo_pill);
            registry.register(dagon);
            registry.register(flying_dagon);
            registry.register(sunlight_drop);
            registry.register(mod_item);
            registry.register(mob_spawner_item_unborn_baby);
            registry.register(mob_spawner_item_climber);
            registry.register(mob_spawner_item_entrail);
            registry.register(mob_spawner_item_freezer);
            registry.register(mob_spawner_item_mutant_steve);
            registry.register(mob_spawner_item_fallen_leader);
            registry.register(mob_spawner_item_bloody_maiden);
            registry.register(mob_spawner_item_zombie_chicken);
            registry.register(mob_spawner_item_present);
            registry.register(mob_spawner_item_stranger);
            registry.register(mob_spawner_item_haunted_cow);
            registry.register(mob_spawner_item_topielec);
            registry.register(harpoon_stone);
            registry.register(harpoon_iron);
            registry.register(harpoon_diamond);
            registry.register(harpoon_obsidian);
        }
    }
}