package com.dmonsters.block;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.main.ModBlocks;

public class ChristmasTree extends Block
{
    protected static final AxisAlignedBB BLOCK_COLLISION_AABB = AxisAlignedBB.getBoundingBox(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);

    public ChristmasTree()
    {
        super(Material.cactus);
        setBlockTextureName(DeadlyMonsters.MOD_ID + ".christmas_tree");
        setBlockName("christmas_tree");
        setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
        this.setTickRandomly(true);
        this.setHardness(2);
        this.setResistance(50);
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World worldIn, int x, int y, int z)
    {
        return BLOCK_COLLISION_AABB.offset(x,y,z);
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public void updateTick(World worldIn, int x, int y, int z, Random random)
    {
        float rndNum = random.nextFloat();
        if (rndNum < 0.99F)
        {
            ChunkCoordinates spawnPos = selectPos(worldIn, new ChunkCoordinates(x, y, z));
            if (spawnPos != null)
            {
                worldIn.setBlock(spawnPos.posX, spawnPos.posY, spawnPos.posZ, ModBlocks.present_box);
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

    private ChunkCoordinates selectPos(World worldIn, ChunkCoordinates currPos)
    {
        List<ChunkCoordinates> posList = new ArrayList<>();
        Block block;
        ChunkCoordinates pos;

        // UP
        pos = new ChunkCoordinates(currPos.posX + 1, currPos.posY, currPos.posZ);
        block = worldIn.getBlock(pos.posX, pos.posY, pos.posZ);
        if (block == Blocks.air)
            posList.add(pos);

        // DOWN
        pos = new ChunkCoordinates(currPos.posX - 1, currPos.posY, currPos.posZ);
        block = worldIn.getBlock(pos.posX, pos.posY, pos.posZ);
        if (block == Blocks.air)
            posList.add(pos);

        // RIGHT
        pos = new ChunkCoordinates(currPos.posX, currPos.posY, currPos.posZ + 1);
        block = worldIn.getBlock(pos.posX, pos.posY, pos.posZ);
        if (block == Blocks.air)
            posList.add(pos);

        // LEFT
        pos = new ChunkCoordinates(currPos.posX, currPos.posY, currPos.posZ - 1);
        block = worldIn.getBlock(pos.posX, pos.posY, pos.posZ);
        if (block == Blocks.air)
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
