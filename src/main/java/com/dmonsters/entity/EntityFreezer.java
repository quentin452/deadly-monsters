package com.dmonsters.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.dmonsters.ai.DeadlyMonsterAIMelee;
import com.dmonsters.ai.EntityAIFreezeEnvironment;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModSounds;

public class EntityFreezer extends EntityMob
{
    public static final ResourceLocation LOOT = new ResourceLocation(MainMod.MODID, "freezer");
    private static final DataParameter<Boolean> ARMS_RAISED = EntityDataManager.createKey(EntityFreezer.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.createKey(EntityFreezer.class, DataSerializers.BOOLEAN);

    public EntityFreezer(World worldIn)
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
        super.onLivingUpdate();
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        spawnParticle();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource amageSource)
    {
        return ModSounds.FREEZER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return ModSounds.FREEZER_DEATH;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (super.attackEntityAsMob(entityIn))
        {
            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 600));
            this.playSound(ModSounds.FREEZER_ATTACK, 1, 1);
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
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.13D * ModConfig.CATEGORY_GENERAL.globalSpeedMultiplier * ModConfig.CATEGORY_FREEZER.freezerSpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(16.0D * ModConfig.CATEGORY_GENERAL.globalStrengthMultiplier * ModConfig.CATEGORY_FREEZER.freezerStrengthMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(45.0D * ModConfig.CATEGORY_GENERAL.globalHealthMultiplier * ModConfig.CATEGORY_FREEZER.freezerHealthMultiplier);
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
        this.tasks.addTask(0, new EntityAIFreezeEnvironment(this));
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new DeadlyMonsterAIMelee(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
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
        return ModSounds.FREEZER_AMBIENT;
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

    private void spawnParticle()
    {
        double motionX = rand.nextGaussian() * 0.15D;
        double motionY = rand.nextGaussian() * 0.15D;
        double motionZ = rand.nextGaussian() * 0.15D;
        world.spawnParticle(EnumParticleTypes.SNOW_SHOVEL,
            posX + rand.nextFloat() * width * 2.0F - width,
            posY + 0.5D + rand.nextFloat() * height,
            posZ + rand.nextFloat() * width * 2.0F - width,
            motionX,
            motionY,
            motionZ
        );
    }

    private void applyEntityAI()
    {
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
    }
}