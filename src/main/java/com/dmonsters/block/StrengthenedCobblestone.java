package com.dmonsters.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.main.ModItems;

public class StrengthenedCobblestone extends Block
{
    public StrengthenedCobblestone()
    {
        super(Material.iron);
        setBlockTextureName(DeadlyMonsters.MOD_ID + ".strengthened_cobblestone");
        setBlockName("strengthened_cobblestone");
        setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
        this.setHardness(10);
        this.setResistance(25);
    }

    @Override
    public void breakBlock(World worldIn, int x, int y, int z, Block blockBroken, int meta)
    {
        super.breakBlock(worldIn, x, y, z, blockBroken, meta);
        ItemStack newItem = new ItemStack(ModItems.rebar, 1);
        EntityItem item = new EntityItem(worldIn, x + 0.5, y + 0.5, z + 0.5, newItem);
        worldIn.spawnEntityInWorld(item);
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }

    @Override
    public StrengthenedCobblestone setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(tab);
        return this;
    }
    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ)
    {
        if (player.isSneaking())
        {
            if (!worldIn.isRemote)
            {
                worldIn.setBlock(x,y,z, Blocks.cobblestone);
            }
            return true;
        }
        else
        {
            return super.onBlockActivated(worldIn, x,y,z, player, side, subX, subY, subZ);
        }
    }
}
