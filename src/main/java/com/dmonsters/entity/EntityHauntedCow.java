package com.dmonsters.entity;

import java.util.Random;
import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.dmonsters.ai.EntityAIHauntedCowAttack;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModSounds;
import com.dmonsters.network.PacketClientFXUpdate;
import com.dmonsters.network.PacketHandler;

public class EntityHauntedCow extends EntityMob
{
    public static final ResourceLocation LOOT = new ResourceLocation(MainMod.MODID, "hauntedcow");

    public EntityHauntedCow(World worldIn)
    {
        super(worldIn);
        this.setSize(0.9F, 1.4F);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIHauntedCowAttack(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    protected SoundEvent getAmbientSound()
    {
        return ModSounds.HAUNTEDCOW_AMBINET;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LOOT;
    }

    protected void applyEntityAI()
    {
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return ModSounds.HAUNTEDCOW_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return ModSounds.HAUNTEDCOW_DEATH;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        super.attackEntityAsMob(entityIn);
        this.playSound(ModSounds.HAUNTEDCOW_AMBINET, 1, 1);
        if (ModConfig.hauntedCowDisableTimeChange)
            return true;
        Random random = new Random();
        float rndNum = random.nextFloat();
        if (rndNum < 0.5f)
            return true;
        PacketHandler.INSTANCE.sendToAll(new PacketClientFXUpdate(entityIn.getPosition(), PacketClientFXUpdate.Type.TIME_CHANGE));
        entityIn.world.setWorldTime(18000);
        return true;
    }

    @Override
    protected boolean isValidLightLevel()
    {
        return true;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D * ModConfig.speedMultiplier * ModConfig.hauntedCowSpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(12.0D * ModConfig.strengthMultiplier * ModConfig.hauntedCowStrengthMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24.0D * ModConfig.healthMultiplier * ModConfig.hauntedCowHealthMultiplier);
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(ModSounds.HAUNTEDCOW_STEP, 0.15F, 1.0F);
    }
}