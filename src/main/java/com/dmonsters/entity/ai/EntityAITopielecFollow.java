package com.dmonsters.entity.ai;

import com.dmonsters.entity.EntityTopielec;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChunkCoordinates;

public class EntityAITopielecFollow extends DeadlyMonsterAIBase
{
    private final EntityTopielec topielec;
    private final float speed;

    public EntityAITopielecFollow(EntityTopielec owner, float speed)
    {
        this.topielec = owner;
        this.speed = speed;
    }

    public boolean shouldExecute()
    {
        return this.topielec.getAttackTarget() != null;
    }

    public void updateTask()
    {
        EntityLivingBase target = this.topielec.getAttackTarget();

        if (target != null)
        {
            int targetPosX = (int) target.posX;
            int targetPosY = (int) target.posY;
            int targetPosZ = (int) target.posZ;

            int topielecPosX = (int) this.topielec.posX;
            int topielecPosY = (int) this.topielec.posY;
            int topielecPosZ = (int) this.topielec.posZ;

            ChunkCoordinates resultPos = new ChunkCoordinates(
                targetPosX - topielecPosX,
                targetPosY - topielecPosY,
                targetPosZ - topielecPosZ
            );

            float[] normVec = normalizeVector(resultPos);
            this.topielec.setMovementVector(normVec[0], normVec[1], normVec[2]);

            float yawRad = (float) Math.atan2(normVec[2], normVec[0]);
            float yawDeg = (float) ((yawRad > 0 ? yawRad : (2 * Math.PI + yawRad)) * 360 / (2 * Math.PI));

            if (yawDeg > 0)
            {
                this.topielec.setPositionAndRotation(this.topielec.posX, this.topielec.posY, this.topielec.posZ, yawDeg, yawDeg);
            }
        }
    }

    private float[] normalizeVector(ChunkCoordinates v)
    {
        float length = (float) Math.sqrt((v.posX * v.posX) + (v.posY * v.posY) + (v.posZ * v.posZ));
        float[] newVec = new float[3];
        newVec[0] = (v.posX / length) * speed;
        newVec[1] = (v.posY / length) * speed;
        newVec[2] = (v.posZ / length) * speed;
        return newVec;
    }
}
