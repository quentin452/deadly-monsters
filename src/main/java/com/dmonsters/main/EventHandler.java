package com.dmonsters.main;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemSword;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.SpecialSpawn;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.dmonsters.entity.EntityHauntedCow;
import com.dmonsters.entity.EntityTopielec;
import com.dmonsters.items.Harpoon;
import com.dmonsters.network.PacketClientFXUpdate;
import com.dmonsters.network.PacketHandler;

public class EventHandler
{
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onEntitySpawn(SpecialSpawn e)
    {
        if (ModConfig.topielecDisabled)
        {
            return;
        }
        Entity squid = e.getEntity();
        if (squid instanceof EntitySquid)
        {
            Random rnd = new Random();
            int rndInt = rnd.nextInt(100);
            if (rndInt < ModConfig.topielecSpawnChance)
            {
                spawnEntity(squid, new EntityTopielec(squid.getEntityWorld()));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onPlayerAttack(AttackEntityEvent e)
    {
        Entity entity = e.getTarget();
        if (!e.getEntity().getEntityWorld().isRemote)
        {
            if (!ModConfig.hauntedCowDisabled && entity instanceof EntityHauntedCow)
            {
                World world = entity.getEntityWorld();
                EntityPlayer player = e.getEntityPlayer();
                Item itemClass = player.getHeldItemMainhand().getItem();
                if (ModConfig.hauntedCowDisableTimeChange || itemClass instanceof ItemSword || itemClass instanceof ItemBow)
                {
                    return;
                }
                Style red = new Style().setColor(TextFormatting.DARK_RED);
                TextComponentTranslation msg = new TextComponentTranslation("msg.dmonsters.hauntedcow");
                msg.setStyle(red);
                PacketHandler.INSTANCE.sendToAll(new PacketClientFXUpdate(player.getPosition(), PacketClientFXUpdate.Type.TIME_CHANGE));
                long worldTime = world.getWorldTime();
                while (worldTime % 18000 != 0)
                {
                    worldTime++;
                }
                world.setWorldTime(worldTime);
                player.sendMessage(msg);
            }
        }
        if (!ModConfig.topielecDisabled && ModConfig.topielecHarpoonOnly && entity instanceof EntityTopielec)
        {
            EntityPlayer player = e.getEntityPlayer();
            Item itemClass = player.getHeldItemMainhand().getItem();
            e.setCanceled(!(itemClass instanceof Harpoon));
        }
    }

    private void spawnEntity(Entity targetEntity, Entity entity)
    {
        World worldIn = targetEntity.getEntityWorld();
        EntityLiving entityliving = (EntityLiving) entity;
        entity.setLocationAndAngles(targetEntity.posX, targetEntity.posY, targetEntity.posZ, MathHelper.wrapDegrees(worldIn.rand.nextFloat() * 360.0F), 0.0F);
        entityliving.rotationYawHead = entityliving.rotationYaw;
        entityliving.renderYawOffset = entityliving.rotationYaw;
        entityliving.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityliving)), null);
        entityliving.playLivingSound();
        worldIn.spawnEntity(entity);
    }
}