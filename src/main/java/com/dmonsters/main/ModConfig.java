package com.dmonsters.main;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = MainMod.MOD_ID, name = "DeadlyMonsters")
public class ModConfig
{
    @Comment("General")
    public static _CategoryGeneral CATEGORY_GENERAL = new _CategoryGeneral();
    @Comment("Bloody Maiden")
    public static CategoryBloodyMaiden CATEGORY_BLOODY_MAIDEN = new CategoryBloodyMaiden();
    @Comment("Climber")
    public static CategoryClimber CATEGORY_CLIMBER = new CategoryClimber();
    @Comment("Entrail")
    public static CategoryEntrail CATEGORY_ENTRAIL = new CategoryEntrail();
    @Comment("Fallen Leader")
    public static CategoryFallenLeader CATEGORY_FALLEN_LEADER = new CategoryFallenLeader();
    @Comment("Freezer")
    public static CategoryFreezer CATEGORY_FREEZER = new CategoryFreezer();
    @Comment("Haunted Cow")
    public static CategoryHauntedCow CATEGORY_HAUNTED_COW = new CategoryHauntedCow();
    @Comment("Mutant Steve")
    public static CategoryMutantSteve CATEGORY_MUTANT_STEVE = new CategoryMutantSteve();
    @Comment("Present")
    public static CategoryPresent CATEGORY_PRESENT = new CategoryPresent();
    @Comment("Stranger")
    public static CategoryStranger CATEGORY_STRANGER = new CategoryStranger();
    @Comment("Topielec")
    public static CategoryTopielec CATEGORY_TOPIELEC = new CategoryTopielec();
    @Comment("Unborn Baby")
    public static CategoryUnbornBaby CATEGORY_UNBORN_BABY = new CategoryUnbornBaby();
    @Comment("Zombie Chicken")
    public static CategoryZombieChicken CATEGORY_ZOMBIE_CHICKEN = new CategoryZombieChicken();

    public static class _CategoryGeneral
    {
        @Name("Global Health Multiplier")
        public float globalHealthMultiplier = 1.0F;
        @Name("Global Strength Multiplier")
        public float globalStrengthMultiplier = 1.0F;
        @Name("Global Speed Multiplier")
        public float globalSpeedMultiplier = 1.0F;
    }

    public static class CategoryBloodyMaiden
    {
        @Name("Health Multiplier")
        public float bloodyMaidenHealthMultiplier = 1.0F;
        @Name("Strength Multiplier")
        public float bloodyMaidenStrengthMultiplier = 1.0F;
        @Name("Speed Multiplier")
        public float bloodyMaidenSpeedMultiplier = 1.0F;
        @Name("Spawn Rate")
        public int bloodyMaidenSpawnRate = 12;
        @Name("Disabled?")
        public boolean bloodyMaidenDisabled = false;
    }

    public static class CategoryClimber
    {
        @Name("Health Multiplier")
        public float climberHealthMultiplier = 1.0F;
        @Name("Strength Multiplier")
        public float climberStrengthMultiplier = 1.0F;
        @Name("Speed Multiplier")
        public float climberSpeedMultiplier = 1.0F;
        @Name("Spawn Rate")
        public int climberSpawnRate = 8;
        @Name("Disabled?")
        public boolean climberDisabled = false;
    }

    public static class CategoryEntrail
    {
        @Name("Health Multiplier")
        public float entrailHealthMultiplier = 1.0F;
        @Name("Strength Multiplier")
        public float entrailStrengthMultiplier = 1.0F;
        @Name("Speed Multiplier")
        public float entrailSpeedMultiplier = 1.0F;
        @Name("Spawn Rate")
        public int entrailSpawnRate = 12;
        @Name("Disabled?")
        public boolean entrailDisabled = false;
    }

    public static class CategoryFallenLeader
    {
        @Name("Health Multiplier")
        public float fallenLeaderHealthMultiplier = 1.0F;
        @Name("Strength Multiplier")
        public float fallenLeaderStrengthMultiplier = 1.0F;
        @Name("Speed Multiplier")
        public float fallenLeaderSpeedMultiplier = 1.0F;
        @Name("Spawn Rate")
        public int fallenLeaderSpawnRate = 12;
        @Name("Disabled?")
        public boolean fallenLeaderDisabled = false;
    }

    public static class CategoryFreezer
    {
        @Name("Health Multiplier")
        public float freezerHealthMultiplier = 1.0F;
        @Name("Strength Multiplier")
        public float freezerStrengthMultiplier = 1.0F;
        @Name("Speed Multiplier")
        public float freezerSpeedMultiplier = 1.0F;
        @Name("Spawn Rate")
        public int freezerSpawnRate = 8;
        @Name("Disabled?")
        public boolean freezerDisabled = false;
    }

