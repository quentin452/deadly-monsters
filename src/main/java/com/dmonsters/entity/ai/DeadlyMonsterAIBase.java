package com.dmonsters.entity.ai;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.Vec3d;

public abstract class DeadlyMonsterAIBase extends EntityAIBase
{
    public static boolean canAttack(Random random, EntityLivingBase attacker, EntityLivingBase target)
    {
        if (attacker.world != target.world || attacker.world == null)
        {
            return false;
        }
        int i = 0;
        float heightFraction = 0.5F;
        do
        {
            Vec3d start = new Vec3d(attacker.posX, attacker.lastTickPosY + (heightFraction * attacker.height), attacker.posZ);
            Vec3d end = new Vec3d(target.posX, target.lastTickPosY + (heightFraction * target.height), target.posZ);
            if (attacker.world.rayTraceBlocks(start, end, false, true, false) == null)
            {
                return true;
            }
            heightFraction = random.nextFloat();
            i++;
        } while (i < 3);
        return false;
    }
}