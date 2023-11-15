package com.dmonsters.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.dmonsters.entity.EntityTopielec;
import com.dmonsters.main.ModConfig;

public class EntityAITopielecAttack extends DeadlyMonsterAIBase
{
    private final EntityTopielec topielec;
    private final float speed;
    private final int searchDistance = ModConfig.topielecSearchDistance;
    private int ticks;
    private EntityPlayer playerEntity;

    public EntityAITopielecAttack(EntityTopielec owner, float speed)
    {
        this.topielec = owner;
        this.speed = speed;
    }

    public boolean shouldExecute() {
        if (this.topielec.getAttackTarget() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) this.topielec.getAttackTarget();
            if (player != null && !isPlayerCreative(player) && !player.isRiding()) {
                double distance = this.topielec.getDistance(player.posX, player.posY, player.posZ);
                if (distance < 2d) {
                    playerEntity = player;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isPlayerCreative(EntityPlayer player) {
        return player.capabilities.isCreativeMode;
    }

    public void updateTask()
    {
        double myX = this.topielec.posX;
        double myY = this.topielec.posY;
        double myZ = this.topielec.posZ;
        playerEntity.setPositionAndUpdate(myX, myY, myZ);

        ticks++;
        if (ticks > 40)
            return;
        else
            ticks = 0;
        ChunkCoordinates targetPos = findBestPosition();
        //System.out.println(targetPos);
        float[] normVec = normalizeVector(targetPos.posX - this.topielec.posX, targetPos.posY - this.topielec.posY, targetPos.posZ - this.topielec.posZ);
        //System.out.println(normVec[0] + ", " + myY + ", " + normVec[2]);
        this.topielec.setMovementVector(normVec[0], normVec[1], normVec[2]);
    }

    private float[] normalizeVector(double x, double y, double z) {
        double length = Math.sqrt(x * x + y * y + z * z);
        if (length != 0) {
            return new float[]{(float) (x / length), (float) (y / length), (float) (z / length)};
        } else {
            return new float[]{0.0F, 0.0F, 0.0F};
        }
    }

    private ChunkCoordinates findBestPosition()
    {
        ChunkCoordinates myPos = new ChunkCoordinates(MathHelper.floor_double(this.topielec.posX), MathHelper.floor_double(this.topielec.posY), MathHelper.floor_double(this.topielec.posZ));
        ChunkCoordinates bestPos = myPos;
        int minBoundsX = -searchDistance + myPos.posX;
        int maxBoundsX = searchDistance + myPos.posX;
        int minBoundsZ = -searchDistance + myPos.posZ;
        int maxBoundsZ = searchDistance + myPos.posZ;
        World worldIn = this.topielec.worldObj;
        int deepestY = myPos.posY;

        for (int x = minBoundsX; x < maxBoundsX; x++)
        {
            for (int z = minBoundsZ; z < maxBoundsZ; z++)
            {
                int tempDeepestY = myPos.posY;
                for (int y = myPos.posY; y > 0; y--)
                {
                    ChunkCoordinates currPos = new ChunkCoordinates(x, y, z);
                    Block block = worldIn.getBlock(currPos.posX, currPos.posY, currPos.posZ);

                    if (block == Blocks.water && y <= tempDeepestY)
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
        return new ChunkCoordinates(bestPos.posX, myPos.posY, bestPos.posZ);
    }
}
