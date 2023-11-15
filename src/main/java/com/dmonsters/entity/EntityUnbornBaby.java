package com.dmonsters.entity;

import javax.annotation.Nullable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundEvent;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.ai.DeadlyMonsterAIMelee;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModSounds;

public class EntityUnbornBaby extends EntityMob
{
    public static final ResourceLocation LOOT = new ResourceLocation(DeadlyMonsters.MOD_ID, "unborn_baby");
    private int blindRefreshTick = 0;

    public EntityUnbornBaby(World worldIn)
    {
        super(worldIn);
        setSize(0.9F, 1.95F);
        initEntityAI();
    }

    public void onLivingUpdate() {
        if (!this.worldObj.isRemote) {
            // Check if it's daytime
            if (this.worldObj.isDaytime()) {
                float brightness = this.getBrightness(1.0F); // Use getBrightness with a parameter

                // Check if the entity is in direct sunlight
                if (brightness > 0.5F && this.rand.nextFloat() * 30.0F < (brightness - 0.4F) * 2.0F) {
                    // Set the entity on fire
                    this.setFire(8);
                }
            }

            // Check if babyBlindness is enabled in the config
            if (ModConfig.babyBlindness && this.getAttackTarget() != null) {
                // Apply blindness effect to the attack target
                if (blindRefreshTick == 20) {
                    blindRefreshTick = 0;
                    this.getAttackTarget().addPotionEffect(new PotionEffect(Potion.blindness.id, 100)); // Use Potion.blindness.id
                } else {
                    blindRefreshTick++;
                }
            }
        }

        // Call the superclass method
        super.onLivingUpdate();
    }


    @Override
    protected String getHurtSound()
    {
        return ModSounds.BABY_HURT.toString();
    }

    @Override
    protected String getDeathSound()
    {
        return ModSounds.BABY_DEATH.toString();
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (super.attackEntityAsMob(entityIn))
        {
            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(2, 600));
            this.playSound(ModSounds.BABY_ATTACK.toString(), 1, 1);
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
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D * ModConfig.globalSpeedMultiplier * ModConfig.babySpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(12.0D * ModConfig.globalStrengthMultiplier * ModConfig.babyStrengthMultiplier);
        this.getEntityAttribute(ARMOR_ATTRIBUTE).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D * ModConfig.globalHealthMultiplier * ModConfig.babyHealthMultiplier);
    }
    private static final IAttribute ARMOR_ATTRIBUTE = new RangedAttribute("generic.armor", 0, 0, 30).setShouldWatch(true);

    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

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
    }

    @Override
    protected String getLivingSound()
    {
        return ModSounds.BABY_AMBIENT.toString();
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

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    private void applyEntityAI()
    {
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));

    }
}
