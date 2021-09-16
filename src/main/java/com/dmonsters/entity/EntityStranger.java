package com.dmonsters.entity;

import java.util.Random;
import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.dmonsters.ai.DeadlyMonsterAIMelee;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModSounds;

public class EntityStranger extends EntityMob
{
    public static final ResourceLocation LOOT = new ResourceLocation(MainMod.MODID, "stranger");

    public EntityStranger(World worldIn)
    {
        super(worldIn);
        setSize(1.0F, 1.5F);
    }

    @Override
    public void onLivingUpdate()
    {
        if (this.world.isDaytime() && !this.world.isRemote)
        {
            float f = this.getBrightness();
            BlockPos blockpos = this.getRidingEntity() instanceof EntityBoat ? (new BlockPos(this.posX, (double) Math.round(this.posY), this.posZ)).up() : new BlockPos(this.posX, (double) Math.round(this.posY), this.posZ);
            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(blockpos))
            {
                this.setFire(8);
            }
        }
        super.onLivingUpdate();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource dmgSource)
    {
        return ModSounds.STRANGER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return ModSounds.STRANGER_DEATH;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (super.attackEntityAsMob(entityIn))
        {
            this.playSound(ModSounds.STRANGER_ATTACK, 1, 1);
            if (entityIn instanceof EntityPlayer)
            {
                hitPlayer(entityIn);
                entityIn.attackEntityFrom(DamageSource.GENERIC, 10);
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D * ModConfig.CATEGORY_GENERAL.globalSpeedMultiplier * ModConfig.CATEGORY_STRANGER.strangerSpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D * ModConfig.CATEGORY_GENERAL.globalStrengthMultiplier * ModConfig.CATEGORY_STRANGER.strangerStrengthMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D * ModConfig.CATEGORY_GENERAL.globalHealthMultiplier * ModConfig.CATEGORY_STRANGER.strangerHealthMultiplier);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new DeadlyMonsterAIMelee(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSounds.STRANGER_AMBIENT;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LOOT;
    }

    private void applyEntityAI()
    {
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
    }

    private void hitPlayer(Entity entityIn)
    {
        if (world.isRemote)
            return;
        this.playSound(ModSounds.STRANGER_IMPACT, 1, 1);
        Random rnd = new Random();
        float rndNum = rnd.nextFloat() + 0.1F;
        entityIn.addVelocity(0, rndNum, 0);
    }
}