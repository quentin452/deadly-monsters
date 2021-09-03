package com.dmonsters.ai;

import net.minecraft.entity.ai.EntityAIAttackMelee;

import com.dmonsters.entity.EntityZombieChicken;

public class EntityAIChickenAttack extends EntityAIAttackMelee
{
    private final EntityZombieChicken chicken;
    private int raiseArmTicks;

    public EntityAIChickenAttack(EntityZombieChicken chickenIn, double speedIn, boolean longMemoryIn)
    {
        super(chickenIn, speedIn, longMemoryIn);
        this.chicken = chickenIn;
    }
}