package com.dmonsters.entity.ai;

import java.util.Random;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;

public class DeadlyMonsterAIMelee extends EntityAIAttackMelee
{
    protected Random random = new Random();

    public DeadlyMonsterAIMelee(EntityCreature creature, double speed, boolean useLongMemory)
    {
        super(creature, speed, useLongMemory);
    }

    @Override
    protected void checkAndPerformAttack(EntityLivingBase target, double distanceSquared)
    {
        double maxDistanceSquared = this.getAttackReachSqr(target);

        if (distanceSquared <= maxDistanceSquared && DeadlyMonsterAIBase.canAttack(random, attacker, target))
        {
            super.checkAndPerformAttack(target, distanceSquared);
        }
    }
}