package com.dmonsters.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.main.ModBlocks;

public class Rebar extends Item
{
    public Rebar()
    {
        setRegistryName("rebar");
        setUnlocalizedName(DeadlyMonsters.MOD_ID + ".rebar");
        this.setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack stack = playerIn.getHeldItem(hand);
        if (!playerIn.canPlayerEdit(pos, facing, stack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            if (worldIn.getBlockState(pos).getBlock() == Blocks.STONE)
            {
                if (!playerIn.capabilities.isCreativeMode)
                {
                    stack.shrink(1);
                }
                worldIn.playSound(playerIn, pos, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                worldIn.setBlockState(pos, ModBlocks.strengthened_stone.getDefaultState(), 11);
            }
            if (worldIn.getBlockState(pos).getBlock() == Blocks.COBBLESTONE)
            {
                if (!playerIn.capabilities.isCreativeMode)
                {
                    stack.shrink(1);
                }
                worldIn.playSound(playerIn, pos, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                worldIn.setBlockState(pos, ModBlocks.strengthened_cobblestone.getDefaultState(), 11);
            }
            stack.damageItem(1, playerIn);
            return EnumActionResult.SUCCESS;
        }
    }
}