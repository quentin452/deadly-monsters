package com.dmonsters.main;

import com.dmonsters.entity.EntityHauntedCow;
import com.dmonsters.entity.EntityTopielec;
import com.dmonsters.item.Harpoon;
import com.dmonsters.network.PacketClientFXUpdate;
import com.dmonsters.network.PacketHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

public class EventHandler
{
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onPlayerAttack(AttackEntityEvent event) {
        Entity targetEntity = event.target;
        EntityPlayer player = event.entityPlayer;
        World world = player.worldObj;

        if (!world.isRemote) {
            if (!ModConfig.hauntedCowDisabled && targetEntity instanceof EntityHauntedCow) {
                Item heldItem = player.getHeldItem().getItem();

                if (!shouldDisableTimeChange(heldItem)) {
                    return;
                }

                if (world.isDaytime()) {
                    handleDaytimeAttack(world, player);
                }
            }

            if (!ModConfig.topielecDisabled && ModConfig.topielecHarpoonOnly && targetEntity instanceof EntityTopielec) {
                Item heldItem = player.getHeldItem().getItem();
                event.setCanceled(!(heldItem instanceof Harpoon));
            }
        }
    }

    private boolean shouldDisableTimeChange(Item heldItem) {
        return ModConfig.hauntedCowDisableTimeChange ||
            ModConfig.isValidWeapon(heldItem) ||
            heldItem instanceof ItemSword ||
            heldItem instanceof ItemBow;
    }

    private void handleDaytimeAttack(World world, EntityPlayer player) {
        PacketHandler.INSTANCE.sendToAll(new PacketClientFXUpdate(player.getPlayerCoordinates(), PacketClientFXUpdate.Type.TIME_CHANGE));
        if (world.getGameRules().getGameRuleBooleanValue("doDaylightCycle")) {
            long newTime = world.getWorldTime() + 24000L;
            world.setWorldTime((newTime - newTime % 24000L) - 6000L);
        }

        ChatComponentTranslation msg = new ChatComponentTranslation("msg.dmonsters.haunted_cow");
        msg.getChatStyle().setColor(EnumChatFormatting.DARK_RED);
        player.addChatMessage(msg);
    }
}
