package com.dmonsters.item;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import com.dmonsters.DeadlyMonsters;

public class UnbornBabyEye extends Item
{
    public UnbornBabyEye()
    {
        setUnlocalizedName("unborn_baby_eye");
        setTextureName(DeadlyMonsters.MOD_ID + ".unborn_baby_eye");
        this.setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (!player.canPlayerEdit(x, y, z, side, stack))
        {
            return false;
        }

        if (!world.isRemote)
        {
            ChunkCoordinates pos = new ChunkCoordinates(x, y, z);
            Block block = world.getBlock(pos.posX, pos.posY, pos.posZ);
            world.setBlockToAir(pos.posX, pos.posY, pos.posZ);
            ItemStack blockItemStack = new ItemStack(Item.getItemFromBlock(block), 1);
            EntityItem itemEntity = new EntityItem(world, pos.posX + 0.5, pos.posY + 0.5, pos.posZ + 0.5, blockItemStack);
            world.spawnEntityInWorld(itemEntity);

            stack.stackSize--;
        }
        return true;
    }
}
