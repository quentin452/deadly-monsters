package com.dmonsters.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.dmonsters.main.MainMod;

public class WidemanSpine extends Item
{

    public WidemanSpine()
    {
        setRegistryName("widemanSpine");
        setUnlocalizedName(MainMod.MODID + ".widemanSpine");
        this.setCreativeTab(MainMod.MOD_CREATIVETAB);
        this.maxStackSize = 1;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        target.knockBack(target, 4, attacker.posX - target.posX, attacker.posZ - target.posZ);
        return true;
    }
}