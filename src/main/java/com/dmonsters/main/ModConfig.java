package com.dmonsters.main;

import java.io.File;

import org.apache.logging.log4j.Level;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.dmonsters.proxy.CommonProxy;

public class ModConfig
{
    private static final String CATEGORY_GENERAL = "General";
    private static final String CATEGORY_UNBORNBABY = "Unborn Baby";
    private static final String CATEGORY_CLIMBER = "Climber";
    private static final String CATEGORY_ENTRAIL = "Entrail";
    private static final String CATEGORY_FREEZER = "Freezer";
    private static final String CATEGORY_MUTANTSTEVE = "Mutant Steve";
    private static final String CATEGORY_FALLENLEADER = "Fallen Leader";
    private static final String CATEGORY_BLOODYMAIDEN = "Bloody Maiden";
    private static final String CATEGORY_ZOMBIECHICKEN = "Zombie Chicken";
    private static final String CATEGORY_PRESENT = "Present";
    private static final String CATEGORY_STRANGER = "Stranger";
    private static final String CATEGORY_HAUNTEDCOW = "Haunted Cow";
    private static final String CATEGORY_TOPIELEC = "Topielec";

    //
    //GENERAL
    //

    public static boolean mobsDisable = false;
    public static float healthMultiplier = 1;
    public static float strengthMultiplier = 0.8F;
    public static float speedMultiplier = 1;

    //
    //MOBS
    //

    //Bloody Maiden
    public static float bloodyMaidenHealthMultiplier = 1;
    public static float bloodyMaidenStrengthMultiplier = 1;
    public static float bloodyMaidenSpeedMultiplier = 1;
    public static int bloodyMaidenSpawnRate = 12;
    public static boolean bloodyMaidenDisabled = false;
    //Climber
    public static float climberHealthMultiplier = 1;
    public static float climberStrengthMultiplier = 1;
    public static float climberSpeedMultiplier = 1;
    public static int climberSpawnRate = 8;
    public static boolean climberDisabled = false;
    //Entrail
    public static float entrailHealthMultiplier = 1;
    public static float entrailStrengthMultiplier = 1;
    public static float entrailSpeedMultiplier = 1;
    public static int entrailSpawnRate = 12;
    public static boolean entrailDisabled = false;
    //Fallen Leader
    public static float fallenLeaderHealthMultiplier = 1;
    public static float fallenLeaderStrengthMultiplier = 1;
    public static float fallenLeaderSpeedMultiplier = 1;
    public static int fallenLeaderSpawnRate = 12;
    public static boolean fallenLeaderDisabled = false;
    //Freezer
    public static float freezerHealthMultiplier = 1;
    public static float freezerStrengthMultiplier = 1;
    public static float freezerSpeedMultiplier = 1;
    public static int freezerSpawnRate = 8;
    public static boolean freezerDisabled = false;
    //Haunted Cow
    public static float hauntedCowHealthMultiplier = 1;
    public static float hauntedCowStrengthMultiplier = 1;
    public static float hauntedCowSpeedMultiplier = 1;
    public static int hauntedCowSpawnRate = 8;
    public static boolean hauntedCowDisabled = false;
    public static boolean hauntedCowDisableTimeChange = false;
    //Mutant Steve
    public static float mutantSteveHealthMultiplier = 1;
    public static float mutantSteveStrengthMultiplier = 1;
    public static float mutantSteveSpeedMultiplier = 1;
    public static int mutantSteveSpawnRate = 8;
    public static boolean mutantSteveDisabled = false;
    //Present
    public static float presentHealthMultiplier = 1;
    public static float presentStrengthMultiplier = 1;
    public static float presentSpeedMultiplier = 1;
    public static int presentSpawnRate = 12;
    public static boolean presentDisabled = false;
    //Stranger
    public static float strangerHealthMultiplier = 1;
    public static float strangerStrengthMultiplier = 1;
    public static float strangerSpeedMultiplier = 1;
    public static int strangerSpawnRate = 12;
    public static boolean strangerDisabled = false;
    //Topielec
    public static float topielecHealthMultiplier = 1;
    public static float topielecStrengthMultiplier = 1;
    public static float topielecSpeedMultiplier = 1;
    public static int topielecSpawnRate = 8;
    public static int topielecSearchDistance = 8;
    public static boolean topielecHarpoonOnly = false;
    public static boolean topielecDisabled = false;
    //Unborn Baby
    public static float babyHealthMultiplier = 1;
    public static float babyStrengthMultiplier = 1;
    public static float babySpeedMultiplier = 1;
    public static int babySpawnRate = 12;
    public static boolean babyBlindness = false;
    public static boolean babyDisabled = false;
    //Zombie Chicken
    public static float zombieChickenHealthMultiplier = 1;
    public static float zombieChickenStrengthMultiplier = 1;
    public static float zombieChickenSpeedMultiplier = 1;
    public static int zombieChickenSpawnRate = 12;
    public static boolean zombieChickenDisabled = false;

