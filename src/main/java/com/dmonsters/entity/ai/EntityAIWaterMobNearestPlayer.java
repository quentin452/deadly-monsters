package com.dmonsters.entity.ai;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class EntityAIWaterMobNearestPlayer extends DeadlyMonsterAIBase
{
    private final EntityCreature owner;
    private final float distance;
    private EntityPlayer attackTarget;

    public EntityAIWaterMobNearestPlayer(EntityCreature owner, float distance)
    {
        this.owner = owner;
        this.distance = distance;
    }

    public boolean shouldExecute()
    {
        EntityPlayer player = this.owner.world.getClosestPlayerToEntity(this.owner, this.distance);
        this.owner.setAttackTarget(player);
        BlockPos AABB_01 = new BlockPos(this.owner.posX - this.distance, this.owner.posY - this.distance, this.owner.posZ - this.distance);
        BlockPos AABB_02 = new BlockPos(this.owner.posX + this.distance, this.owner.posY + this.distance, this.owner.posZ + this.distance);
        AxisAlignedBB AABB = new AxisAlignedBB(AABB_01, AABB_02);
        List<Entity> list = this.owner.world.getEntitiesWithinAABBExcludingEntity(this.owner, AABB);
        if (list.isEmpty())
        {
            return false;
        }
        else
        {
            for (Entity entity : list)
            {
                if (entity instanceof EntityPlayer)
                {
                    attackTarget = (EntityPlayer) entity;
                    return true;
                }
            }
        }
        return false;
    }

    public void startExecuting()
    {
        this.owner.setAttackTarget(this.attackTarget);
        super.startExecuting();
    }
}