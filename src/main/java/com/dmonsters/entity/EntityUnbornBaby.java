package com.dmonsters.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.dmonsters.ai.DeadlyMonsterAIMelee;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModSounds;

public class EntityUnbornBaby extends EntityMob
{
    public static final ResourceLocation LOOT = new ResourceLocation(MainMod.MODID, "unborn_baby");
    private static final DataParameter<Boolean> ARMS_RAISED = EntityDataManager.createKey(EntityUnbornBaby.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.createKey(EntityUnbornBaby.class, DataSerializers.BOOLEAN);
    private int blindRefreshTick = 0;

    public EntityUnbornBaby(World worldIn)
    {
        super(worldIn);
        setSize(0.9F, 1.95F);
    }

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
        if (ModConfig.CATEGORY_UNBORN_BABY.babyBlindness && !this.world.isRemote && getAttaking() && this.getAttackTarget() != null)
        {
            if (blindRefreshTick == 20)
            {
                blindRefreshTick = 0;
                this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100));
            }
            else
            {
                blindRefreshTick++;
            }
        }
        super.onLivingUpdate();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource amageSource)
    {
        return ModSounds.BABY_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return ModSounds.BABY_DEATH;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (super.attackEntityAsMob(entityIn))
        {
            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 600));
            this.playSound(ModSounds.BABY_ATTACK, 1, 1);
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
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D * ModConfig.CATEGORY_GENERAL.globalSpeedMultiplier * ModConfig.CATEGORY_UNBORN_BABY.babySpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(12.0D * ModConfig.CATEGORY_GENERAL.globalStrengthMultiplier * ModConfig.CATEGORY_UNBORN_BABY.babyStrengthMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D * ModConfig.CATEGORY_GENERAL.globalHealthMultiplier * ModConfig.CATEGORY_UNBORN_BABY.babyHealthMultiplier);
    }

    @SideOnly(Side.CLIENT)
    public boolean isArmsRaised()
    {
        return this.getDataManager().get(ARMS_RAISED);
    }

    public void setArmsRaised(boolean armsRaised)
    {
        this.getDataManager().set(ARMS_RAISED, armsRaised);
    }

    public boolean getAttaking()
    {
        return this.getDataManager().get(ATTACKING);
    }

    public void setAttaking(boolean mode)
    {
        this.getDataManager().set(ATTACKING, mode);
    }

    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
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
    protected void entityInit()
    {
        super.entityInit();
        this.getDataManager().register(ARMS_RAISED, Boolean.FALSE);
        this.getDataManager().register(ATTACKING, Boolean.FALSE);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSounds.BABY_AMBIENT;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LOOT;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    private void applyEntityAI()
    {
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }
}