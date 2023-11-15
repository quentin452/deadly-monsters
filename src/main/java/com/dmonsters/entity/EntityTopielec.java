package com.dmonsters.entity;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.ai.EntityAITopielecAttack;
import com.dmonsters.entity.ai.EntityAITopielecFollow;
import com.dmonsters.entity.ai.EntityAITopielecIdle;
import com.dmonsters.entity.ai.EntityAIWaterMobNearestPlayer;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModSounds;
import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityTopielec extends EntityMob
{
    private static final IAttribute ARMOR_ATTRIBUTE = new RangedAttribute("generic.armor", 0, 0, 30).setShouldWatch(true);

    public static final ResourceLocation LOOT = new ResourceLocation(DeadlyMonsters.MOD_ID, "topielec");
    private float targetVectorX;
    private float targetVectorY;
    private float targetVectorZ;
    private double lastWaterX;
    private double lastWaterY;
    private double lastWaterZ;

    public EntityTopielec(World worldIn)
    {
        super(worldIn);
        this.setSize(1F, 1F);
        initEntityAI();

    }

    public boolean canBreatheUnderwater()
    {
        return true;
    }

    public void setMovementVector(float randomMotionVecXIn, float randomMotionVecYIn, float randomMotionVecZIn)
    {
        this.targetVectorX = randomMotionVecXIn;
        this.targetVectorY = randomMotionVecYIn;
        this.targetVectorZ = randomMotionVecZIn;
    }

    public void setLastWaterPosition(double waterX, double waterY, double waterZ)
    {
        this.lastWaterX = waterX;
        this.lastWaterY = waterY;
        this.lastWaterZ = waterZ;
    }

    public boolean hasMovementVector()
    {
        return this.targetVectorX != 0.0F || this.targetVectorY != 0.0F || this.targetVectorZ != 0.0F;
    }

    public boolean isPushedByWater()
    {
        return false;
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAITopielecAttack(this, 0.5F));
        this.tasks.addTask(2, new EntityAITopielecFollow(this, 0.5F));
        this.tasks.addTask(3, new EntityAITopielecIdle(this));
        this.applyEntityAI();
    }

    public int getTalkInterval()
    {
        return 120;
    }

    public void onEntityUpdate() {
        int i = this.getAir();
        super.onEntityUpdate();

        if (this.isEntityAlive() && !this.isInWater()) {
            this.setPosition(lastWaterX, lastWaterY, lastWaterZ);
            this.motionX = 0.0D;
            this.motionZ = 0.0D;
            if (this.isInsideOfMaterial(Material.water)) {
                this.motionY -= 0.5D;
            }
            --i;
            this.setAir(i);

            if (this.getAir() == -20) {
                this.setAir(0);
                this.attackEntityFrom(DamageSource.drown, 2.0F);
            }
        } else {
            this.motionX = this.targetVectorX;
            this.motionY = this.targetVectorY;
            this.motionZ = this.targetVectorZ;
            this.setLastWaterPosition(this.posX, this.posY, this.posZ);
            this.setAir(300);
        }
    }

    @Override
    protected String getLivingSound()
    {
        return ModSounds.TOPIELEC_AMBIENT.toString();
    }

    @Override
    protected void dropFewItems(boolean recentlyHit, int lootingModifier) {
        super.dropFewItems(recentlyHit, lootingModifier);

        if (recentlyHit) {
            this.dropItem(Items.bone, 1 + this.rand.nextInt(2 + lootingModifier));
        }
    }


    protected boolean canDespawn()
    {
        return true;
    }

    @Override
    protected String getHurtSound()
    {
        return ModSounds.TOPIELEC_HURT.toString();
    }

    @Override
    protected String getDeathSound()
    {
        return ModSounds.TOPIELEC_DEATH.toString();
    }

    public boolean getCanSpawnHere() {
        return this.posY > 45.0D && this.posY < 63D && super.getCanSpawnHere();
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4D * ModConfig.globalSpeedMultiplier * ModConfig.topielecSpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D * ModConfig.globalStrengthMultiplier * ModConfig.topielecStrengthMultiplier);
        this.getEntityAttribute(ARMOR_ATTRIBUTE).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D * ModConfig.globalHealthMultiplier * ModConfig.topielecHealthMultiplier);
    }

    private void applyEntityAI()
    {
        this.targetTasks.addTask(0, new EntityAIWaterMobNearestPlayer(this, ModConfig.topielecSearchDistance));
    }
}
