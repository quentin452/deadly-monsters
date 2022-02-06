package com.dmonsters.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;

public class ChristmasTree extends Block
{
    protected static final AxisAlignedBB BLOCK_COLLISION_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);

    public ChristmasTree()
    {
        super(Material.CACTUS);
        setUnlocalizedName(MainMod.MOD_ID + ".christmas_tree");
        setRegistryName("christmas_tree");
        setCreativeTab(MainMod.MOD_CREATIVE_TAB);
        this.setTickRandomly(true);
        this.setHardness(2);
        this.setResistance(50);
    }

    public boolean isFullCube(IBlockState state)
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos)
    {
        return BLOCK_COLLISION_AABB.offset(pos);
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        Random random = new Random();
        float rndNum = random.nextFloat();
        if (rndNum < 0.99F)
        {
            BlockPos spawnPos = selectPos(worldIn, pos);
            if (spawnPos != null)
            {
                worldIn.setBlockState(spawnPos, ModBlocks.present_box.getDefaultState());
            }
        }
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 1;
    }

    @Override
    public ChristmasTree setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(tab);
        return this;
    }

    private BlockPos selectPos(World worldIn, BlockPos currPos)
    {
        List<BlockPos> posList = new ArrayList<BlockPos>();
        Block block;
        BlockPos pos;
        //UP
        pos = new BlockPos(currPos.getX() + 1, currPos.getY(), currPos.getZ());
        block = worldIn.getBlockState(pos).getBlock();
        if (block == Blocks.AIR)
            posList.add(pos);
        //DOWN
        pos = new BlockPos(currPos.getX() - 1, currPos.getY(), currPos.getZ());
        block = worldIn.getBlockState(pos).getBlock();
        if (block == Blocks.AIR)
            posList.add(pos);
        //RIGHT
        pos = new BlockPos(currPos.getX(), currPos.getY(), currPos.getZ() + 1);
        block = worldIn.getBlockState(pos).getBlock();
        if (block == Blocks.AIR)
            posList.add(pos);
        //LEFT
        pos = new BlockPos(currPos.getX(), currPos.getY(), currPos.getZ() - 1);
        block = worldIn.getBlockState(pos).getBlock();
        if (block == Blocks.AIR)
            posList.add(pos);

        if (posList.size() > 0)
        {
            Random rand = new Random();
            int rndNum = rand.nextInt(posList.size());
            return posList.get(rndNum);
        }
        return null;
    }
}