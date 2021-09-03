package com.dmonsters.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.dmonsters.entity.EntityEntrail;
import com.dmonsters.main.MainMod;

public class EntrailFlesh extends Item
{

    public EntrailFlesh()
    {
        setRegistryName("entrailFlesh");
        setUnlocalizedName(MainMod.MODID + ".entrailFlesh");
        this.setCreativeTab(MainMod.MOD_CREATIVETAB);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        if (!attacker.world.isRemote && !(target instanceof EntityEntrail))
        {
            target.setDead();
            double x, y, z = 0;
            x = target.posX;
            y = target.posY;
            z = target.posZ;
            Entity slime = new EntityEntrail(attacker.world);
            slime.setPosition(x, y, z);
            attacker.world.spawnEntity(slime);
            //--stack.stackSize;
            stack.shrink(1);
        }
        return true;
    }
}
