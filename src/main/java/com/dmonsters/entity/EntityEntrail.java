package com.dmonsters.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.ai.DeadlyMonsterAIMelee;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModSounds;
import net.minecraftforge.client.event.sound.SoundEvent;

public class EntityEntrail extends EntityMob
{
    public static final ResourceLocation LOOT = new ResourceLocation(DeadlyMonsters.MOD_ID, "entrail");

    public EntityEntrail(World worldIn)
    {
        super(worldIn);
        setSize(0.9F, 1.95F);
        initEntityAI();

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
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (!this.worldObj.isRemote && !source.isFireDamage())
        {
            double x, y, z;
            x = this.posX;
            y = this.posY;
            z = this.posZ;
            Entity slime = new EntitySlime(this.worldObj);
            slime.setPosition(x, y, z);
            this.worldObj.spawnEntityInWorld(slime);
        }
        return super.attackEntityFrom(source, amount);
    }

    @Override
    protected String getHurtSound()
    {
        return ModSounds.ENTRAIL_HURT.toString();
    }

    @Override
    protected String getDeathSound()
    {
        return ModSounds.ENTRAIL_DEATH.toString();
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (super.attackEntityAsMob(entityIn))
        {
            this.playSound(ModSounds.ENTRAIL_ATTACK.toString(), 1, 1);
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
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D * ModConfig.globalSpeedMultiplier * ModConfig.entrailSpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10.0D * ModConfig.globalStrengthMultiplier * ModConfig.entrailStrengthMultiplier);
        this.getEntityAttribute(ARMOR_ATTRIBUTE).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D * ModConfig.globalHealthMultiplier * ModConfig.entrailHealthMultiplier);
    }

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
    protected String getLivingSound()
    {
        return ModSounds.ENTRAIL_AMBIENT.toString();
    }

    @Override
    protected void dropFewItems(boolean recentlyHit, int lootingModifier) {
        super.dropFewItems(recentlyHit, lootingModifier);

        if (recentlyHit) {
            this.dropItem(Items.bone, 1 + this.rand.nextInt(2 + lootingModifier));
        }
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    private void applyEntityAI()
    {
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));}
}
