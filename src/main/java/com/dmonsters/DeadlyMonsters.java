package com.dmonsters;

import com.dmonsters.main.*;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;

import com.dmonsters.proxy.CommonProxy;

import java.io.File;

@Mod(modid = DeadlyMonsters.MOD_ID, name = DeadlyMonsters.MOD_NAME, version = DeadlyMonsters.MOD_VERSION)
public class DeadlyMonsters
{
    public static final String MOD_ID = "dmonsters";
    public static final String MOD_NAME = "Deadly Monsters";
    public static final String MOD_VERSION = "1.12.2-1.9.5";
    public static final ModCreativeTabs MOD_CREATIVE_TAB = new ModCreativeTabs("dmonsters_tab");

    @SidedProxy(clientSide = "com.dmonsters.proxy.ClientProxy", serverSide = "com.dmonsters.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static DeadlyMonsters instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        ModSounds.init();
        proxy.preInit(event);
        File configFile = new File(event.getSuggestedConfigurationFile().getParentFile(), MOD_ID + ".cfg");
        ModConfig.preInit(configFile);
    }

    public static void registerEvent(Object obj) {
        FMLCommonHandler.instance()
            .bus()
            .register(obj);
        MinecraftForge.EVENT_BUS.register(obj);
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent e)
    {
        ModBlocks.RegistrationHandler.register(e);
        ModItems.RegistrationHandler.register(e);
      //  ModSounds.RegistrationHandler.registerSound(e);
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
        proxy.postInit(e);
    }
}
