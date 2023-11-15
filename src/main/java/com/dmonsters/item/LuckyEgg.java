package com.dmonsters.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.projectile.EntityLuckyEgg;

public class LuckyEgg extends Item
{
    public LuckyEgg()
    {
        setUnlocalizedName("lucky_egg");
        setTextureName(DeadlyMonsters.MOD_ID + ".lucky_egg");
        this.setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player)
    {
        if (!player.capabilities.isCreativeMode)
        {
            itemStackIn.stackSize--;
        }
        worldIn.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isRemote)
        {
            EntityLuckyEgg entityegg = new EntityLuckyEgg(worldIn, player);
            entityegg.setThrowableHeading(player.rotationYaw, player.rotationPitch, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntityInWorld(entityegg);
        }
        player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
        return itemStackIn;
    }
}
