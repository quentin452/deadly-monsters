package com.dmonsters.entity.ai;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import com.dmonsters.entity.EntityFreezer;

public class EntityAIFreezeEnvironment extends DeadlyMonsterAIBase
{
    private final EntityLiving theEntity;
    private final EntityFreezer freezerEntity;
    int freezeTicks = 0;

    public EntityAIFreezeEnvironment(EntityLiving entitylivingIn)
    {
        this.theEntity = entitylivingIn;
        freezerEntity = (EntityFreezer) entitylivingIn;
    }

    public boolean shouldExecute()
    {
        return true;
    }

    public void updateTask()
    {
        if (freezeTicks++ >= 40)
        {
            freezeAround(-1);
            freezeAround(0);
            freezeAround(1);
            freezeTicks = 0;
        }
    }

    private void freezeAround(int yOffset)
    {
        IBlockState blockToFreez;
        BlockPos blockToFreezePos;
        World worldin = this.theEntity.world;
        double y = this.theEntity.posY;
        int freezingArea = 0;
        if (freezerEntity.getAttacking())
            freezingArea = 2;

        for (int dx = -freezingArea; dx <= freezingArea; ++dx)
        {
            for (int dz = -freezingArea; dz <= freezingArea; ++dz)
            {
                blockToFreezePos = new BlockPos(this.theEntity.posX + dx, y + yOffset, this.theEntity.posZ + dz);
                blockToFreez = worldin.getBlockState(blockToFreezePos);
                if (blockToFreez.getBlock() == Blocks.WATER)
                {
                    worldin.setBlockState(blockToFreezePos, Blocks.ICE.getDefaultState());
                }
                for (int l = 0; l < 4; ++l)
                {
                    int i = MathHelper.floor(this.theEntity.posX + dx + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
                    int j = MathHelper.floor(this.theEntity.posY);
                    int k = MathHelper.floor(this.theEntity.posZ + dz + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
                    BlockPos blockpos = new BlockPos(i, j, k);

                    if (worldin.getBlockState(blockpos).getMaterial() == Material.AIR && Blocks.SNOW_LAYER.canPlaceBlockAt(worldin, blockpos))
                    {
                        worldin.setBlockState(blockpos, Blocks.SNOW_LAYER.getDefaultState());
                    }
                }
            }
        }
    }
}