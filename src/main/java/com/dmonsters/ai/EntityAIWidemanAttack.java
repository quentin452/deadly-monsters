package com.dmonsters.ai;

import net.minecraft.entity.ai.EntityAIAttackMelee;

import com.dmonsters.entity.EntityWideman;

public class EntityAIWidemanAttack extends EntityAIAttackMelee
{
    private final EntityWideman mob;
    private int raiseArmTicks;

    public EntityAIWidemanAttack(EntityWideman mobIn, double speedIn, boolean longMemoryIn)
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