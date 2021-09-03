package com.dmonsters.items;

import net.minecraft.item.Item;

import com.dmonsters.main.MainMod;

public class ModItem extends Item
{
    public ModItem()
    {
        setRegistryName("modItem");
        setUnlocalizedName(MainMod.MODID + ".modItem");
        this.setCreativeTab(MainMod.MOD_CREATIVETAB);
    }
}