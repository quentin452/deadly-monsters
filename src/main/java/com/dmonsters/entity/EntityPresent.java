package com.dmonsters.entity;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.ai.DeadlyMonsterAIMelee;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModSounds;
import net.minecraftforge.client.event.sound.SoundEvent;

public class EntityPresent extends EntityMob
{
    public static final ResourceLocation LOOT = new ResourceLocation(DeadlyMonsters.MOD_ID, "present");
    private int cageTicks = 0;
    private boolean debugCage;

    public EntityPresent(World worldIn)
    {
        super(worldIn);
        setSize(0.9F, 1.5F);
        initEntityAI();
    }

    @Override
    public void onLivingUpdate()
    {
        if (debugCage)
        {
            cageTicks++;
            if (cageTicks == 400)
            {
                cageTicks = 0;
                debugCage = false;
            }
        }
        super.onLivingUpdate();
    }

    @Override
    protected String getHurtSound()
    {
        return ModSounds.PRESENT_HURT.toString();
    }

    @Override
    protected String getDeathSound()
    {
        return ModSounds.PRESENT_DEATH.toString();
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (super.attackEntityAsMob(entityIn))
        {
            this.playSound(ModSounds.PRESENT_ATTACK.toString(), 1, 1);
            if (entityIn instanceof EntityPlayer)
            {
                makeCage((EntityPlayer) entityIn);
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean getCanSpawnHere()
    {
        int posX = MathHelper.floor_double(this.posX);
        int posY = MathHelper.floor_double(this.posY);
        int posZ = MathHelper.floor_double(this.posZ);

        return super.getCanSpawnHere() && this.worldObj.canBlockSeeTheSky(posX, posY, posZ);
    }

    private static final IAttribute ARMOR_ATTRIBUTE = new RangedAttribute("generic.armor", 0, 0, 30).setShouldWatch(true);

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D * ModConfig.globalSpeedMultiplier * ModConfig.presentSpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D * ModConfig.globalStrengthMultiplier * ModConfig.presentStrengthMultiplier);
        this.getEntityAttribute(ARMOR_ATTRIBUTE).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(26.0D * ModConfig.globalHealthMultiplier * ModConfig.presentHealthMultiplier);
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
        return ModSounds.PRESENT_AMBIENT.toString();
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

    private void applyEntityAI()
    {
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));

    }

    private void makeCage(EntityPlayer player) {
        if (debugCage) {
            return;
        }
        debugCage = true;

        int height = 3;
        int hSize = 3;
        int vSize = 7;
        int xPos = (int) this.posX;
        int yPos = (int) this.posY;
        int zPos = (int) this.posZ;
        ChunkCoordinates pos;
        Block block;
        int hCenter = (int) (hSize * 0.5F) - 1;

        // Walls and floor/ceiling
        for (int i = 0; i < vSize; i++) {
            for (int x = -hSize; x < hSize + 1; x++) {
                for (int z = -hSize; z < hSize + 1; z++) {
                    pos = new ChunkCoordinates(x + xPos, yPos + height + i, z + zPos);
                    block = this.worldObj.getBlock(pos.posX, pos.posY, pos.posZ);

                    if (x == -hSize || x == hSize || z == -hSize || z == hSize || i == 0 || i == vSize - 1) {
                        handleWallAndEdges(x, z, pos, block,hSize);
                    } else if (i == vSize - 2) {
                        handleCenterTorches(x, z, xPos, yPos, zPos, height, hCenter, pos);
                    }
                }
            }
        }

        // Teleport player to the center of the cage
        player.setPositionAndUpdate(xPos + hSize * 0.5F - 1, yPos + height + 1, zPos + hSize * 0.5F - 1);
    }

    private void handleWallAndEdges(int x, int z, ChunkCoordinates pos, Block block, int hSize) {
        if (block == Blocks.air) {
            if (x == hSize || z == hSize || x == -hSize || z == -hSize) {
                this.worldObj.setBlock(pos.posX, pos.posY, pos.posZ, ModBlocks.present_block, 1, 3);
            } else {
                this.worldObj.setBlock(pos.posX, pos.posY, pos.posZ, ModBlocks.present_block, 0, 3);
            }
        }
    }

    private void handleCenterTorches(int x, int z, int xPos, int yPos, int zPos, int height, int hCenter, ChunkCoordinates pos) {
        if (x == hCenter && z == hCenter) {
            ChunkCoordinates lightPos = new ChunkCoordinates(hCenter + xPos, yPos + height + 1, hCenter + zPos);
            this.worldObj.setBlock(lightPos.posX, lightPos.posY, lightPos.posZ, Blocks.torch);
            if (!this.worldObj.isRemote) {
                Entity creeper = new EntityCreeper(this.worldObj);
                creeper.setPosition(lightPos.posX, lightPos.posY, lightPos.posZ);
                this.worldObj.spawnEntityInWorld(creeper);
            }
        }
        if (x == 0 || z == 0) {
            this.worldObj.setBlock(pos.posX, pos.posY, pos.posZ, ModBlocks.present_block, 1, 3);
        } else {
            this.worldObj.setBlock(pos.posX, pos.posY, pos.posZ, ModBlocks.present_block, 0, 3);
        }
    }
}
