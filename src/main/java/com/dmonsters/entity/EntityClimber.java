package com.dmonsters.entity;

import java.util.Random;
import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.ai.DeadlyMonsterAIMelee;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModSounds;
import net.minecraftforge.client.event.sound.SoundEvent;

public class EntityClimber extends EntityMob
{
    public static final ResourceLocation LOOT = new ResourceLocation(DeadlyMonsters.MOD_ID, "climber");

    public EntityClimber(World worldIn)
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

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (!this.worldObj.isRemote)
        {
            this.setBesideClimbableBlock(this.isCollidedHorizontally);
        }
    }

    protected String getDeathSound()
    {
        return ModSounds.CLIMBER_DEATH.toString();
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (super.attackEntityAsMob(entityIn))
        {
            this.playSound(ModSounds.CLIMBER_ATTACK.toString(), 1, 1);
            return true;
        }
        else
        {
            return false;
        }
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(24.0D * ModConfig.globalHealthMultiplier * ModConfig.climberHealthMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.1D * ModConfig.globalSpeedMultiplier * ModConfig.climberSpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(12.0D * ModConfig.globalStrengthMultiplier * ModConfig.climberStrengthMultiplier);
    }

    public boolean isPotionApplicable(PotionEffect potioneffectIn)
    {
        return potioneffectIn.getPotionID() != Potion.poison.id && super.isPotionApplicable(potioneffectIn);
    }


    public boolean isOnLadder()
    {
        return this.isBesideClimbableBlock();
    }

    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    private static final int CLIMBING_DATA_INDEX = 20;
    public boolean isBesideClimbableBlock() {
        return (this.getDataWatcher().getWatchableObjectByte(CLIMBING_DATA_INDEX) & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean climbing) {
        byte b0 = this.getDataWatcher().getWatchableObjectByte(CLIMBING_DATA_INDEX);

        if (climbing) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 = (byte) (b0 & -2);
        }

        this.getDataWatcher().updateObject(CLIMBING_DATA_INDEX, b0);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new DeadlyMonsterAIMelee(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new AISpiderTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(3, new AISpiderTarget(this, EntityIronGolem.class, 0, true));

    }

    protected void entityInit()
    {
        super.entityInit();
        this.getDataWatcher().addObject(CLIMBING_DATA_INDEX, (byte) 0);
    }

    protected String getLivingSound()
    {
        return ModSounds.CLIMBER_AMBIENT.toString();
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
        return 5;
    }

    protected String getHurtSound()
    {
        return ModSounds.CLIMBER_HURT.toString();
    }
   /*
    protected void playStepSound(int x, int y, int z, Block blockIn)
    {
        this.playSound("random.glass", 0.15F, 1.0F);
    }

    */


    public double getMountedYOffset()
    {
        return this.height * 0.5F;
    }

    public void setInWeb()
    {
    }

    public static class AISpiderTarget extends EntityAINearestAttackableTarget {

        public AISpiderTarget(EntityCreature entity, Class targetClass,int idk, boolean checkSight) {
            super(entity,targetClass,idk,checkSight);
        }

        @Override
        public boolean shouldExecute() {
            return super.shouldExecute();
        }
    }

    public static class GroupData implements IEntityLivingData {
        public Potion effect;

        public void setRandomEffect(Random rand) {
            int i = rand.nextInt(5);

            if (i == 0) {
                this.effect = Potion.moveSpeed;
            } else if (i == 1) {
                this.effect = Potion.damageBoost;
            } else if (i == 2) {
                this.effect = Potion.regeneration;
            } else if (i == 3) {
                this.effect = Potion.invisibility;
            }
        }
    }
}
