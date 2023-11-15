package com.dmonsters.entity;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.ai.DeadlyMonsterAIMelee;
import com.dmonsters.main.ModConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityZombieChicken extends EntityMob
{
    public static final ResourceLocation LOOT = new ResourceLocation(DeadlyMonsters.MOD_ID, "zombie_chicken");

    public EntityZombieChicken(World worldIn)
    {
        super(worldIn);
        setSize(0.5F, 0.5F);
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
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (super.attackEntityAsMob(entityIn))
        {
            if (entityIn instanceof EntityChicken)
            {
                double x, y, z;
                x = entityIn.posX;
                y = entityIn.posY;
                z = entityIn.posZ;
                entityIn.setDead();
                Entity newZombieChiken = new EntityZombieChicken(this.worldObj);
                newZombieChiken.setPosition(x, y, z);
                this.worldObj.spawnEntityInWorld(newZombieChiken);
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    private static final IAttribute ARMOR_ATTRIBUTE = new RangedAttribute("generic.armor", 0, 0, 30).setShouldWatch(true);

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.26D * ModConfig.globalSpeedMultiplier * ModConfig.zombieChickenSpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D * ModConfig.globalStrengthMultiplier * ModConfig.zombieChickenStrengthMultiplier);
        this.getEntityAttribute(ARMOR_ATTRIBUTE).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16.0D * ModConfig.globalHealthMultiplier * ModConfig.zombieChickenHealthMultiplier);
    }

    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
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

    protected void applyEntityAI()
    {
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class,0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityChicken.class,0, true));
    }
}