    public static void readConfig()
    {
        Configuration cfg = CommonProxy.config;
        try
        {
            cfg.load();
            initGeneralConfig(cfg);
            initBabyConfig(cfg);
            initClimberConfig(cfg);
            initEntrailConfig(cfg);
            initFreezerConfig(cfg);
            initMutantSteveConfig(cfg);
            initWidemanConfig(cfg);
            initWomanConfig(cfg);
            initZombieChickenConfig(cfg);
            initPresentConfig(cfg);
            initStrangerConfig(cfg);
            initHauntedCowConfig(cfg);
            initTopielecConfig(cfg);
        }
        catch (Exception e1)
        {
            MainMod.logger.log(Level.ERROR, "Problem loading config file!", e1);
        }
        finally
        {
            if (cfg.hasChanged())
            {
                cfg.save();
            }
        }
    }

    public static void initConfig(FMLPreInitializationEvent e)
    {
        File directory = e.getModConfigurationDirectory();
        CommonProxy.config = new Configuration(new File(directory.getPath(), "DeadlyMonsters.cfg"));
        readConfig();
    }

    public static void saveConfig(FMLPostInitializationEvent e)
    {
        if (CommonProxy.config.hasChanged())
        {
            CommonProxy.config.save();
        }
    }

    private static void initGeneralConfig(Configuration cfg)
    {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        mobsDisable = cfg.getBoolean("disableMobs", CATEGORY_GENERAL, mobsDisable, "Set to true if you want to disable additional mobs");
        healthMultiplier = cfg.getFloat("healthMultiplier", CATEGORY_GENERAL, 1, 0.01F, 999, "Health multiplier for all mod mobs");
        strengthMultiplier = cfg.getFloat("strengthMultiplier", CATEGORY_GENERAL, 1, 0.01F, 999, "Strength multiplier the all mod mobs");
        speedMultiplier = cfg.getFloat("speedMultiplier", CATEGORY_GENERAL, 1, 0.01F, 999, "Speed multiplier for the mod mobs");
    }

    private static void initBabyConfig(Configuration cfg)
    {
        cfg.addCustomCategoryComment(CATEGORY_UNBORNBABY, "Unborn Baby");
        babyDisabled = cfg.getBoolean("babyDisabled", CATEGORY_UNBORNBABY, babyDisabled, "Set to true if you want to disable Unborn Baby");
        babyBlindness = cfg.getBoolean("babyBlindness", CATEGORY_UNBORNBABY, babyBlindness, "Set to true if you want to enable the blindness ability");
        babyHealthMultiplier = cfg.getFloat("babyHealthMultiplier", CATEGORY_UNBORNBABY, 1, 0.01F, 999, "Unborn Baby health multiplier");
        babyStrengthMultiplier = cfg.getFloat("babyStrengthMultiplier", CATEGORY_UNBORNBABY, 1, 0.01F, 999, "Unborn Baby strength multiplier");
        babySpeedMultiplier = cfg.getFloat("babySpeedMultiplier", CATEGORY_UNBORNBABY, 1, 0.01F, 999, "Unborn Baby speed multiplier");
        babySpawnRate = cfg.getInt("babySpawnRate", CATEGORY_UNBORNBABY, babySpawnRate, 0, 999, "Unborn Baby spawn rate. Default for Zombie is 8.");
    }

