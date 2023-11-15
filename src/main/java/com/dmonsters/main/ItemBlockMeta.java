package com.dmonsters.main;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMeta extends ItemBlock
{
    public ItemBlockMeta(Block block)
    {
        super(block);
        if (!(block instanceof IMetaBlockName))
        {
            throw new IllegalArgumentException(String.format("The given Block %s is not an instance of ISpecialBlockName!", block.getUnlocalizedName()));
        }
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    public int getMetadata(int damage)
    {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        Block block = Block.getBlockFromItem(stack.getItem());
        return super.getUnlocalizedName(stack) + "." + ((IMetaBlockName) block).getSpecialName(stack);
    }
}
