package com.dmonsters.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAIWaterMobNearestPlayer extends EntityAIBase
{
    private final EntityCreature owner;
    private final float distance;
    private EntityPlayer attackTarget;

    public EntityAIWaterMobNearestPlayer(EntityCreature _owner, float _distance)
    {
        this.owner = _owner;
        this.distance = _distance;
    }

    public boolean shouldExecute()
    {
        EntityPlayer player = this.owner.world.getClosestPlayerToEntity(this.owner, this.distance);
        this.owner.setAttackTarget(player);
        return false;
    }

    public void startExecuting()
    {
        this.owner.setAttackTarget(this.attackTarget);
        super.startExecuting();
    }
}