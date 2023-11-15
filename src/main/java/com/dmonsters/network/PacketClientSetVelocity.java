package com.dmonsters.network;


import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import io.netty.buffer.ByteBuf;

public class PacketClientSetVelocity implements IMessage
{
    private float x, y, z;
    private int entityID;

    public PacketClientSetVelocity()
    {
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        entityID = buf.readInt();
        x = buf.readFloat();
        y = buf.readFloat();
        z = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(entityID);
        buf.writeFloat(x);
        buf.writeFloat(y);
        buf.writeFloat(z);
    }

    public static class Handler implements IMessageHandler<PacketClientSetVelocity, IMessage>
    {
        @Override
        public IMessage onMessage(PacketClientSetVelocity message, MessageContext ctx)
        {
         //   FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message));
            return null;
        }

        private void handle(PacketClientSetVelocity message)
        {
            World world = Minecraft.getMinecraft().theWorld;
            Entity entity = world.getEntityByID(message.entityID);
            entity.setVelocity(message.x, message.y, message.z);
        }
    }
}
