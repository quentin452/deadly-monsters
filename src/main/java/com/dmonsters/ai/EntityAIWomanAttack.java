package com.dmonsters.ai;

import net.minecraft.entity.ai.EntityAIAttackMelee;

import com.dmonsters.entity.EntityWoman;

public class EntityAIWomanAttack extends EntityAIAttackMelee
{
    private final EntityWoman mob;
    private int raiseArmTicks;

    public EntityAIWomanAttack(EntityWoman mobIn, double speedIn, boolean longMemoryIn)
    {
        super(mobIn, speedIn, longMemoryIn);
        this.mob = mobIn;
    }

    public void startExecuting()
    {
        super.startExecuting();
        this.raiseArmTicks = 0;
    }
}