    private static void initClimberConfig(Configuration cfg)
    {
        cfg.addCustomCategoryComment(CATEGORY_CLIMBER, "Climber");
        climberDisabled = cfg.getBoolean("climberDisabled", CATEGORY_CLIMBER, climberDisabled, "Set to true if you want to disable Climber");
        climberHealthMultiplier = cfg.getFloat("climberHealthMultiplier", CATEGORY_CLIMBER, 1, 0.01F, 999, "Climber health multiplier");
        climberStrengthMultiplier = cfg.getFloat("climberStrengthMultiplier", CATEGORY_CLIMBER, 1, 0.01F, 999, "Climber strength multiplier");
        climberSpeedMultiplier = cfg.getFloat("climberSpeedMultiplier", CATEGORY_CLIMBER, 1, 0.01F, 999, "Climber speed multiplier");
        climberSpawnRate = cfg.getInt("climberSpawnRate", CATEGORY_CLIMBER, climberSpawnRate, 0, 999, "Climber spawn rate. Default for Zombie is 8.");
    }

    private static void initEntrailConfig(Configuration cfg)
    {
        cfg.addCustomCategoryComment(CATEGORY_ENTRAIL, "Entrail");
        entrailDisabled = cfg.getBoolean("entrailDisabled", CATEGORY_ENTRAIL, entrailDisabled, "Set to true if you want to disable Entrail");
        entrailHealthMultiplier = cfg.getFloat("entrailHealthMultiplier", CATEGORY_ENTRAIL, 1, 0.01F, 999, "Entrail health multiplier");
        entrailStrengthMultiplier = cfg.getFloat("entrailStrengthMultiplier", CATEGORY_ENTRAIL, 1, 0.01F, 999, "Entrail strength multiplier");
        entrailSpeedMultiplier = cfg.getFloat("entrailSpeedMultiplier", CATEGORY_ENTRAIL, 1, 0.01F, 999, "Entrail speed multiplier");
        entrailSpawnRate = cfg.getInt("entrailSpawnRate", CATEGORY_ENTRAIL, entrailSpawnRate, 0, 999, "Entrail spawn rate. Default for Zombie is 8.");
    }

    private static void initFreezerConfig(Configuration cfg)
    {
        cfg.addCustomCategoryComment(CATEGORY_FREEZER, "Freezer");
        freezerDisabled = cfg.getBoolean("freezerDisabled", CATEGORY_FREEZER, freezerDisabled, "Set to true if you want to disable Freezer");
        freezerHealthMultiplier = cfg.getFloat("freezerHealthMultiplier", CATEGORY_FREEZER, 1, 0.01F, 999, "Freezer health multiplier");
        freezerStrengthMultiplier = cfg.getFloat("freezerStrengthMultiplier", CATEGORY_FREEZER, 1, 0.01F, 999, "Freezer strength multiplier");
        freezerSpeedMultiplier = cfg.getFloat("freezerSpeedMultiplier", CATEGORY_FREEZER, 1, 0.01F, 999, "Freezer speed multiplier");
        freezerSpawnRate = cfg.getInt("freezerSpawnRate", CATEGORY_FREEZER, freezerSpawnRate, 0, 999, "Freezer spawn rate. Default for Zombie is 8.");
    }

    private static void initMutantSteveConfig(Configuration cfg)
    {
        cfg.addCustomCategoryComment(CATEGORY_MUTANTSTEVE, "Mutant Steve");
        mutantSteveDisabled = cfg.getBoolean("mutantSteveDisabled", CATEGORY_MUTANTSTEVE, mutantSteveDisabled, "Set to true if you want to disable Mutant Steve");
        mutantSteveHealthMultiplier = cfg.getFloat("mutantSteveHealthMultiplier", CATEGORY_MUTANTSTEVE, 1, 0.01F, 999, "Mutant Steve health multiplier");
        mutantSteveStrengthMultiplier = cfg.getFloat("mutantSteveStrengthMultiplier", CATEGORY_MUTANTSTEVE, 1, 0.01F, 999, "Mutant Steve strength multiplier");
        mutantSteveSpeedMultiplier = cfg.getFloat("mutantSteveSpeedMultiplier", CATEGORY_MUTANTSTEVE, 1, 0.01F, 999, "Mutant Steve speed multiplier");
        mutantSteveSpawnRate = cfg.getInt("mutantSteveSpawnRate", CATEGORY_MUTANTSTEVE, mutantSteveSpawnRate, 0, 999, "Mutant Steve spawn rate. Default for Zombie is 8.");
    }

