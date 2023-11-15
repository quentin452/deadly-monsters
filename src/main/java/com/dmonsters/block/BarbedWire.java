package com.dmonsters.block;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.main.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BarbedWire extends Block
{
    public BarbedWire() {
        super(Material.cactus);
        setBlockTextureName(DeadlyMonsters.MOD_ID + ".barbed_wire");
        setBlockName("barbed_wire");
        setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
        this.setHardness(1);
        this.setResistance(1);
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int x, int y, int z)
    {
        return null;
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        int blockY = y - 1;
        Block blockUnder = world.getBlock(x, blockY, z);
        if(!(blockUnder == ModBlocks.mesh_fence || blockUnder == ModBlocks.mesh_fence_pole)) {
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public int getRenderType() {
        return 3;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return world.getBlock(x, y - 1, z) == ModBlocks.mesh_fence || world.getBlock(x, y - 1, z) == ModBlocks.mesh_fence_pole;
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side) {
        return this.canPlaceBlockAt(world, x, y, z);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        if (placer instanceof EntityPlayer) {
            String message = EnumChatFormatting.DARK_RED + "Your error message here";
            ((EntityPlayer) placer).addChatMessage(new ChatComponentText(message));
        }
    }

    public void onEntityCollidedWithBlock(World worldIn, int x, int y, int z, Entity entityIn)
    {
        entityIn.attackEntityFrom(DamageSource.cactus, 1.0F);
        entityIn.motionX *= 0.2D;
        entityIn.motionZ *= 0.2D;
    }

    public BarbedWire setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(tab);
        return this;
    }
}
