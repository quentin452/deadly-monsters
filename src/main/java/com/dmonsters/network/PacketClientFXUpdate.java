package com.dmonsters.network;


import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.dmonsters.main.ModSounds;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.chunk.Chunk;

public class PacketClientFXUpdate implements IMessage
{
    private ChunkCoordinates blockPos;
    private Type FXtype;

    public PacketClientFXUpdate()
    {
    }

    public PacketClientFXUpdate(ChunkCoordinates pos, Type type)
    {
        blockPos = pos;
        FXtype = type;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        blockPos = new ChunkCoordinates(buf.readInt(), buf.readInt(), buf.readInt());
        FXtype = Type.fromInteger(buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(blockPos.posX);
        buf.writeInt(blockPos.posY);
        buf.writeInt(blockPos.posZ);
        buf.writeInt(FXtype.ordinal());
    }

    public enum Type
    {
        SOULEYE,
        DUMP,
        BLOODY_MAIDEN_HEART,
        SUNLIGHT_USE,
        TIME_CHANGE;

        public static Type fromInteger(int x)
        {
            switch (x)
            {
                case 0:
                    return SOULEYE;
                case 1:
                    return DUMP;
                case 2:
                    return BLOODY_MAIDEN_HEART;
                case 3:
                    return SUNLIGHT_USE;
                case 4:
                    return TIME_CHANGE;
            }
            return null;
        }
    }

    public static class Handler implements IMessageHandler<PacketClientFXUpdate, IMessage>
    {
        @Override
        public IMessage onMessage(PacketClientFXUpdate message, MessageContext ctx)
        {
          //  FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message));
            return null;
        }

        private void handle(PacketClientFXUpdate message)
        {
            switch (message.FXtype)
            {
                case SOULEYE:
                    SoulEye(message);
                    break;
                case DUMP:
                    Dump(message);
                    break;
                case BLOODY_MAIDEN_HEART:
                    WomanHeart(message);
                    break;
                case SUNLIGHT_USE:
                    HauntedCowSounds(message, 0);
                    break;
                case TIME_CHANGE:
                    HauntedCowSounds(message, 1);
                    break;
                default:
                    break;
            }
        }

        private void SoulEye(PacketClientFXUpdate message) {
            World world = Minecraft.getMinecraft().theWorld;
            ChunkCoordinates pos = message.blockPos;

       /*     String soundName = ModSounds.BLOCK_SOULEYE_KILL.getSoundName();

            world.playSound(pos.posX, pos.posY, pos.posZ, soundName, 1, 1, false);


        */
            Random rnd = new Random();

            for (int i = 0; i < 15; i++) {
                double motionX = rnd.nextGaussian() * 0.001D;
                double motionY = Math.abs(rnd.nextGaussian() * 0.08D);
                double motionZ = rnd.nextGaussian() * 0.001D;
                float randX = rnd.nextFloat();
                float randY = rnd.nextFloat();
                float randZ = rnd.nextFloat();

                world.spawnParticle("flame",
                    pos.posX + randX,
                    pos.posY + 0.5F + randY,
                    pos.posZ + randZ,
                    motionX,
                    motionY,
                    motionZ
                );
            }
        }
        private void Dump(PacketClientFXUpdate message) {
            World world = Minecraft.getMinecraft().theWorld;
            ChunkCoordinates pos = message.blockPos;

       /*     ResourceLocation soundLocation = ModSounds.DUMP_MAKE.getSoundName();

            if (soundLocation != null) {
                String soundName = soundLocation.toString();
                world.playSound(pos.posX, pos.posY, pos.posZ, soundName, 1, 1, false);
            }

        */
        }

        private void HauntedCowSounds(PacketClientFXUpdate message, int soundType)
        {
            World world = Minecraft.getMinecraft().theWorld;
            ChunkCoordinates pos = message.blockPos;
            switch (soundType)
            {
                case 0:
                  //  world.playSound(pos.posX, pos.posY, pos.posZ, ModSounds.SUNLIGHTDROP_USE, SoundCategory.AMBIENT, 1.0f, 1.0f, false);
                    Random rnd = new Random();
                    for (int i = 0; i < 32; i++)
                    {
                        double motionX = rnd.nextGaussian() * 0.001D;
                        double motionY = Math.abs(rnd.nextGaussian() * 0.08D);
                        double motionZ = rnd.nextGaussian() * 0.001D;
                        float randX = rnd.nextFloat() * (rnd.nextBoolean() ? 1 : -1);
                        float randY = rnd.nextFloat() * (rnd.nextBoolean() ? 1 : -1);
                        float randZ = rnd.nextFloat() * (rnd.nextBoolean() ? 1 : -1);
                        world.spawnParticle("enchantmenttable",
                            pos.posX + randX,
                            pos.posY + 1.5F + randY,
                            pos.posZ + randZ,
                            motionX,
                            motionY,
                            motionZ);
                    }
                    break;
                case 1:
     //               world.playSound(pos.posX, pos.posY, pos.posZ, ModSounds.HAUNTEDCOW_TIMECHANGE, SoundCategory.AMBIENT, 1, 1, false);
                    break;
                default:
                    break;
            }
        }

        private void WomanHeart(PacketClientFXUpdate message)
        {
            World world = Minecraft.getMinecraft().theWorld;
            ChunkCoordinates pos = message.blockPos;
         //   world.playSound(pos.posX, pos.posY, pos.posZ, ModSounds.MAIDEN_ATTACK, SoundCategory.BLOCKS, 0.25F, 1, false);
            Random rnd = new Random();
            for (int i = 0; i < 8; i++)
            {
                double motionX = rnd.nextGaussian() * 0.001D;
                double motionY = Math.abs(rnd.nextGaussian() * 0.08D);
                double motionZ = rnd.nextGaussian() * 0.001D;
                float randX = rnd.nextFloat();
                float randY = rnd.nextFloat();
                float randZ = rnd.nextFloat();
                world.spawnParticle("instantSpell",
                    pos.posX + randX,
                    pos.posY + 0.5F + randY,
                    pos.posZ + randZ,
                    motionX,
                    motionY,
                    motionZ
                );
            }
        }
    }
}
