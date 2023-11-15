package com.dmonsters.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.dmonsters.DeadlyMonsters;

public class FallenLeaderSpine extends Item
{
    public FallenLeaderSpine()
    {
        setUnlocalizedName("fallen_leader_spine");
        setTextureName(DeadlyMonsters.MOD_ID + ".fallen_leader_spine");
        this.setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
        this.maxStackSize = 1;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        target.knockBack(target, 4, attacker.posX - target.posX, attacker.posZ - target.posZ);
        return true;
    }
}
