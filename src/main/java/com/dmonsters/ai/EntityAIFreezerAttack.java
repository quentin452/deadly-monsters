package com.dmonsters.ai;

import net.minecraft.entity.ai.EntityAIAttackMelee;

import com.dmonsters.entity.EntityFreezer;

public class EntityAIFreezerAttack extends EntityAIAttackMelee
{
    private final EntityFreezer freezer;
    private int raiseArmTicks;

    public EntityAIFreezerAttack(EntityFreezer freezerIn, double speedIn, boolean longMemoryIn)
    {
        super(freezerIn, speedIn, longMemoryIn);
        this.freezer = freezerIn;
    }

    public void startExecuting()
    {
        super.startExecuting();
        this.raiseArmTicks = 0;
        freezer.setAttaking(true);
    }

    public void resetTask()
    {
        super.resetTask();
        this.freezer.setArmsRaised(false);
        freezer.setAttaking(false);
    }

    public void updateTask()
    {
        super.updateTask();
        ++this.raiseArmTicks;

        this.freezer.setArmsRaised(this.raiseArmTicks >= 5 && this.raiseArmTicks < 10);
    }
}