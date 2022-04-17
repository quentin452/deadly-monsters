package com.dmonsters;

import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.dmonsters.main.ModCreativeTabs;
import com.dmonsters.main.ModSounds;
import com.dmonsters.proxy.CommonProxy;

@Mod(modid = DeadlyMonsters.MOD_ID, name = DeadlyMonsters.MOD_NAME, version = DeadlyMonsters.MOD_VERSION)
public class DeadlyMonsters
{
    public static final String MOD_ID = "dmonsters";
    public static final String MOD_NAME = "Deadly Monsters";
    public static final String MOD_VERSION = "1.9.4";
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
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e)
    {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
        proxy.postInit(e);
    }
}