package com.dmonsters.item;

import net.minecraft.item.Item;

import com.dmonsters.DeadlyMonsters;

public class ModItem extends Item
{
    public ModItem()
    {
        setRegistryName("mod_item");
        setUnlocalizedName(DeadlyMonsters.MOD_ID + ".mod_item");
    }
}