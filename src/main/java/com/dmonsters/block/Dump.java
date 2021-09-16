package com.dmonsters.block;

import java.util.Random;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;

public class Dump extends Block
{
    public static final PropertyInteger STACKS = PropertyInteger.create("stacks", 0, 15);
    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.25, 0.0D, 0.25, 0.75, 0.4, 0.75);

    public Dump()
    {
        super(Material.CAKE);
        setUnlocalizedName(MainMod.MODID + ".dump");
        setRegistryName("dump");
        setCreativeTab(MainMod.MOD_CREATIVETAB);
        this.setHardness(1);
        this.setResistance(1);
        this.setDefaultState(this.blockState.getBaseState().withProperty(STACKS, 0));
    }

    public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand, BlockPlanks.EnumType saplingType)
    {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
        WorldGenerator worldgenerator = rand.nextInt(10) == 0 ? new WorldGenBigTree(true) : new WorldGenTrees(true);
        int i = 0;
        int j = 0;
        boolean flag = false;

        switch (saplingType)
        {
            case SPRUCE:
                label114:

                for (i = 0; i >= -1; --i)
                {
                    for (j = 0; j >= -1; --j)
                    {
                        if (this.isTwoByTwoOfType(worldIn, pos, i, j, BlockPlanks.EnumType.SPRUCE, saplingType))
                        {
                            worldgenerator = new WorldGenMegaPineTree(false, rand.nextBoolean());
                            flag = true;
                            break label114;
                        }
                    }
                }

                if (!flag)
                {
                    i = 0;
                    j = 0;
                    worldgenerator = new WorldGenTaiga2(true);
                }

                break;
            case BIRCH:
                worldgenerator = new WorldGenBirchTree(true, false);
                break;
            case JUNGLE:
                IBlockState iblockstate = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
                IBlockState iblockstate1 = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE);
                worldgenerator = new WorldGenMegaJungle(true, 10, 20, iblockstate, iblockstate1);
                break;
            case ACACIA:
                worldgenerator = new WorldGenSavannaTree(true);
                break;
            case DARK_OAK:
                label390:

                for (i = 0; i >= -1; --i)
                {
                    for (j = 0; j >= -1; --j)
                    {
                        if (this.isTwoByTwoOfType(worldIn, pos, i, j, BlockPlanks.EnumType.DARK_OAK, saplingType))
                        {
                            worldgenerator = new WorldGenCanopyTree(true);
                            flag = true;
                            break label390;
                        }
                    }
                }

                if (!flag)
                {
                    return;
                }

            case OAK:
        }

        IBlockState iblockstate2 = Blocks.AIR.getDefaultState();

        if (flag)
        {
            worldIn.setBlockState(pos.add(i, 0, j), iblockstate2, 4);
            worldIn.setBlockState(pos.add(i + 1, 0, j), iblockstate2, 4);
            worldIn.setBlockState(pos.add(i, 0, j + 1), iblockstate2, 4);
            worldIn.setBlockState(pos.add(i + 1, 0, j + 1), iblockstate2, 4);
        }
        else
        {
            worldIn.setBlockState(pos, iblockstate2, 4);
        }

        if (!worldgenerator.generate(worldIn, rand, pos.add(i, 0, j)))
        {
            if (flag)
            {
                worldIn.setBlockState(pos.add(i, 0, j), state, 4);
                worldIn.setBlockState(pos.add(i + 1, 0, j), state, 4);
                worldIn.setBlockState(pos.add(i, 0, j + 1), state, 4);
                worldIn.setBlockState(pos.add(i + 1, 0, j + 1), state, 4);
            }
            else
            {
                worldIn.setBlockState(pos, state, 4);
            }
        }
    }

    public boolean isTypeAt(World worldIn, BlockPos pos, BlockPlanks.EnumType type, BlockPlanks.EnumType saplingType)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        return iblockstate.getBlock() == this && saplingType == type;
    }

    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(STACKS, meta);
    }

    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(STACKS);
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return AABB;
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        for (int i = 0; i < 1; i++)
        {
            double motionY = Math.abs(rand.nextGaussian() * 0.02D);
            float randX = rand.nextFloat();
            float randY = rand.nextFloat();
            float randZ = rand.nextFloat();
            worldIn.spawnParticle(EnumParticleTypes.SPELL_MOB,
                pos.getX() + randX,
                pos.getY() + randY,
                pos.getZ() + randZ,
                0,
                motionY,
                0
            );
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos p_189540_5_)
    {
        BlockPos blockPos;
        blockPos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
        if (checkSaplingBlock(worldIn, blockPos, pos))
            return;
        blockPos = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
        if (checkSaplingBlock(worldIn, blockPos, pos))
            return;
        blockPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
        if (checkSaplingBlock(worldIn, blockPos, pos))
            return;
        blockPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
        if (checkSaplingBlock(worldIn, blockPos, pos))
            return;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 1;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return getMetaFromState(state);
    }

    @Override
    public Dump setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(tab);
        return this;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, STACKS);
    }

    private boolean checkSaplingBlock(World worldIn, BlockPos pos, BlockPos curretPos)
    {
        IBlockState blockNear = worldIn.getBlockState(pos);
        if (blockNear.getBlock() instanceof BlockSapling)
        {
            updateDumpState(worldIn, curretPos);
            BlockPlanks.EnumType test = (BlockPlanks.EnumType) blockNear.getActualState(worldIn, pos).getProperties().values().asList().get(1);
            generateTree(worldIn, pos, blockNear, new Random(), test);
            return true;
        }
        return false;
    }

    private void updateDumpState(World worldIn, BlockPos curretPos)
    {
        IBlockState block = worldIn.getBlockState(curretPos);
        if (!(block.getBlock() instanceof Dump))
            return;
        int stateValue = block.getValue(STACKS);
        stateValue++;
        if (stateValue < 4)
            worldIn.setBlockState(curretPos, ModBlocks.dump.getStateFromMeta(stateValue));
        else
            worldIn.setBlockState(curretPos, Blocks.AIR.getDefaultState());
    }

    private boolean isTwoByTwoOfType(World worldIn, BlockPos pos, int p_181624_3_, int p_181624_4_, BlockPlanks.EnumType type, BlockPlanks.EnumType saplingType)
    {
        return this.isTypeAt(worldIn, pos.add(p_181624_3_, 0, p_181624_4_), type, saplingType) && this.isTypeAt(worldIn, pos.add(p_181624_3_ + 1, 0, p_181624_4_), type, saplingType) && this.isTypeAt(worldIn, pos.add(p_181624_3_, 0, p_181624_4_ + 1), type, saplingType) && this.isTypeAt(worldIn, pos.add(p_181624_3_ + 1, 0, p_181624_4_ + 1), type, saplingType);
    }
}