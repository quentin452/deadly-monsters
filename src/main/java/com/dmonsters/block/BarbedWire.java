package com.dmonsters.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.main.ModBlocks;

public class BarbedWire extends Block
{
    public BarbedWire()
    {
        super(Material.CACTUS);
        setUnlocalizedName(DeadlyMonsters.MOD_ID + ".barbed_wire");
        setRegistryName("barbed_wire");
        setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
        this.setHardness(1);
        this.setResistance(1);
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos p_189540_5_)
    {
        BlockPos blockUnderPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
        IBlockState blockUnder = worldIn.getBlockState(blockUnderPos);
        if (!(blockUnder.getBlock() == ModBlocks.mesh_fence || blockUnder.getBlock() == ModBlocks.mesh_fence_pole))
        {
            worldIn.destroyBlock(pos, true);
        }
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        if (!worldIn.isRemote)
        {
            BlockPos blockUnderPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
            IBlockState blockUnder = worldIn.getBlockState(blockUnderPos);
            if (blockUnder.getBlock() == ModBlocks.mesh_fence || blockUnder.getBlock() == ModBlocks.mesh_fence_pole)
                return ModBlocks.barbed_wire.getDefaultState();
            if (placer instanceof EntityPlayer)
            {
                Style red = new Style().setColor(TextFormatting.DARK_RED);
                TextComponentTranslation test = new TextComponentTranslation("msg.dmonsters.barbed_wire.error");
                test.setStyle(red);
                placer.sendMessage(test);
            }
        }
        return Blocks.AIR.getDefaultState();
    }

    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        entityIn.attackEntityFrom(DamageSource.CACTUS, 1.0F);
        entityIn.motionX *= 0.2D;
        entityIn.motionZ *= 0.2D;
    }

    public BarbedWire setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(tab);
        return this;
    }
}