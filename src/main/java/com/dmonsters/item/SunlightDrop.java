package com.dmonsters.item;

import cpw.mods.fml.common.Loader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import com.dmonsters.DeadlyMonsters;
import com.dmonsters.main.ModConfig;
import com.dmonsters.network.PacketClientFXUpdate;
import com.dmonsters.network.PacketHandler;

public class SunlightDrop extends Item
{
    public SunlightDrop()
    {
        setUnlocalizedName("sunlight_drop");
        setTextureName(DeadlyMonsters.MOD_ID + ".sunlight_drop");
        this.setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        // Return the original ItemStack if it's daytime
        if (!world.isDaytime())
        {
            // Return the modified ItemStack on success
            if (ModConfig.hauntedCowDisableTimeChange)
            {
                String msg = "msg.dmonsters.haunted_cow_time_disabled";
                player.addChatMessage(new ChatComponentTranslation(msg));
            }
            else
            {
                PacketHandler.INSTANCE.sendToAll(new PacketClientFXUpdate(player.getPlayerCoordinates(), PacketClientFXUpdate.Type.SUNLIGHT_USE));
                if (world.getGameRules().getGameRuleBooleanValue("doDaylightCycle"))
                {
                    long worldTime = world.getWorldTime();
                    world.setWorldTime(worldTime - worldTime % 24000L);
                }
                stack.stackSize -= 1; // Decrease the stack size
            }
        }
        return stack; // Return the original ItemStack on failure
    }
}
