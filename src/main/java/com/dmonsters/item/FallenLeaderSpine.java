package com.dmonsters.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.dmonsters.main.MainMod;

public class FallenLeaderSpine extends Item
{
    public FallenLeaderSpine()
    {
        setRegistryName("fallen_leader_spine");
        setUnlocalizedName(MainMod.MODID + ".fallen_leader_spine");
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