    private static void initWidemanConfig(Configuration cfg)
    {
        cfg.addCustomCategoryComment(CATEGORY_FALLENLEADER, "Fallen Leader");
        fallenLeaderDisabled = cfg.getBoolean("fallenLeaderDisabled", CATEGORY_FALLENLEADER, fallenLeaderDisabled, "Set to true if you want to disable Fallen Leader");
        fallenLeaderHealthMultiplier = cfg.getFloat("fallenLeaderHealthMultiplier", CATEGORY_FALLENLEADER, 1, 0.01F, 999, "Fallen Leader health multiplier");
        fallenLeaderStrengthMultiplier = cfg.getFloat("fallenLeaderStrengthMultiplier", CATEGORY_FALLENLEADER, 1, 0.01F, 999, "Fallen Leader strength multiplier");
        fallenLeaderSpeedMultiplier = cfg.getFloat("fallenLeaderSpeedMultiplier", CATEGORY_FALLENLEADER, 1, 0.01F, 999, "Fallen Leader speed multiplier");
        fallenLeaderSpawnRate = cfg.getInt("fallenLeaderSpawnRate", CATEGORY_FALLENLEADER, fallenLeaderSpawnRate, 0, 999, "Fallen Leader spawn rate. Default for Zombie is 8.");
    }

    private static void initWomanConfig(Configuration cfg)
    {
        cfg.addCustomCategoryComment(CATEGORY_BLOODYMAIDEN, "Bloody Maiden");
        bloodyMaidenDisabled = cfg.getBoolean("bloodyMaidenDisabled", CATEGORY_BLOODYMAIDEN, bloodyMaidenDisabled, "Set to true if you want to disable Bloody Maiden");
        bloodyMaidenHealthMultiplier = cfg.getFloat("bloodyMaidenHealthMultiplier", CATEGORY_BLOODYMAIDEN, 1, 0.01F, 999, "Bloody Maiden health multiplier");
        bloodyMaidenStrengthMultiplier = cfg.getFloat("bloodyMaidenStrengthMultiplier", CATEGORY_BLOODYMAIDEN, 1, 0.01F, 999, "Bloody Maiden strength multiplier");
        bloodyMaidenSpeedMultiplier = cfg.getFloat("bloodyMaidenSpeedMultiplier", CATEGORY_BLOODYMAIDEN, 1, 0.01F, 999, "Bloody Maiden speed multiplier");
        bloodyMaidenSpawnRate = cfg.getInt("bloodyMaidenSpawnRate", CATEGORY_BLOODYMAIDEN, bloodyMaidenSpawnRate, 0, 999, "Bloody Maiden spawn rate. Default for Zombie is 8.");
    }

    private static void initZombieChickenConfig(Configuration cfg)
    {
        cfg.addCustomCategoryComment(CATEGORY_ZOMBIECHICKEN, "Zombie Chicken");
        zombieChickenDisabled = cfg.getBoolean("zombieChickenDisabled", CATEGORY_ZOMBIECHICKEN, zombieChickenDisabled, "Set to true if you want to disable Zombie Chicken");
        zombieChickenHealthMultiplier = cfg.getFloat("zombieChickenHealthMultiplier", CATEGORY_ZOMBIECHICKEN, 1, 0.01F, 999, "Zombie Chicken health multiplier");
        zombieChickenStrengthMultiplier = cfg.getFloat("zombieChickenStrengthMultiplier", CATEGORY_ZOMBIECHICKEN, 1, 0.01F, 999, "Zombie Chicken strength multiplier");
        zombieChickenSpeedMultiplier = cfg.getFloat("zombieChickenSpeedMultiplier", CATEGORY_ZOMBIECHICKEN, 1, 0.01F, 999, "Zombie Chicken speed multiplier");
        zombieChickenSpawnRate = cfg.getInt("zombieChickenSpawnRate", CATEGORY_ZOMBIECHICKEN, zombieChickenSpawnRate, 0, 999, "Zombie Chicken spawn rate. Default for Zombie is 8.");
    }

    private static void initPresentConfig(Configuration cfg)
    {
        cfg.addCustomCategoryComment(CATEGORY_PRESENT, "Present");
        presentDisabled = cfg.getBoolean("presentDisabled", CATEGORY_PRESENT, presentDisabled, "Set to true if you want to disable Present");
        presentHealthMultiplier = cfg.getFloat("presentHealthMultiplier", CATEGORY_PRESENT, 1, 0.01F, 999, "Present health multiplier");
        presentStrengthMultiplier = cfg.getFloat("presentStrengthMultiplier", CATEGORY_PRESENT, 1, 0.01F, 999, "Present strength multiplier");
        presentSpeedMultiplier = cfg.getFloat("presentSpeedMultiplier", CATEGORY_PRESENT, 1, 0.01F, 999, "Present speed multiplier");
        presentSpawnRate = cfg.getInt("presentSpawnRate", CATEGORY_PRESENT, presentSpawnRate, 0, 999, "Present spawn rate. Default for Zombie is 8.");
    }

