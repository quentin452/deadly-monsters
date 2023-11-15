package com.dmonsters.entity;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.ai.DeadlyMonsterAIMelee;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModSounds;
import com.dmonsters.network.PacketClientFXUpdate;
import com.dmonsters.network.PacketHandler;

public class EntityHauntedCow extends EntityMob
{
    public static final ResourceLocation LOOT = new ResourceLocation(DeadlyMonsters.MOD_ID, "haunted_cow");

    public EntityHauntedCow(World worldIn)
    {
        super(worldIn);
        this.setSize(0.9F, 1.4F);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new DeadlyMonsterAIMelee(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    protected String getLivingSound()
    {
        return ModSounds.HAUNTEDCOW_AMBIENT.toString();
    }

    @Override
    protected void dropFewItems(boolean recentlyHit, int lootingModifier)
    {
        if (recentlyHit)
        {
            int count = this.rand.nextInt(2 + lootingModifier) + 1;
            for (int i = 0; i < count; ++i)
            {
                this.dropItem(Items.bone, 1);
            }
        }
    }

    protected void applyEntityAI()
    {
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    protected String getHurtSound()
    {
        return ModSounds.HAUNTEDCOW_HURT.toString();
    }

    protected String getDeathSound()
    {
        return ModSounds.HAUNTEDCOW_DEATH.toString();
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        super.attackEntityAsMob(entityIn);
        this.playSound(ModSounds.HAUNTEDCOW_AMBIENT.toString(), 1, 1);

        if (ModConfig.hauntedCowDisableTimeChange) {
            return true;
        }

        Random random = new Random();
        float rndNum = random.nextFloat();

        if (rndNum < 0.5f) {
            return true;
        }

        if (entityIn.worldObj.isDaytime()) {
            ChunkCoordinates entityPos = new ChunkCoordinates((int) entityIn.posX, (int) entityIn.posY, (int) entityIn.posZ);

            PacketHandler.INSTANCE.sendToAll(new PacketClientFXUpdate(entityPos, PacketClientFXUpdate.Type.TIME_CHANGE));

            if (entityIn.worldObj.getGameRules().getGameRuleBooleanValue("doDaylightCycle")) {
                long worldTime = entityIn.worldObj.getWorldTime() + 24000L;
                entityIn.worldObj.setWorldTime((worldTime - worldTime % 24000L) - 6000L);
            }
        }

        return true;
    }

    @Override
    protected boolean isValidLightLevel()
    {
        return true;
    }
    private static final IAttribute ARMOR_ATTRIBUTE = new RangedAttribute("generic.armor", 0, 0, 30).setShouldWatch(true);

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D * ModConfig.globalSpeedMultiplier * ModConfig.hauntedCowSpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(12.0D * ModConfig.globalStrengthMultiplier * ModConfig.hauntedCowStrengthMultiplier);
        this.getEntityAttribute(ARMOR_ATTRIBUTE).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(24.0D * ModConfig.globalHealthMultiplier * ModConfig.hauntedCowHealthMultiplier);
    }
/*
    protected void playStepSound(ChunkCoordinates pos, Block blockIn)
    {
        this.playSound(ModSounds.HAUNTEDCOW_STEP, 0.15F, 1.0F);
    }

 */
}
