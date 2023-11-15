package com.dmonsters.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.EntityEntrail;

public class EntrailFlesh extends Item
{
    public EntrailFlesh()
    {
        setUnlocalizedName("entrail_flesh");
        setTextureName(DeadlyMonsters.MOD_ID + ".entrail_flesh");
        this.setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        if (!attacker.worldObj.isRemote && !(target instanceof EntityEntrail))
        {
            target.setDead();
            double x, y, z;
            x = target.posX;
            y = target.posY;
            z = target.posZ;
            Entity slime = new EntityEntrail(attacker.worldObj);
            slime.setPosition(x, y, z);
            attacker.worldObj.spawnEntityInWorld(slime);

            if (attacker instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) attacker;
                if (!player.capabilities.isCreativeMode)
                {
                    stack.stackSize--;
                }
            }
        }
        return true;
    }

}
