package com.dmonsters.main;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class ModCreativeTabs extends CreativeTabs
{
    public ModCreativeTabs(String label)
    {
        super(label);
        this.setBackgroundImageName("mod.png");
    }

    @SideOnly(Side.CLIENT)
    public Item getTabIconItem()
    {
        return new ItemStack(ModItems.mod_item).getItem();
    }
}
