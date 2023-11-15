package com.dmonsters.entity.ai;

import java.util.Random;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.attributes.IAttributeInstance;

public class DeadlyMonsterAIMelee extends EntityAIAttackOnCollide
{
    protected int attackTick;
    protected Random random = new Random();
    protected EntityCreature attacker;

    public DeadlyMonsterAIMelee(EntityCreature creature, double speed, boolean useLongMemory)
    {
        super(creature, speed, useLongMemory);
        this.attacker = creature;
    }

    @Override
    public void updateTask()
    {
        EntityLivingBase target = this.attacker.getAttackTarget();
        double distanceSquared = this.attacker.getDistanceSq(target.posX, target.boundingBox.minY, target.posZ);
        double maxDistanceSquared = getAttackReachSqr(target);

        if (distanceSquared <= maxDistanceSquared && DeadlyMonsterAIBase.canAttack(random, attacker, target))
        {
            this.attackTick = Math.max(this.attackTick - 1, 0);

            if (distanceSquared <= maxDistanceSquared && this.attackTick <= 20)
            {
                this.attackTick = 20;

                if (this.attacker.getHeldItem() != null)
                {
                    this.attacker.swingItem();
                }

                this.attacker.attackEntityAsMob(target);
            }
        }
        else
        {
            super.updateTask();
        }
    }

    private double getAttackReachSqr(EntityLivingBase target)
    {
        IAttributeInstance attribute = this.attacker.getEntityAttribute(SharedMonsterAttributes.followRange);
        return attribute == null ? 0.0D : attribute.getAttributeValue() * attribute.getAttributeValue();
    }
}