    private static void initStrangerConfig(Configuration cfg)
    {
        cfg.addCustomCategoryComment(CATEGORY_STRANGER, "Stranger");
        strangerDisabled = cfg.getBoolean("strangerDisabled", CATEGORY_STRANGER, strangerDisabled, "Set to true if you want to disable Stranger");
        strangerHealthMultiplier = cfg.getFloat("strangerHealthMultiplier", CATEGORY_STRANGER, 1, 0.01F, 999, "Stranger health multiplier");
        strangerStrengthMultiplier = cfg.getFloat("strangerStrengthMultiplier", CATEGORY_STRANGER, 1, 0.01F, 999, "Stranger strength multiplier");
        strangerSpeedMultiplier = cfg.getFloat("strangerSpeedMultiplier", CATEGORY_STRANGER, 1, 0.01F, 999, "Stranger speed multiplier");
        strangerSpawnRate = cfg.getInt("strangerSpawnRate", CATEGORY_STRANGER, strangerSpawnRate, 0, 999, "Stranger spawn rate. Default for Zombie is 8.");
    }

    private static void initHauntedCowConfig(Configuration cfg)
    {
        cfg.addCustomCategoryComment(CATEGORY_HAUNTEDCOW, "Haunted Cow");
        hauntedCowDisabled = cfg.getBoolean("hauntedCowDisabled", CATEGORY_HAUNTEDCOW, hauntedCowDisabled, "Set to true if you want to disable Haunted Cow");
        hauntedCowHealthMultiplier = cfg.getFloat("hauntedCowHealthMultiplier", CATEGORY_HAUNTEDCOW, 1, 0.01F, 999, "Haunted Cow health multiplier");
        hauntedCowStrengthMultiplier = cfg.getFloat("hauntedCowStrengthMultiplier", CATEGORY_HAUNTEDCOW, 1, 0.01F, 999, "Haunted Cow strength multiplier");
        hauntedCowSpeedMultiplier = cfg.getFloat("hauntedCowSpeedMultiplier", CATEGORY_HAUNTEDCOW, 1, 0.01F, 999, "Haunted Cow speed multiplier");
        hauntedCowSpawnRate = cfg.getInt("hauntedCowSpawnRate", CATEGORY_HAUNTEDCOW, hauntedCowSpawnRate, 0, 999, "Haunted Cow spawn rate. Default for Zombie is 8.");
        hauntedCowDisableTimeChange = cfg.getBoolean("disableTimeChange", CATEGORY_HAUNTEDCOW, hauntedCowDisableTimeChange, "Set to true if you want to disable time change event");
    }

    private static void initTopielecConfig(Configuration cfg)
    {
        cfg.addCustomCategoryComment(CATEGORY_TOPIELEC, "Topielec");
        topielecDisabled = cfg.getBoolean("topielecDisabled", CATEGORY_TOPIELEC, topielecDisabled, "Set to true if you want to disable Topielec");
        topielecHealthMultiplier = cfg.getFloat("topielecHealthMultiplier", CATEGORY_TOPIELEC, 1, 0.01F, 999, "Topielec health multiplier");
        topielecStrengthMultiplier = cfg.getFloat("topielecStrengthMultiplier", CATEGORY_TOPIELEC, 1, 0.01F, 999, "Topielec strength multiplier");
        topielecSpeedMultiplier = cfg.getFloat("topielecSpeedMultiplier", CATEGORY_TOPIELEC, 1, 0.01F, 999, "Topielec speed multiplier");
        topielecSpawnRate = cfg.getInt("topielecSpawnRate", CATEGORY_TOPIELEC, topielecSpawnRate, 0, 999, "Topielec spawn rate. Default for Zombie is 8.");
        topielecSearchDistance = cfg.getInt("topielecSearchDistance", CATEGORY_TOPIELEC, topielecSearchDistance, 0, 999, "Topielec search distance.");
        topielecHarpoonOnly = cfg.getBoolean("topielecHarpoonOnly", CATEGORY_TOPIELEC, topielecHarpoonOnly, "Set to true if harpoons should be the only weapon against Topielecs.");
    }
}