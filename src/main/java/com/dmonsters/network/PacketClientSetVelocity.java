package com.dmonsters.network;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import io.netty.buffer.ByteBuf;

public class PacketClientSetVelocity implements IMessage
{
    private float x, y, z;
    private int entityID;

    public PacketClientSetVelocity()
    {
    }

    public PacketClientSetVelocity(float _x, float _y, float _z, int id)
    {
        entityID = id;
        x = _x;
        y = _y;
        z = _z;
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
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketClientSetVelocity message, MessageContext ctx)
        {
            World world = Minecraft.getMinecraft().world;
            Entity entity = world.getEntityByID(message.entityID);
            entity.setVelocity(message.x, message.y, message.z);
        }
    }
}