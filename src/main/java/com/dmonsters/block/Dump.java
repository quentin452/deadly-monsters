package com.dmonsters.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.*;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.main.ModBlocks;

public class Dump extends Block
{
    protected static final AxisAlignedBB AABB = AxisAlignedBB.getBoundingBox(0.25, 0.0D, 0.25, 0.75, 0.4, 0.75);

    public Dump() {
        super(Material.cake);
        setBlockTextureName(DeadlyMonsters.MOD_ID + ".dump");
        setBlockName("dump");
        setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
        this.setHardness(1);
        this.setResistance(1);
    }

    public void generateTree(World worldIn, ChunkCoordinates pos, Block state, Random rand, Block saplingType) {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos.posX, pos.posY, pos.posZ)) {
            return;
        }

        WorldGenerator worldgenerator = rand.nextInt(10) == 0 ? new WorldGenBigTree(true) : new WorldGenTrees(true);
        int i = 0;
        int j = 0;
        boolean flag = false;

        if (saplingType == Blocks.sapling) {
            // Use metadata to determine sapling type
            int metadata = worldIn.getBlockMetadata(pos.posX, pos.posY, pos.posZ);

            if (metadata == 1) { // Spruce sapling
                label114:
                for (i = 0; i >= -1; --i) {
                    for (j = 0; j >= -1; --j) {
                        if (this.isTwoByTwoOfType(worldIn, pos, i, j)) {
                            worldgenerator = new WorldGenMegaPineTree(false, rand.nextBoolean());
                            flag = true;
                            break label114;
                        }
                    }
                }

                if (!flag) {
                    i = 0;
                    j = 0;
                    worldgenerator = new WorldGenTaiga2(true);
                }
                else if (metadata == 2) { // Birch sapling
                    worldgenerator = new WorldGenTrees(true);
                }
            } else if (metadata == 3) { // Jungle sapling
                Block logBlock = Blocks.log;
                Block leavesBlock = Blocks.leaves;
                int logBlockID = Block.getIdFromBlock(logBlock);
                int leavesBlockID = Block.getIdFromBlock(leavesBlock);
                worldgenerator = new WorldGenMegaJungle(true, 10, 20, logBlockID, leavesBlockID);
            } else if (metadata == 4) { // Acacia sapling
                worldgenerator = new WorldGenSavannaTree(true);
            } else if (metadata == 5) { // Dark Oak sapling
                label390:
                for (i = 0; i >= -1; --i) {
                    for (j = 0; j >= -1; --j) {
                        if (this.isTwoByTwoOfType(worldIn, pos, i, j)) {
                            worldgenerator = new WorldGenCanopyTree(true);
                            flag = true;
                            break label390;
                        }
                    }
                }

                if (!flag) {
                    return;
                }
            } else if (metadata == 0) { // Oak sapling
                // Handle oak sapling case
            }
        }

        Block airBlock = Blocks.air;

        if (flag) {
            worldIn.setBlock(pos.posX + i, pos.posY, pos.posZ + j, airBlock);
            worldIn.setBlock(pos.posX + i + 1, pos.posY, pos.posZ + j, airBlock);
            worldIn.setBlock(pos.posX + i, pos.posY, pos.posZ + j + 1, airBlock);
            worldIn.setBlock(pos.posX + i + 1, pos.posY, pos.posZ + j + 1, airBlock);
        } else {
            worldIn.setBlock(pos.posX, pos.posY, pos.posZ, airBlock);
        }

        if (!worldgenerator.generate(worldIn, rand, pos.posX + i, pos.posY, pos.posZ + j)) {

            if (flag) {
                worldIn.setBlock(pos.posX + i, pos.posY, pos.posZ + j, state);
                worldIn.setBlock(pos.posX + i + 1, pos.posY, pos.posZ + j, state);
                worldIn.setBlock(pos.posX + i, pos.posY, pos.posZ + j + 1, state);
                worldIn.setBlock(pos.posX + i + 1, pos.posY, pos.posZ + j + 1, state);
            } else {
                worldIn.setBlock(pos.posX, pos.posY, pos.posZ, state);
            }
        }
    }


    public boolean isTypeAt(World worldIn, ChunkCoordinates pos, Block blockType, Block saplingType)
    {
        Block block = worldIn.getBlock(pos.posX, pos.posY, pos.posZ);
        return block == blockType && saplingType == block;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int x, int y, int z)
    {
        return AABB;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        for(int i = 0; i < 1; i++) {
            double motionY = Math.abs(random.nextGaussian() * 0.02D);
            float randX = random.nextFloat();
            float randY = random.nextFloat();
            float randZ = random.nextFloat();
            world.spawnParticle("smoke", x + randX, y + randY, z + randZ, 0, motionY, 0);
        }
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 1;
    }

    @Override
    public int damageDropped(int meta) {
        return (meta);
    }

    @Override
    public Dump setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(tab);
        return this;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        ChunkCoordinates blockPos = new ChunkCoordinates(x + 1, y, z);
        if (checkSaplingBlock(world, blockPos, new ChunkCoordinates(x, y, z)))
            return;

        blockPos = new ChunkCoordinates(x - 1, y, z);
        if (checkSaplingBlock(world, blockPos, new ChunkCoordinates(x, y, z)))
            return;

        blockPos = new ChunkCoordinates(x, y, z + 1);
        if (checkSaplingBlock(world, blockPos, new ChunkCoordinates(x, y, z)))
            return;

        blockPos = new ChunkCoordinates(x, y, z - 1);
        if (checkSaplingBlock(world, blockPos, new ChunkCoordinates(x, y, z)))
            return;
    }

    private boolean checkSaplingBlock(World worldIn, ChunkCoordinates pos, ChunkCoordinates curretPos) {

        Block blockNear = worldIn.getBlock(pos.posX, pos.posY, pos.posZ);

        if(blockNear instanceof BlockSapling) {

            int metadata = worldIn.getBlockMetadata(pos.posX, pos.posY, pos.posZ);

            updateDumpState(worldIn, curretPos);

            Block saplingType;

            switch(metadata) {
                case 0:
                    saplingType = Blocks.sapling;
                    break;
                default:
                    saplingType = Blocks.sapling;
            }

            generateTree(worldIn, pos, blockNear, new Random(), saplingType);

            return true;

        }

        return false;

    }
    private void updateDumpState(World worldIn, ChunkCoordinates curretPos)
    {
        Block block = worldIn.getBlock(curretPos.posX, curretPos.posY, curretPos.posZ);
        if (block instanceof Dump)
        {
            int stateValue = worldIn.getBlockMetadata(curretPos.posX, curretPos.posY, curretPos.posZ);
            stateValue++;
            if (stateValue < 4)
                worldIn.setBlock(curretPos.posX, curretPos.posY, curretPos.posZ, ModBlocks.dump);
            else
                worldIn.setBlock(curretPos.posX, curretPos.posY, curretPos.posZ, Blocks.air);
        }
    }

    private boolean isTwoByTwoOfType(World worldIn, ChunkCoordinates pos, int p_181624_3_, int p_181624_4_)
    {
        Block block1 = worldIn.getBlock(pos.posX + p_181624_3_, pos.posY, pos.posZ + p_181624_4_);
        Block block2 = worldIn.getBlock(pos.posX + p_181624_3_ + 1, pos.posY, pos.posZ + p_181624_4_);
        Block block3 = worldIn.getBlock(pos.posX + p_181624_3_, pos.posY, pos.posZ + p_181624_4_ + 1);
        Block block4 = worldIn.getBlock(pos.posX + p_181624_3_ + 1, pos.posY, pos.posZ + p_181624_4_ + 1);

        return isSapling(block1) && isSapling(block2) && isSapling(block3) && isSapling(block4);
    }

    private boolean isSapling(Block block)
    {
        return block instanceof BlockSapling;
    }

        @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
}
