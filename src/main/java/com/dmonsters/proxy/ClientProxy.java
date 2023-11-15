package com.dmonsters.proxy;
import com.dmonsters.main.ModEntities;
import com.dmonsters.main.RegisterModels;
import com.dmonsters.network.PacketHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

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
    public void init(FMLInitializationEvent e) {

        super.init(e);

        ModEntities.initHackModels();

        RegisterModels.registerModels(e);

    }
}
