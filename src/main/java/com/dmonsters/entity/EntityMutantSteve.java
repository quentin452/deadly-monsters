package com.dmonsters.entity;

import javax.annotation.Nullable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundEvent;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.ai.DeadlyMonsterAIMelee;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModSounds;

public class EntityMutantSteve extends EntityMob
{
    public static final ResourceLocation LOOT = new ResourceLocation(DeadlyMonsters.MOD_ID, "mutant_steve");
    public EntityMutantSteve(World worldIn)
    {
        super(worldIn);
        setSize(0.9F, 1.95F);
    }

    public void onLivingUpdate() {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote) {
            int posX = MathHelper.floor_double(this.posX);
            int posY = MathHelper.floor_double(this.posY);
            int posZ = MathHelper.floor_double(this.posZ);
            float f = this.worldObj.getLightBrightness(posX, posY, posZ);

            Entity ridingEntity = this.ridingEntity;
            ChunkCoordinates blockpos;

            if (ridingEntity instanceof EntityBoat) {
                blockpos = new ChunkCoordinates(posX, posY + 1, posZ);
            } else {
                blockpos = new ChunkCoordinates(posX, posY, posZ);
            }

            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.worldObj.canBlockSeeTheSky(blockpos.posX, blockpos.posY, blockpos.posZ)) {
                this.setFire(8);
            }
        }
        super.onLivingUpdate();
    }

    @Override
    protected String getHurtSound()
    {
        return ModSounds.MUTANT_HURT.toString();
    }

    @Override
    protected String getDeathSound()
    {
        return ModSounds.MUTANT_DEATH.toString();
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (super.attackEntityAsMob(entityIn))
        {
            if (entityIn instanceof EntityLivingBase)
            {
                this.playSound(ModSounds.MUTANT_ATTACK.toString(), 1, 1);
                ((EntityLivingBase) entityIn).knockBack(entityIn, 2, this.posX + entityIn.posX, this.posZ + entityIn.posZ);
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    private static final IAttribute ARMOR_ATTRIBUTE = new RangedAttribute("generic.armor", 0, 0, 30).setShouldWatch(true);

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.13D * ModConfig.globalSpeedMultiplier * ModConfig.mutantSteveSpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(16.0D * ModConfig.globalStrengthMultiplier * ModConfig.mutantSteveStrengthMultiplier);
        this.getEntityAttribute(ARMOR_ATTRIBUTE).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D * ModConfig.globalHealthMultiplier * ModConfig.mutantSteveHealthMultiplier);
    }

    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(1, new DeadlyMonsterAIMelee(this, 2.0D, false));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        initEntityAI();
    }

    protected String getLivingSound()
    {
        return ModSounds.MUTANT_AMBIENT.toString();
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LOOT;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }
/*
    protected void playStepSound(ChunkCoordinates pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.15F, 1.0F);
    }

 */

    private void applyEntityAI()
    {
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }
}
