package com.dmonsters.main;

import com.dmonsters.DeadlyMonsters;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ModConfig {
    private static Configuration config;

    // Create configuration categories
    public static final String CATEGORY_GENERAL = "General";
    public static final String CATEGORY_BLOODY_MAIDEN = "BloodyMaiden";
    public static final String CATEGORY_CLIMBER = "Climber";
    public static final String CATEGORY_ENTRAIL = "Entrail";
    public static final String CATEGORY_FALLEN_LADDER = "Fallen Leader";
    public static final String CATEGORY_FREEZER = "Freezer";
    public static final String CATEGORY_HAUNTED_COW = "Haunted Cow";
    public static final String CATEGORY_MUTANT_STEVE = "Mutant Steve";
    public static final String CATEGORY_PRESENT = "Present";
    public static final String CATEGORY_STRANGER = "Stranger";
    public static final String CATEGORY_TOPIELEC = "Topielec";
    public static final String CATEGORY_UNBORN_BABY = "Unborn Baby";
    public static final String CATEGORY_ZOMBIE_CHICKEN = "Zombie Chicken";

    // General category properties
    public static float globalHealthMultiplier;
    public static float globalStrengthMultiplier;
    public static float globalSpeedMultiplier;
    public static boolean disableTimeChangeInvasions;

    // Bloody Maiden category properties
    public static float bloodyMaidenHealthMultiplier;
    public static float bloodyMaidenStrengthMultiplier;
    public static float bloodyMaidenSpeedMultiplier;
    public static int bloodyMaidenSpawnRate;
    public static boolean bloodyMaidenDisabled;

    // Climber category properties
    public static float climberHealthMultiplier;
    public static float climberStrengthMultiplier;
    public static float climberSpeedMultiplier;
    public static int climberSpawnRate;
    public static boolean climberDisabled;

    // Entrail category properties
    public static float entrailHealthMultiplier;
    public static float entrailStrengthMultiplier;
    public static float entrailSpeedMultiplier;
    public static int entrailSpawnRate;
    public static boolean entrailDisabled;

    // Fallen Leader category properties

    public static float fallenLeaderHealthMultiplier;
    public static float fallenLeaderStrengthMultiplier;
    public static float fallenLeaderSpeedMultiplier;
    public static int fallenLeaderSpawnRate;
    public static boolean fallenLeaderDisabled;
    // Freezer category properties
    public static float freezerHealthMultiplier;
    public static float freezerStrengthMultiplier;
    public static float freezerSpeedMultiplier;
    public static int freezerSpawnRate;
    public static boolean freezerDisabled;

    // Haunted Cow category properties
    public static String[] validWeapons = {"thaumicaugmentation:morphic_tool"};
    public static float hauntedCowHealthMultiplier;
    public static float hauntedCowStrengthMultiplier;
    public static float hauntedCowSpeedMultiplier;
    public static int hauntedCowSpawnRate;
    public static boolean hauntedCowDisabled;
    public static boolean hauntedCowDisableTimeChange;

    // Mutant Steve category properties
    public static float mutantSteveHealthMultiplier;
    public static float mutantSteveStrengthMultiplier;
    public static float mutantSteveSpeedMultiplier;
    public static int mutantSteveSpawnRate;
    public static boolean mutantSteveDisabled;

    // Present category properties
    public static float presentHealthMultiplier;
    public static float presentStrengthMultiplier;
    public static float presentSpeedMultiplier;
    public static int presentSpawnRate;
    public static boolean presentDisabled;

    // Stranger category properties
    public static float strangerHealthMultiplier;
    public static float strangerStrengthMultiplier;
    public static float strangerSpeedMultiplier;
    public static int strangerSpawnRate;
    public static boolean strangerDisabled;

    // Topielec category properties
    public static float topielecHealthMultiplier;
    public static float topielecStrengthMultiplier;
    public static float topielecSpeedMultiplier;
    public static int topielecSpawnRate;
    public static int topielecSearchDistance = 16;
    public static boolean topielecHarpoonOnly = false;
    public static boolean topielecDisabled;

    // Unborn Baby category properties
    public static float babyHealthMultiplier;
    public static float babyStrengthMultiplier;
    public static float babySpeedMultiplier;
    public static int babySpawnRate;
    public static boolean babyBlindness;
    public static boolean babyDisabled;

    // Zombie Chicken category properties
    public static float zombieChickenHealthMultiplier;
    public static float zombieChickenStrengthMultiplier;
    public static float zombieChickenSpeedMultiplier;
    public static int zombieChickenSpawnRate;
    public static boolean zombieChickenDisabled;

    public static void preInit(File configFile)
    {
        config = new Configuration(configFile);
        syncConfig();
    }

    private static void syncConfig() {
        // General category
        globalHealthMultiplier = config.getFloat("Global Health Multiplier", CATEGORY_GENERAL, 1.0F, 0.0F, Float.MAX_VALUE,
            "Global Health Multiplier");
        globalStrengthMultiplier = config.getFloat("Global Strength Multiplier", CATEGORY_GENERAL, 1.0F, 0.0F, Float.MAX_VALUE,
            "Global Strength Multiplier");
        globalSpeedMultiplier = config.getFloat("Global Speed Multiplier", CATEGORY_GENERAL, 1.0F, 0.0F, Float.MAX_VALUE,
            "Global Speed Multiplier");
        disableTimeChangeInvasions = config.getBoolean("Disable Time Change During Invasions", CATEGORY_GENERAL, true,
            "Disable Time Change During Invasions");

        // Bloody Maiden category
        bloodyMaidenHealthMultiplier = config.getFloat("Health Multiplier", CATEGORY_BLOODY_MAIDEN, 1.0F, 0.0F, Float.MAX_VALUE,
            "Health Multiplier");
        bloodyMaidenStrengthMultiplier = config.getFloat("Strength Multiplier", CATEGORY_BLOODY_MAIDEN, 1.0F, 0.0F, Float.MAX_VALUE,
            "Strength Multiplier");
        bloodyMaidenSpeedMultiplier = config.getFloat("Speed Multiplier", CATEGORY_BLOODY_MAIDEN, 1.0F, 0.0F, Float.MAX_VALUE,
            "Speed Multiplier");
        bloodyMaidenSpawnRate = config.getInt("Spawn Rate", CATEGORY_BLOODY_MAIDEN, 12, 0, Integer.MAX_VALUE, "Spawn Rate");
        bloodyMaidenDisabled = config.getBoolean("Disabled?", CATEGORY_BLOODY_MAIDEN, false, "Disabled?");

        // Climber category properties
        climberHealthMultiplier = config.getFloat("Health Multiplier", CATEGORY_CLIMBER, 1.0F, 0.0F, Float.MAX_VALUE,
            "Health Multiplier");
        climberStrengthMultiplier = config.getFloat("Strength Multiplier", CATEGORY_CLIMBER, 1.0F, 0.0F, Float.MAX_VALUE,
            "Strength Multiplier");
        climberSpeedMultiplier = config.getFloat("Speed Multiplier", CATEGORY_CLIMBER, 1.0F, 0.0F, Float.MAX_VALUE,
            "Speed Multiplier");
        climberSpawnRate = config.getInt("Spawn Rate", CATEGORY_CLIMBER, 8, 0, Integer.MAX_VALUE, "Spawn Rate");
        climberDisabled = config.getBoolean("Disabled?", CATEGORY_CLIMBER, false, "Disabled?");


        // Entrail category properties
        entrailHealthMultiplier = config.getFloat("Health Multiplier", CATEGORY_ENTRAIL, 1.0F, 0.0F, Float.MAX_VALUE,
            "Health Multiplier");
        entrailStrengthMultiplier = config.getFloat("Strength Multiplier", CATEGORY_ENTRAIL, 1.0F, 0.0F, Float.MAX_VALUE,
            "Strength Multiplier");
        entrailSpeedMultiplier = config.getFloat("Speed Multiplier", CATEGORY_ENTRAIL, 1.0F, 0.0F, Float.MAX_VALUE,
            "Speed Multiplier");
        entrailSpawnRate = config.getInt("Spawn Rate", CATEGORY_ENTRAIL, 12, 0, Integer.MAX_VALUE, "Spawn Rate");
        entrailDisabled = config.getBoolean("Disabled?", CATEGORY_ENTRAIL, false, "Disabled?");

        // Fallen Leader category properties
        fallenLeaderHealthMultiplier = config.getFloat("Health Multiplier", CATEGORY_FALLEN_LADDER, 1.0F, 0.0F, Float.MAX_VALUE,
            "Health Multiplier");
        fallenLeaderStrengthMultiplier = config.getFloat("Strength Multiplier", CATEGORY_FALLEN_LADDER, 1.0F, 0.0F, Float.MAX_VALUE,
            "Strength Multiplier");
        fallenLeaderSpeedMultiplier = config.getFloat("Speed Multiplier", CATEGORY_FALLEN_LADDER, 1.0F, 0.0F, Float.MAX_VALUE,
            "Speed Multiplier");
        fallenLeaderSpawnRate = config.getInt("Spawn Rate", CATEGORY_FALLEN_LADDER, 12, 0, Integer.MAX_VALUE, "Spawn Rate");
        fallenLeaderDisabled = config.getBoolean("Disabled?", CATEGORY_FALLEN_LADDER, false, "Disabled?");


        // Freezer category properties
        freezerHealthMultiplier = config.getFloat("Health Multiplier", CATEGORY_FREEZER, 1.0F, 0.0F, Float.MAX_VALUE,
            "Health Multiplier");
        freezerStrengthMultiplier = config.getFloat("Strength Multiplier", CATEGORY_FREEZER, 1.0F, 0.0F, Float.MAX_VALUE,
            "Strength Multiplier");
        freezerSpeedMultiplier = config.getFloat("Speed Multiplier", CATEGORY_FREEZER, 1.0F, 0.0F, Float.MAX_VALUE,
            "Speed Multiplier");
        freezerSpawnRate = config.getInt("Spawn Rate", CATEGORY_FREEZER, 8, 0, Integer.MAX_VALUE, "Spawn Rate");
        freezerDisabled = config.getBoolean("Disabled?", CATEGORY_FREEZER, false, "Disabled?");


        // Haunted Cow category properties
        validWeapons = config.getStringList("Valid Weapons", CATEGORY_HAUNTED_COW, validWeapons,
            "List of valid weapons against haunted cows");
        hauntedCowHealthMultiplier = config.getFloat("Health Multiplier", CATEGORY_HAUNTED_COW, 1.0F, 0.0F, Float.MAX_VALUE,
            "Health Multiplier");
        hauntedCowStrengthMultiplier = config.getFloat("Strength Multiplier", CATEGORY_HAUNTED_COW, 1.0F, 0.0F, Float.MAX_VALUE,
            "Strength Multiplier");
        hauntedCowSpeedMultiplier = config.getFloat("Speed Multiplier", CATEGORY_HAUNTED_COW, 1.0F, 0.0F, Float.MAX_VALUE,
            "Speed Multiplier");
        hauntedCowSpawnRate = config.getInt("Spawn Rate", CATEGORY_HAUNTED_COW, 8, 0, Integer.MAX_VALUE, "Spawn Rate");
        hauntedCowDisabled = config.getBoolean("Disabled?", CATEGORY_HAUNTED_COW, false, "Disabled?");
        hauntedCowDisableTimeChange = config.getBoolean("Time Change Disabled??", CATEGORY_HAUNTED_COW, false, "Whether or not the time changing feature on wrong weapon hits should be disabled?");

        // Mutant Steve category properties
        mutantSteveHealthMultiplier = config.getFloat("Health Multiplier", CATEGORY_MUTANT_STEVE, 1.0F, 0.0F, Float.MAX_VALUE,
            "Health Multiplier");
        mutantSteveStrengthMultiplier = config.getFloat("Strength Multiplier", CATEGORY_MUTANT_STEVE, 1.0F, 0.0F, Float.MAX_VALUE,
            "Strength Multiplier");
        mutantSteveSpeedMultiplier = config.getFloat("Speed Multiplier", CATEGORY_MUTANT_STEVE, 1.0F, 0.0F, Float.MAX_VALUE,
            "Speed Multiplier");
        mutantSteveSpawnRate = config.getInt("Spawn Rate", CATEGORY_MUTANT_STEVE, 8, 0, Integer.MAX_VALUE, "Spawn Rate");
        mutantSteveDisabled = config.getBoolean("Disabled?", CATEGORY_MUTANT_STEVE, false, "Disabled?");

        // Present category properties
        presentHealthMultiplier = config.getFloat("Health Multiplier", CATEGORY_PRESENT, 1.0F, 0.0F, Float.MAX_VALUE,
            "Health Multiplier");
        presentStrengthMultiplier = config.getFloat("Strength Multiplier", CATEGORY_PRESENT, 1.0F, 0.0F, Float.MAX_VALUE,
            "Strength Multiplier");
        presentSpeedMultiplier = config.getFloat("Speed Multiplier", CATEGORY_PRESENT, 1.0F, 0.0F, Float.MAX_VALUE,
            "Speed Multiplier");
        presentSpawnRate = config.getInt("Spawn Rate", CATEGORY_PRESENT, 12, 0, Integer.MAX_VALUE, "Spawn Rate");
        presentDisabled = config.getBoolean("Disabled?", CATEGORY_PRESENT, false, "Disabled?");

        // Stranger category properties
        strangerHealthMultiplier = config.getFloat("Health Multiplier", CATEGORY_STRANGER, 1.0F, 0.0F, Float.MAX_VALUE,
            "Health Multiplier");
        strangerStrengthMultiplier = config.getFloat("Strength Multiplier", CATEGORY_STRANGER, 1.0F, 0.0F, Float.MAX_VALUE,
            "Strength Multiplier");
        strangerSpeedMultiplier = config.getFloat("Speed Multiplier", CATEGORY_STRANGER, 1.0F, 0.0F, Float.MAX_VALUE,
            "Speed Multiplier");
        strangerSpawnRate = config.getInt("Spawn Rate", CATEGORY_STRANGER, 12, 0, Integer.MAX_VALUE, "Spawn Rate");
        strangerDisabled = config.getBoolean("Disabled?", CATEGORY_STRANGER, false, "Disabled?");

        // Topielec category properties
        topielecHealthMultiplier = config.getFloat("Health Multiplier", CATEGORY_TOPIELEC, 1.0F, 0.0F, Float.MAX_VALUE,
            "Health Multiplier");
        topielecStrengthMultiplier = config.getFloat("Strength Multiplier", CATEGORY_TOPIELEC, 1.0F, 0.0F, Float.MAX_VALUE,
            "Strength Multiplier");
        topielecSpeedMultiplier = config.getFloat("Speed Multiplier", CATEGORY_TOPIELEC, 1.0F, 0.0F, Float.MAX_VALUE,
            "Speed Multiplier");
        topielecSpawnRate = config.getInt("Spawn Rate", CATEGORY_TOPIELEC, 8, 0, Integer.MAX_VALUE, "Spawn Rate");
        topielecSearchDistance = config.getInt("Search Distance", CATEGORY_TOPIELEC, 16, 0, Integer.MAX_VALUE, "The search distance for nearby players in blocks");
        topielecHarpoonOnly = config.getBoolean("Harpoons Only", CATEGORY_TOPIELEC, false, "Whether or not only harpoons should be effective agains Topielecs");
        topielecDisabled = config.getBoolean("Disabled?", CATEGORY_TOPIELEC, false, "Disabled?");

        // Unborn Baby category properties
        babyHealthMultiplier = config.getFloat("Health Multiplier", CATEGORY_UNBORN_BABY, 1.0F, 0.0F, Float.MAX_VALUE,
            "Health Multiplier");
        babyStrengthMultiplier = config.getFloat("Strength Multiplier", CATEGORY_UNBORN_BABY, 1.0F, 0.0F, Float.MAX_VALUE,
            "Strength Multiplier");
        babySpeedMultiplier = config.getFloat("Speed Multiplier", CATEGORY_UNBORN_BABY, 1.0F, 0.0F, Float.MAX_VALUE,
            "Speed Multiplier");
        babySpawnRate = config.getInt("Spawn Rate", CATEGORY_UNBORN_BABY, 12, 0, Integer.MAX_VALUE, "Spawn Rate");
        babyBlindness = config.getBoolean("Blindness", CATEGORY_UNBORN_BABY, false, "Whether or not blindness should be inflicted");
        babyDisabled = config.getBoolean("Disabled?", CATEGORY_UNBORN_BABY, false, "Disabled?");

        // Zombie Chicken category properties
        zombieChickenHealthMultiplier = config.getFloat("Health Multiplier", CATEGORY_ZOMBIE_CHICKEN, 1.0F, 0.0F, Float.MAX_VALUE,
            "Health Multiplier");
        zombieChickenStrengthMultiplier = config.getFloat("Strength Multiplier", CATEGORY_ZOMBIE_CHICKEN, 1.0F, 0.0F, Float.MAX_VALUE,
            "Strength Multiplier");
        zombieChickenSpeedMultiplier = config.getFloat("Speed Multiplier", CATEGORY_ZOMBIE_CHICKEN, 1.0F, 0.0F, Float.MAX_VALUE,
            "Speed Multiplier");
        zombieChickenSpawnRate = config.getInt("Spawn Rate", CATEGORY_ZOMBIE_CHICKEN, 12, 0, Integer.MAX_VALUE, "Spawn Rate");
        zombieChickenDisabled = config.getBoolean("Disabled?", CATEGORY_ZOMBIE_CHICKEN, false, "Disabled?");
        if (config.hasChanged()) {
            config.save();
        }
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(DeadlyMonsters.MOD_ID)) {
            syncConfig();
        }
    }
    public static boolean isValidWeapon(Item item)
    {
        String regName = item.getUnlocalizedName();
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
