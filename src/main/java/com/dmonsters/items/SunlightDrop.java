package com.dmonsters.items;

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

import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModConfig;
import com.dmonsters.network.PacketClientFXUpdate;
import com.dmonsters.network.PacketHandler;

public class SunlightDrop extends Item
{
    public SunlightDrop()
    {
        setRegistryName("sunlight_drop");
        setUnlocalizedName(MainMod.MODID + ".sunlight_drop");
        this.setCreativeTab(MainMod.MOD_CREATIVETAB);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        ItemStack itemStackIn = playerIn.getHeldItem(hand);
        if (ModConfig.CATEGORY_HAUNTED_COW.hauntedCowDisableTimeChange)
        {
            Style red = new Style().setColor(TextFormatting.DARK_RED);
            TextComponentTranslation msg = new TextComponentTranslation("msg.dmonsters.haunted_cow_time_disabled");
            msg.setStyle(red);
            playerIn.sendMessage(msg);
            return new ActionResult(EnumActionResult.FAIL, itemStackIn);
        }
        PacketHandler.INSTANCE.sendToAll(new PacketClientFXUpdate(playerIn.getPosition(), PacketClientFXUpdate.Type.SUNLIGHT_USE));
        worldIn.setWorldTime(0);
        itemStackIn.shrink(1);
        return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
    }
}