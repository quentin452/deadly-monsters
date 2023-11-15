package com.dmonsters.item;

import net.minecraft.item.Item;

import com.dmonsters.DeadlyMonsters;

public class ModItem extends Item
{
    public ModItem()
    {
        setUnlocalizedName("mod_item");
        setTextureName(DeadlyMonsters.MOD_ID + ".mod_item");
    }
}
