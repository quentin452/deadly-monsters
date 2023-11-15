package com.dmonsters.main;

import cpw.mods.fml.common.event.FMLInitializationEvent;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.item.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

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

    public static class RegistrationHandler
    {
        public static void register(FMLInitializationEvent e) {
            registerItems();
        }
        public static void registerItems()
        {
            init();
            register(rebar, "rebar");
            register(lucky_egg, "lucky egg");
            register(unborn_baby_eye, "unborn baby eye");
            register(bloody_maiden_heart, "blood maiden heart");
            register(fallen_leader_spine, "fallen leader spine");
            register(entrail_flesh, "entrail flesh");
            register(poopoo_pill, "poopoo pill");
            register(dagon, "dagon");
            register(flying_dagon, "flying dragon");
            register(sunlight_drop, "sunlight drop");
            register(mod_item, "mod item");
            register(mob_spawner_item_unborn_baby, "mob spawner item unborn baby");
            register(mob_spawner_item_climber, "mob spawner item climber");
            register(mob_spawner_item_entrail, "mob spawner item entrail");
            register(mob_spawner_item_freezer, "mob spawner item freezer");
            register(mob_spawner_item_mutant_steve, "mob spawner item mutant steve");
            register(mob_spawner_item_fallen_leader, "mob spawner item fallen leader");
            register(mob_spawner_item_bloody_maiden, "mob spawner item bloody maiden");
            register(mob_spawner_item_zombie_chicken, "mob spawner item zombie chicken");
            register(mob_spawner_item_present, "mob spawner item present");
            register(mob_spawner_item_stranger, "mob spawner item stranger");
            register(mob_spawner_item_haunted_cow, "mob spawner item haunted cow");
            register(mob_spawner_item_topielec, "mob spawner item topielec");
            register(harpoon_stone, "harpoon stone");
            register(harpoon_iron, "harpoon iron");
            register(harpoon_diamond, "harpoon diamond");
            register(harpoon_obsidian, "harpoon obsidian");
        }
        private static void register(Item item, String name) {
            GameRegistry.registerItem(item, name);
        }
    }
}
