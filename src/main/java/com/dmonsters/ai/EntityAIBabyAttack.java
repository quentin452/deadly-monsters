package com.dmonsters.ai;

import net.minecraft.entity.ai.EntityAIAttackMelee;

import com.dmonsters.entity.EntityBaby;

public class EntityAIBabyAttack extends EntityAIAttackMelee
{
    private final EntityBaby baby;
    private int raiseArmTicks;

    public EntityAIBabyAttack(EntityBaby babyIn, double speedIn, boolean longMemoryIn)
    {
        super(babyIn, speedIn, longMemoryIn);
        this.baby = babyIn;
    }

    public void startExecuting()
    {
        super.startExecuting();
        this.raiseArmTicks = 0;
        baby.setAttaking(true);
    }

    public void resetTask()
    {
        super.resetTask();
        this.baby.setArmsRaised(false);
        baby.setAttaking(false);
    }

    public void updateTask()
    {
        super.updateTask();
        ++this.raiseArmTicks;

        this.baby.setArmsRaised(this.raiseArmTicks >= 5 && this.raiseArmTicks < 10);
    }
}