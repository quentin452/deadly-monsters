package com.dmonsters.projectile;

import com.dmonsters.main.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityDagon extends EntityThrowable
{
    public EntityDagon(World worldIn)
    {
        super(worldIn);
    }

    public EntityDagon(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityDagon(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    @Override
    protected void onImpact(MovingObjectPosition result) {
        if (result.entityHit != null) {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 5.0F);
        }

        if (!this.worldObj.isRemote) {
            ItemStack newItem = new ItemStack(ModItems.dagon, 1);
            EntityItem item = new EntityItem(worldObj, this.posX, this.posY, this.posZ, newItem);
            worldObj.spawnEntityInWorld(item);
        }

        for (int k = 0; k < 8; ++k) {
            this.worldObj.spawnParticle("crit", this.posX, this.posY, this.posZ, ((double) this.rand.nextFloat() - 0.5D) * 0.08D, ((double) this.rand.nextFloat() - 0.5D) * 0.08D, ((double) this.rand.nextFloat() - 0.5D) * 0.08D);
        }

        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}
