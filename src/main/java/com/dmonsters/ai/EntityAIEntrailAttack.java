package com.dmonsters.ai;

import net.minecraft.entity.ai.EntityAIAttackMelee;

import com.dmonsters.entity.EntityEntrail;

public class EntityAIEntrailAttack extends EntityAIAttackMelee
{
    private final EntityEntrail mob;
    private int raiseArmTicks;

    public EntityAIEntrailAttack(EntityEntrail mobIn, double speedIn, boolean longMemoryIn)
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