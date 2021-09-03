package com.dmonsters.ai;

import net.minecraft.entity.ai.EntityAIAttackMelee;

import com.dmonsters.entity.EntityPresent;

public class EntityAIPresent extends EntityAIAttackMelee
{
    private final EntityPresent present;
    private int ticks;
    private boolean ready;

    public EntityAIPresent(EntityPresent presentIn, double speedIn, boolean longMemoryIn)
    {
        super(presentIn, speedIn, longMemoryIn);
        this.present = presentIn;
    }
}