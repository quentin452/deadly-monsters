package com.dmonsters.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketHandler
{
    public static SimpleNetworkWrapper INSTANCE = null;
    private static int packetId = 0;

    public static int nextID()
    {
        return packetId++;
    }

    public static void init(String channelName)
    {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
    }

    @SideOnly(Side.CLIENT)
    public static void registerMessages()
    {
        INSTANCE.registerMessage(PacketClientFXUpdate.Handler.class, PacketClientFXUpdate.class, nextID(), Side.CLIENT);
        INSTANCE.registerMessage(PacketClientSetVelocity.Handler.class, PacketClientSetVelocity.class, nextID(), Side.CLIENT);
    }

    public PacketHandler()
    {
    }
}
