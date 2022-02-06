package com.dmonsters.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.ai.DeadlyMonsterAIMelee;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModSounds;

public class EntityBloodyMaiden extends EntityMob
{
    public static final ResourceLocation LOOT = new ResourceLocation(DeadlyMonsters.MOD_ID, "bloody_maiden");
    private static final DataParameter<Boolean> ARMS_RAISED = EntityDataManager.createKey(EntityBloodyMaiden.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> TRIGGERED = EntityDataManager.createKey(EntityBloodyMaiden.class, DataSerializers.BOOLEAN);

    public EntityBloodyMaiden(World worldIn)
    {
        super(worldIn);
        setSize(1.1F, 0.6F);
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
        super.onLivingUpdate();
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource amageSource)
    {
        return ModSounds.MAIDEN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return ModSounds.MAIDEN_DEATH;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (super.attackEntityAsMob(entityIn))
        {
            this.playSound(ModSounds.MAIDEN_ATTACK, 1, 1);
            if (getTriggered())
                entityIn.attackEntityFrom(DamageSource.GENERIC, 999);
            setTriggered(true);
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
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D * ModConfig.CATEGORY_GENERAL.globalSpeedMultiplier * ModConfig.CATEGORY_BLOODY_MAIDEN.bloodyMaidenSpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D * ModConfig.CATEGORY_GENERAL.globalStrengthMultiplier * ModConfig.CATEGORY_BLOODY_MAIDEN.bloodyMaidenStrengthMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D * ModConfig.CATEGORY_GENERAL.globalHealthMultiplier * ModConfig.CATEGORY_BLOODY_MAIDEN.bloodyMaidenHealthMultiplier);
    }

    @SideOnly(Side.CLIENT)
    public boolean isArmsRaised()
    {
        return this.getDataManager().get(ARMS_RAISED);
    }

    public boolean getTriggered()
    {
        return this.getDataManager().get(TRIGGERED);
    }

    public void setTriggered(boolean mode)
    {
        this.getDataManager().set(TRIGGERED, mode);
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
        this.getDataManager().register(TRIGGERED, Boolean.FALSE);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSounds.MAIDEN_AMBIENT;
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
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
    }
}