package com.dmonsters.ai;

import net.minecraft.entity.ai.EntityAIAttackMelee;

import com.dmonsters.entity.EntityStranger;

public class EntityAIStranger extends EntityAIAttackMelee
{
    private final EntityStranger stranger;

    public EntityAIStranger(EntityStranger strangerIn, double speedIn, boolean longMemoryIn)
    {
        super(strangerIn, speedIn, longMemoryIn);
        this.stranger = strangerIn;
    }
}