package com.dmonsters.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import com.dmonsters.entity.ai.EntityAITopielecAttack;
import com.dmonsters.entity.ai.EntityAITopielecFollow;
import com.dmonsters.entity.ai.EntityAITopielecIdle;
import com.dmonsters.entity.ai.EntityAIWaterMobNearestPlayer;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModSounds;

public class EntityTopielec extends EntityMob
{
    public static final ResourceLocation LOOT = new ResourceLocation(MainMod.MOD_ID, "topielec");
    private float targetVectorX;
    private float targetVectorY;
    private float targetVectorZ;
    private double lastWaterX;
    private double lastWaterY;
    private double lastWaterZ;

    public EntityTopielec(World worldIn)
    {
        super(worldIn);
        this.setSize(1F, 1F);
    }

    public boolean canBreatheUnderwater()
    {
        return true;
    }

    public void setMovementVector(float randomMotionVecXIn, float randomMotionVecYIn, float randomMotionVecZIn)
    {
        this.targetVectorX = randomMotionVecXIn;
        this.targetVectorY = randomMotionVecYIn;
        this.targetVectorZ = randomMotionVecZIn;
    }

    public void setLastWaterPosition(double waterX, double waterY, double waterZ)
    {
        this.lastWaterX = waterX;
        this.lastWaterY = waterY;
        this.lastWaterZ = waterZ;
    }

    public boolean hasMovementVector()
    {
        return this.targetVectorX != 0.0F || this.targetVectorY != 0.0F || this.targetVectorZ != 0.0F;
    }

    public boolean isPushedByWater()
    {
        return false;
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAITopielecAttack(this, 0.5F));
        this.tasks.addTask(2, new EntityAITopielecFollow(this, 0.5F));
        this.tasks.addTask(3, new EntityAITopielecIdle(this));
        this.applyEntityAI();
    }

    public int getTalkInterval()
    {
        return 120;
    }

    public void onEntityUpdate()
    {
        int i = this.getAir();
        super.onEntityUpdate();

        if (this.isEntityAlive() && !this.isInWater())
        {
            this.setPosition(lastWaterX, lastWaterY, lastWaterZ);
            this.motionX = 0.0D;
            this.motionZ = 0.0D;
            if (!this.hasNoGravity())
                this.motionY -= 0.5D;
            --i;
            this.setAir(i);

            if (this.getAir() == -20)
            {
                this.setAir(0);
                this.attackEntityFrom(DamageSource.DROWN, 2.0F);
            }
        }
        else
        {
            this.motionX = this.targetVectorX;
            this.motionY = this.targetVectorY;
            this.motionZ = this.targetVectorZ;
            this.setLastWaterPosition(this.posX, this.posY, this.posZ);
            this.setAir(300);
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSounds.TOPIELEC_AMBIENT;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LOOT;
    }

    protected boolean canDespawn()
    {
        return true;
    }

    public boolean isNotColliding()
    {
        return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return ModSounds.TOPIELEC_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return ModSounds.TOPIELEC_DEATH;
    }

    public boolean getCanSpawnHere()
    {
        return this.posY > 45.0D && this.posY < (double) this.world.getSeaLevel() && super.getCanSpawnHere();
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D * ModConfig.CATEGORY_GENERAL.globalSpeedMultiplier * ModConfig.CATEGORY_TOPIELEC.topielecSpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D * ModConfig.CATEGORY_GENERAL.globalStrengthMultiplier * ModConfig.CATEGORY_TOPIELEC.topielecStrengthMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D * ModConfig.CATEGORY_GENERAL.globalHealthMultiplier * ModConfig.CATEGORY_TOPIELEC.topielecHealthMultiplier);
    }

    private void applyEntityAI()
    {
        this.targetTasks.addTask(0, new EntityAIWaterMobNearestPlayer(this, ModConfig.CATEGORY_TOPIELEC.topielecSearchDistance));
    }
}