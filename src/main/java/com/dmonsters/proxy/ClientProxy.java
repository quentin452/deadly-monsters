package com.dmonsters.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.dmonsters.main.ModEntities;
import com.dmonsters.network.PacketHandler;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent e)
    {
        super.preInit(e);
        ModEntities.initModels();
        PacketHandler.registerMessages();
    }

    @Override
    public void init(FMLInitializationEvent e)
    {
        super.init(e);
        ModEntities.initHackModels();
    }
}