    public static class CategoryHauntedCow
    {
        @Name("Valid Weapons")
        @Comment("Custom valid weapons against haunted cows")
        public String[] validWeapons = {"thaumicaugmentation:morphic_tool"};
        @Name("Health Multiplier")
        public float hauntedCowHealthMultiplier = 1.0F;
        @Name("Strength Multiplier")
        public float hauntedCowStrengthMultiplier = 1.0F;
        @Name("Speed Multiplier")
        public float hauntedCowSpeedMultiplier = 1.0F;
        @Name("Spawn Rate")
        public int hauntedCowSpawnRate = 8;
        @Name("Disabled?")
        public boolean hauntedCowDisabled = false;
        @Name("Time Change Disabled?")
        @Comment("Whether or not the time changing feature on wrong weapon hits should be disabled")
        public boolean hauntedCowDisableTimeChange = false;

        public boolean isValidWeapon(Item item)
        {
            String regName = item.getRegistryName().toString();
            for (String s : validWeapons)
            {
                if (regName.equals(s))
                {
                    return true;
                }
            }
            return false;
        }
    }

    public static class CategoryMutantSteve
    {
        @Name("Health Multiplier")
        public float mutantSteveHealthMultiplier = 1.0F;
        @Name("Strength Multiplier")
        public float mutantSteveStrengthMultiplier = 1.0F;
        @Name("Speed Multiplier")
        public float mutantSteveSpeedMultiplier = 1.0F;
        @Name("Spawn Rate")
        public int mutantSteveSpawnRate = 8;
        @Name("Disabled?")
        public boolean mutantSteveDisabled = false;
    }

    public static class CategoryPresent
    {
        @Name("Health Multiplier")
        public float presentHealthMultiplier = 1.0F;
        @Name("Strength Multiplier")
        public float presentStrengthMultiplier = 1.0F;
        @Name("Speed Multiplier")
        public float presentSpeedMultiplier = 1.0F;
        @Name("Spawn Rate")
        public int presentSpawnRate = 12;
        @Name("Disabled?")
        public boolean presentDisabled = false;
    }

    public static class CategoryStranger
    {
        @Name("Health Multiplier")
        public float strangerHealthMultiplier = 1.0F;
        @Name("Strength Multiplier")
        public float strangerStrengthMultiplier = 1.0F;
        @Name("Speed Multiplier")
        public float strangerSpeedMultiplier = 1.0F;
        @Name("Spawn Rate")
        public int strangerSpawnRate = 12;
        @Name("Disabled?")
        public boolean strangerDisabled = false;
    }

    public static class CategoryTopielec
    {
        @Name("Health Multiplier")
        public float topielecHealthMultiplier = 1.0F;
        @Name("Strength Multiplier")
        public float topielecStrengthMultiplier = 1.0F;
        @Name("Speed Multiplier")
        public float topielecSpeedMultiplier = 1.0F;
        @Name("Spawn Rate")
        public int topielecSpawnRate = 8;
        @Name("Search Distance")
        @Comment("The search distance for nearby players in blocks")
        public int topielecSearchDistance = 16;
        @Name("Harpoons Only")
        @Comment("Whether or not only harpoons should be effective agains Topielecs")
        public boolean topielecHarpoonOnly = false;
        @Name("Disabled?")
        public boolean topielecDisabled = false;
    }

    public static class CategoryUnbornBaby
    {
        @Name("Health Multiplier")
        public float babyHealthMultiplier = 1.0F;
        @Name("Strength Multiplier")
        public float babyStrengthMultiplier = 1.0F;
        @Name("Speed Multiplier")
        public float babySpeedMultiplier = 1.0F;
        @Name("Spawn Rate")
        public int babySpawnRate = 12;
        @Name("Blindness")
        @Comment("Whether or not blindness should be inflicted")
        public boolean babyBlindness = false;
        @Name("Disabled?")
        public boolean babyDisabled = false;
    }

    public static class CategoryZombieChicken
    {
        @Name("Health Multiplier")
        public float zombieChickenHealthMultiplier = 1.0F;
        @Name("Strength Multiplier")
        public float zombieChickenStrengthMultiplier = 1.0F;
        @Name("Speed Multiplier")
        public float zombieChickenSpeedMultiplier = 1.0F;
        @Name("Spawn Rate")
        public int zombieChickenSpawnRate = 12;
        @Name("Disabled?")
        public boolean zombieChickenDisabled = false;
    }

    @Mod.EventBusSubscriber(modid = MainMod.MOD_ID)
    public static class EventHandler
    {
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(MainMod.MOD_ID))
            {
                ConfigManager.sync(MainMod.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}