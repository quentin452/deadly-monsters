package com.dmonsters.entityProjectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import com.dmonsters.main.ModItems;

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
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 5.0F);
        }

        if (!this.world.isRemote)
        {
            ItemStack newItem = new ItemStack(ModItems.dagon, 1);
            EntityItem item = new EntityItem(world, this.posX, this.posY, this.posZ, newItem);
            world.spawnEntity(item);
        }

        for (int k = 0; k < 8; ++k)
        {
            this.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, this.posX, this.posY, this.posZ, ((double) this.rand.nextFloat() - 0.5D) * 0.08D, ((double) this.rand.nextFloat() - 0.5D) * 0.08D, ((double) this.rand.nextFloat() - 0.5D) * 0.08D, Item.getIdFromItem(Items.EGG));
        }

        if (!this.world.isRemote)
        {
            this.setDead();
        }
    }
}