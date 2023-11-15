package com.dmonsters.entity.ai;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public abstract class DeadlyMonsterAIBase extends EntityAIBase
{
    public static boolean canAttack(Random random, EntityLivingBase attacker, EntityLivingBase target)
    {
        if (attacker.worldObj != target.worldObj || attacker.worldObj == null)
        {
            return false;
        }
        int i = 0;
        float heightFraction = 0.5F;
        do
        {
            Vec3 start = Vec3.createVectorHelper(attacker.posX, attacker.lastTickPosY + (heightFraction * attacker.height), attacker.posZ);
            Vec3 end = Vec3.createVectorHelper(target.posX, target.lastTickPosY + (heightFraction * target.height), target.posZ);
            MovingObjectPosition result = attacker.worldObj.rayTraceBlocks(start, end);
            if (result == null)
            {
                return true;
            }
            heightFraction = random.nextFloat();
            i++;
        } while (i < 3);
        return false;
    }
}
