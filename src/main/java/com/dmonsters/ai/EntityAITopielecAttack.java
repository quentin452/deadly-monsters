package com.dmonsters.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.dmonsters.entity.EntityTopielec;
import com.dmonsters.main.ModConfig;

public class EntityAITopielecAttack extends EntityAIBase
{
    private final EntityTopielec topielec;
    private final float speed;
    private BlockPos targetPos;
    private int ticks = 0;
    private EntityLivingBase victimEntity;

    public EntityAITopielecAttack(EntityTopielec _owner, float _speed)
    {
        this.topielec = _owner;
        this.speed = _speed;
    }

    public boolean shouldExecute()
    {
        EntityLivingBase victim = this.topielec.getAttackTarget();
        if (victim != null)
        {
            double distance = this.topielec.getDistance(victim.posX, victim.posY, victim.posZ);
            if (distance < 2d)
            {
                victimEntity = victim;
                return true;
            }
        }
        return false;
    }

    public void updateTask()
    {
        double victimX = this.topielec.posX;
        double victimY = this.topielec.posY;
        double victimZ = this.topielec.posZ;
        victimEntity.setPositionAndUpdate(victimX, victimY, victimZ);

        ticks++;
        if (ticks > 40)
        {
            return;
        }
        else
        {
            ticks = 0;
        }
        BlockPos targetPos = findBestPosition();
        //System.out.println(targetPos);
        float[] normVec = normalizeVector(targetPos.subtract(this.topielec.getPosition()));
        //System.out.println(normVec[0] + ", " + victimY + ", " + normVec[2]);
        this.topielec.setMovementVector(normVec[0], normVec[1], normVec[2]);
    }

    private float[] normalizeVector(BlockPos v)
    {
        float length = (float) Math.sqrt((v.getX() * v.getX()) + (v.getY() * v.getY()) + (v.getZ() * v.getZ()));
        float[] newVec = new float[3];
        newVec[0] = (v.getX() / length) * speed;
        newVec[1] = (v.getY() / length) * speed;
        newVec[2] = (v.getZ() / length) * speed;
        return newVec;
    }

    private BlockPos findBestPosition()
    {
        BlockPos victimPos = this.topielec.getPosition();
        BlockPos bestPos = victimPos;
        int searchDistance = ModConfig.topielecSearchDistance;
        int minBoundsX = -searchDistance + victimPos.getX();
        int maxBoundsX = searchDistance + victimPos.getX();
        int minBoundsZ = -searchDistance + victimPos.getZ();
        int maxBoundsZ = searchDistance + victimPos.getZ();
        World worldIn = this.topielec.getEntityWorld();
        int deepestY = victimPos.getY();
        //System.out.println("START " + victimPos);
        //System.out.println(deepestY);
        for (int x = minBoundsX; x < maxBoundsX; x++)
        {
            for (int z = minBoundsZ; z < maxBoundsZ; z++)
            {
                int tempDeepestY = victimPos.getY();
                for (int y = victimPos.getY(); y > 0; y--)
                {
                    BlockPos currPos = new BlockPos(x, y, z);
                    Block block = worldIn.getBlockState(currPos).getBlock();
                    //System.out.println(y + " " + block + ", " + currPos);
                    if (block == Blocks.WATER && y <= tempDeepestY)
                    {
                        tempDeepestY = y;
                    }
                    else
                    {
                        if (tempDeepestY <= deepestY)
                        {
                            deepestY = tempDeepestY;
                            bestPos = currPos;
                        }
                        break;
                    }
                }
            }
        }
        //System.out.println("END: " + bestPos);
        return new BlockPos(bestPos.getX(), victimPos.getY(), bestPos.getZ());
    }
}