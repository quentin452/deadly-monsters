package com.dmonsters.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;

import com.corosus.inv.InvasionManager;
import com.dmonsters.DeadlyMonsters;
import com.dmonsters.main.ModConfig;
import com.dmonsters.network.PacketClientFXUpdate;
import com.dmonsters.network.PacketHandler;

public class SunlightDrop extends Item
{
    public SunlightDrop()
    {
        setRegistryName("sunlight_drop");
        setUnlocalizedName(DeadlyMonsters.MOD_ID + ".sunlight_drop");
        this.setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        ItemStack itemStackIn = playerIn.getHeldItem(hand);
        if (!worldIn.isDaytime())
        {
            if (ModConfig.CATEGORY_HAUNTED_COW.hauntedCowDisableTimeChange && checkInvasion(playerIn))
            {
                Style red = new Style().setColor(TextFormatting.DARK_RED);
                TextComponentTranslation msg = new TextComponentTranslation("msg.dmonsters.haunted_cow_time_disabled");
                msg.setStyle(red);
                playerIn.sendMessage(msg);
                return new ActionResult(EnumActionResult.FAIL, itemStackIn);
            }
            else
            {
                PacketHandler.INSTANCE.sendToAll(new PacketClientFXUpdate(playerIn.getPosition(), PacketClientFXUpdate.Type.SUNLIGHT_USE));
                if (worldIn.getGameRules().getBoolean("doDaylightCycle"))
                {
                    long i = worldIn.getWorldTime();
                    worldIn.setWorldTime(i - i % 24000L);
                }
                itemStackIn.shrink(1);
                return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
            }
        }
        else
        {
            return new ActionResult(EnumActionResult.FAIL, itemStackIn);
        }
    }

    public boolean checkInvasion(EntityPlayer playerIn)
    {
        if (Loader.isModLoaded("hw_inv"))
        {
            if (ModConfig.CATEGORY_GENERAL.disableTimeChangeInvasions)
            {
                return InvasionManager.shouldLockOutFeaturesForPossibleActiveInvasion(playerIn.getEntityWorld());
            }
        }
        return false;
    }
}