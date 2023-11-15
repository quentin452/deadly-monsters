package com.dmonsters.entity.ai;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;

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
        EntityPlayer player = this.owner.worldObj.getClosestPlayerToEntity(this.owner, this.distance);
        this.owner.setAttackTarget(player);

        ChunkCoordinates AABB_01 = new ChunkCoordinates((int) (this.owner.posX - this.distance), (int) (this.owner.posY - this.distance), (int) (this.owner.posZ - this.distance));
        ChunkCoordinates AABB_02 = new ChunkCoordinates((int) (this.owner.posX + this.distance), (int) (this.owner.posY + this.distance), (int) (this.owner.posZ + this.distance));

        AxisAlignedBB AABB = AxisAlignedBB.getBoundingBox(AABB_01.posX, AABB_01.posY, AABB_01.posZ, AABB_02.posX, AABB_02.posY, AABB_02.posZ);

        List list = this.owner.worldObj.getEntitiesWithinAABBExcludingEntity(this.owner, AABB);
        if (list.isEmpty())
        {
            return false;
        }
        else
        {
            for (Object entity : list)
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
