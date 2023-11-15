package com.dmonsters.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.main.ModBlocks;
import net.minecraft.world.chunk.Chunk;

public class MeshFence extends Block
{
    public static final AxisAlignedBB PILLAR_AABB = AxisAlignedBB.getBoundingBox(0.375D, 0.0D, 0.375D, 0.625D, 1.5D, 0.625D);
    public static final AxisAlignedBB SOUTH_AABB =  AxisAlignedBB.getBoundingBox(0.375D, 0.0D, 0.625D, 0.625D, 1.5D, 1.0D);
    public static final AxisAlignedBB WEST_AABB = AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.375D, 0.375D, 1.5D, 0.625D);
    public static final AxisAlignedBB NORTH_AABB = AxisAlignedBB.getBoundingBox(0.375D, 0.0D, 0.0D, 0.625D, 1.5D, 0.375D);
    public static final AxisAlignedBB EAST_AABB = AxisAlignedBB.getBoundingBox(0.625D, 0.0D, 0.375D, 1.0D, 1.5D, 0.625D);
    protected static final AxisAlignedBB[] BOUNDING_BOXES = new AxisAlignedBB[] {
                AxisAlignedBB.getBoundingBox(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D),
                AxisAlignedBB.getBoundingBox(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 1.0D),
                AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D),
                AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.375D, 0.625D, 1.0D, 1.0D),
                AxisAlignedBB.getBoundingBox(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 0.625D),
                AxisAlignedBB.getBoundingBox(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D),
                AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.0D, 0.625D, 1.0D, 0.625D),
                AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D),
                AxisAlignedBB.getBoundingBox(0.375D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D),
                AxisAlignedBB.getBoundingBox(0.375D, 0.0D, 0.375D, 1.0D, 1.0D, 1.0D),
                AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D),
                AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 1.0D),
                AxisAlignedBB.getBoundingBox(0.375D, 0.0D, 0.0D, 1.0D, 1.0D, 0.625D),
                AxisAlignedBB.getBoundingBox(0.375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D),
                AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.625D),
                AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};

    private static int getBoundingBoxIdx(Block block, World world, ChunkCoordinates pos)
    {
        int i = 0;

        if (world.getBlock(pos.posX, pos.posY, pos.posZ - 1) == block)
        {
            i |= 1 << EnumFacing.NORTH.ordinal();
        }

        if (world.getBlock(pos.posX + 1, pos.posY, pos.posZ) == block)
        {
            i |= 1 << EnumFacing.EAST.ordinal();
        }

        if (world.getBlock(pos.posX, pos.posY, pos.posZ + 1) == block)
        {
            i |= 1 << EnumFacing.SOUTH.ordinal();
        }

        if (world.getBlock(pos.posX - 1, pos.posY, pos.posZ) == block)
        {
            i |= 1 << EnumFacing.WEST.ordinal();
        }

        return i;
    }

    public MeshFence()
    {
        super(Material.rock);
        setBlockTextureName(DeadlyMonsters.MOD_ID + ".mesh_fence");
        setBlockName("mesh_fence");
        setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
        this.setHardness(5);
        this.setResistance(5);
    }

    public void addCollisionBoxesToList(World worldIn, int x, int y, int z, AxisAlignedBB mask, List list, Entity collider) {
        Block block = worldIn.getBlock(x, y, z);
        int meta = worldIn.getBlockMetadata(x, y, z);

        super.addCollisionBoxesToList(worldIn, x, y, z, mask, list, collider);

        if (hasBitSet(meta, 0)) {
            addCollisionBoxToList(block, worldIn, x, y, z, mask, list, NORTH_AABB);
        }

        if (hasBitSet(meta, 1)) {
            addCollisionBoxToList(block, worldIn, x, y, z, mask, list, EAST_AABB);
        }

        if (hasBitSet(meta, 2)) {
            addCollisionBoxToList(block, worldIn, x, y, z, mask, list, SOUTH_AABB);
        }

        if (hasBitSet(meta, 3)) {
            addCollisionBoxToList(block, worldIn, x, y, z, mask, list, WEST_AABB);
        }
    }

    private void addCollisionBoxToList(Block block, World worldIn, int x, int y, int z, AxisAlignedBB mask, List<AxisAlignedBB> list, AxisAlignedBB aabb) {
        if (aabb != null && mask.intersectsWith(aabb)) {
            list.add(aabb);
            worldIn.notifyBlockChange(x, y, z, block);
        }
    }

    private boolean hasBitSet(int value, int bitIndex) {
        return ((value >> bitIndex) & 1) == 1;
    }

    public boolean isPassable(IBlockAccess worldIn, ChunkCoordinates pos)
    {
        return false;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }
    @Override
    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
        // North
        int nz = z - 1;
        if (checkIfCanBuildInDirection(world, x, y, nz)) {
            return;
        }
        // East
        if (checkIfCanBuildInDirection(world, x + 1, y, z)) {
            return;
        }
        // West
        int nx = x - 1;
        if (checkIfCanBuildInDirection(world, nx, y, z)) {
            return;
        }
        // South
        if (checkIfCanBuildInDirection(world, x, y, z + 1)) {
            return;
        }
        // Schedule a block update or mark the block for update
        if (world instanceof World) {
            ((World) world).markBlockForUpdate(x, y, z);
        }
    }

    private boolean checkIfCanBuildInDirection(IBlockAccess world, int x, int y, int z) {
        // Add your conditions for whether you can build here or not
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
}
