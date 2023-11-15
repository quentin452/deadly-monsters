package com.dmonsters.entity;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.ai.DeadlyMonsterAIMelee;
import com.dmonsters.entity.ai.EntityAIFreezeEnvironment;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModSounds;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityFreezer extends EntityMob
{
    public static final ResourceLocation LOOT = new ResourceLocation(DeadlyMonsters.MOD_ID, "freezer");
    public boolean isAttacking;
    public EntityFreezer(World worldIn)
    {
        super(worldIn);
        setSize(0.9F, 1.95F);
        initEntityAI();

    }
    public boolean getAttacking() {
        return this.isAttacking;
    }

    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float partialTicks) {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.posZ);

        if (this.worldObj.blockExists(i, 0, j)) {
            double d0 = (this.boundingBox.maxY - this.boundingBox.minY) * 0.66D;
            int k = MathHelper.floor_double(this.posY - (double) this.yOffset + d0);
            return this.worldObj.getLightBrightnessForSkyBlocks(i, k, j, 0);
        } else {
            return 0;
        }
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        spawnParticle();
    }

    @Override
    protected String getHurtSound()
    {
        return ModSounds.FREEZER_HURT.toString();
    }

    @Override
    protected String getDeathSound()
    {
        return ModSounds.FREEZER_DEATH.toString();
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (super.attackEntityAsMob(entityIn))
        {
            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 600));
            this.playSound(ModSounds.FREEZER_ATTACK.toString(), 1, 1);
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
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.13D * ModConfig.globalSpeedMultiplier * ModConfig.freezerSpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(16.0D * ModConfig.globalStrengthMultiplier * ModConfig.freezerStrengthMultiplier);
        IAttribute armorAttribute = new RangedAttribute("generic.armor", 2.0D, 0.0D, Double.MAX_VALUE).setDescription("Armor Rating");
        this.getEntityAttribute(armorAttribute).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(45.0D * ModConfig.globalHealthMultiplier * ModConfig.freezerHealthMultiplier);
    }


    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

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
    }

    @Override
    protected String getLivingSound()
    {
        return ModSounds.FREEZER_AMBIENT.toString();
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

    private void spawnParticle()
    {
        double motionX = rand.nextGaussian() * 0.15D;
        double motionY = rand.nextGaussian() * 0.15D;
        double motionZ = rand.nextGaussian() * 0.15D;
        worldObj.spawnParticle("note",
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
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));
    }
}
