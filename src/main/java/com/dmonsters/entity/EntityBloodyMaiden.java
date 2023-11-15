package com.dmonsters.entity;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.ai.DeadlyMonsterAIMelee;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBloodyMaiden extends EntityMob
{
    public static final ResourceLocation LOOT = new ResourceLocation(DeadlyMonsters.MOD_ID, "bloody_maiden");
    private boolean triggered;
    public EntityBloodyMaiden(World worldIn)
    {
        super(worldIn);
        setSize(1.1F, 0.6F);
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
    public boolean getTriggered() {
        return triggered;
    }

    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }


    @Override
    public void onUpdate()
    {
        super.onUpdate();
    }

    @Override
    protected String getHurtSound()
    {
        return ModSounds.MAIDEN_HURT.toString();
    }

    @Override
    protected String getDeathSound()
    {
        return ModSounds.MAIDEN_DEATH.toString();
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (super.attackEntityAsMob(entityIn))
        {
            this.playSound(ModSounds.MAIDEN_ATTACK.toString(), 1, 1);
                entityIn.attackEntityFrom(DamageSource.generic, 999);
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
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2D * ModConfig.globalSpeedMultiplier * ModConfig.bloodyMaidenSpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D * ModConfig.globalStrengthMultiplier * ModConfig.bloodyMaidenStrengthMultiplier);
        this.getEntityAttribute(ARMOR_ATTRIBUTE).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D * ModConfig.globalHealthMultiplier * ModConfig.bloodyMaidenHealthMultiplier);
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
    protected void entityInit()
    {
        super.entityInit();
        initEntityAI();
    }

    @Override
    protected String getLivingSound()
    {
        return ModSounds.MAIDEN_AMBIENT.toString();
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
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));
    }    @Override

public void readEntityFromNBT(NBTTagCompound compound) {
    super.readEntityFromNBT(compound);
    this.triggered = compound.getBoolean("Triggered");
}

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Triggered", this.triggered);
    }

}
