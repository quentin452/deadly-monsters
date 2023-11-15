package com.dmonsters.item;

import net.minecraft.block.Block;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.main.ModBlocks;

public class Rebar extends Item
{
    public Rebar()
    {
        setUnlocalizedName("rebar");
        setTextureName(DeadlyMonsters.MOD_ID + ".rebar");
        this.setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (!player.canPlayerEdit(x, y, z, side, stack))
        {
            return false; // Indicate failure
        }
        else
        {
            ChunkCoordinates pos = new ChunkCoordinates(x, y, z);
            Block block = world.getBlock(x, y, z);

            if (block == Blocks.stone)
            {
                if (!player.capabilities.isCreativeMode)
                {
                    stack.stackSize--; // Reduce stack size
                }

                world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, "random.anvil_land", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                world.setBlock(x, y, z, ModBlocks.strengthened_stone, 11, 3);
            }

            if (block == Blocks.cobblestone)
            {
                if (!player.capabilities.isCreativeMode)
                {
                    stack.stackSize--; // Reduce stack size
                }

                world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, "random.anvil_land", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                world.setBlock(x,y,z, ModBlocks.strengthened_cobblestone, 11,3);
            }

            stack.damageItem(1, player);
            return true; // Indicate success
        }
    }
}
