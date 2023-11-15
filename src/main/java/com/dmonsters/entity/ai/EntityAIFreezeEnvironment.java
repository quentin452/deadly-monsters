package com.dmonsters.entity.ai;

import com.dmonsters.entity.EntityFreezer;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

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

    private void freezeAround(int yOffset) {
        Block blockToFreeze;
        ChunkCoordinates blockToFreezePos;
        World worldIn = this.theEntity.worldObj;
        double y = this.theEntity.posY;
        int freezingArea = 0;

        if (freezerEntity.getAttacking()) {
            freezingArea = 2;
        }

        for (int dx = -freezingArea; dx <= freezingArea; ++dx) {
            for (int dz = -freezingArea; dz <= freezingArea; ++dz) {
                blockToFreezePos = new ChunkCoordinates(MathHelper.floor_double(this.theEntity.posX) + dx, MathHelper.floor_double(y) + yOffset, MathHelper.floor_double(this.theEntity.posZ) + dz);
                blockToFreeze = worldIn.getBlock(blockToFreezePos.posX, blockToFreezePos.posY, blockToFreezePos.posZ);

                if (blockToFreeze == Blocks.water) {
                    worldIn.setBlock(blockToFreezePos.posX, blockToFreezePos.posY, blockToFreezePos.posZ, Blocks.ice);
                }

                for (int l = 0; l < 4; ++l) {
                    int i = MathHelper.floor_double(this.theEntity.posX) + dx + (int) ((l % 2 * 2 - 1) * 0.25F);
                    int j = MathHelper.floor_double(y);
                    int k = MathHelper.floor_double(this.theEntity.posZ) + dz + (int) ((l / 2 % 2 * 2 - 1) * 0.25F);
                    ChunkCoordinates blockPos = new ChunkCoordinates(i, j, k);

                    if (worldIn.isAirBlock(blockPos.posX, blockPos.posY, blockPos.posZ) && Blocks.snow_layer.canPlaceBlockAt(worldIn, blockPos.posX, blockPos.posY, blockPos.posZ)) {
                        worldIn.setBlock(blockPos.posX, blockPos.posY, blockPos.posZ, Blocks.snow_layer);
                    }
                }
            }
        }
    }
}
