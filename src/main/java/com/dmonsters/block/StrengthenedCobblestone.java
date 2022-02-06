package com.dmonsters.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.main.ModItems;

public class StrengthenedCobblestone extends Block
{
    public StrengthenedCobblestone()
    {
        super(Material.IRON);
        setUnlocalizedName(DeadlyMonsters.MOD_ID + ".strengthened_cobblestone");
        setRegistryName("strengthened_cobblestone");
        setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
        this.setHardness(10);
        this.setResistance(25);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        super.breakBlock(worldIn, pos, state);
        ItemStack newItem = new ItemStack(ModItems.rebar, 1);
        EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), newItem);
        worldIn.spawnEntity(item);
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (playerIn.isSneaking())
        {
            if (!worldIn.isRemote)
            {
                worldIn.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState());
            }
            return true;
        }
        else
        {
            return super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
        }
    }

    @Override
    public StrengthenedCobblestone setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(tab);
        return this;
    }
}