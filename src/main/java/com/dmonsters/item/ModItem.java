package com.dmonsters.item;

import net.minecraft.item.Item;

import com.dmonsters.main.MainMod;

public class ModItem extends Item
{
    public ModItem()
    {
        setRegistryName("mod_item");
        setUnlocalizedName(MainMod.MODID + ".mod_item");
    }
}