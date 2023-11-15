package com.dmonsters.item;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class MobSpawnerItem extends Item
{
    private final String mobName;

    public MobSpawnerItem(String name)
    {
        setUnlocalizedName("mob_spawner_item_" + name);
        setTextureName(DeadlyMonsters.MOD_ID + ".mob_spawner_item_" + name);
        this.setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
        mobName = name;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else if (!player.canPlayerEdit(x, y, z, side, stack))
        {
            return false;
        }
        else
        {
            ChunkCoordinates pos = new ChunkCoordinates(x, y, z);
            Block block = world.getBlock(x, y, z);

            switch (side)
            {
                case 0:
                    pos.posY--;
                    break;
                case 1:
                    pos.posY++;
                    break;
                case 2:
                    pos.posZ--;
                    break;
                case 3:
                    pos.posZ++;
                    break;
                case 4:
                    pos.posX--;
                    break;
                case 5:
                    pos.posX++;
                    break;
            }

            double d0 = 0.0D;

            if (side == 1 && block instanceof BlockFence)
            {
                d0 = 0.5D;
            }

            Entity entity = spawnEntity(world, (double) pos.posX + 0.5D, (double) pos.posY + d0, (double) pos.posZ + 0.5D);

            if (entity instanceof EntityLivingBase && stack.hasDisplayName())
            {
                entity.getDataWatcher().updateObject(10, stack.getDisplayName());
            }

            if (!player.capabilities.isCreativeMode)
            {
                stack.stackSize--;
            }

            return true;
        }
    }

    private Entity spawnEntity(World worldIn, double x, double y, double z)
    {
        Entity entity = getEntity(worldIn);
        EntityLiving entityliving = (EntityLiving) entity;
        entity.setLocationAndAngles(x, y, z, worldIn.rand.nextFloat() * 360.0F, 0.0F);
        entityliving.rotationYawHead = entityliving.rotationYaw;
        entityliving.renderYawOffset = entityliving.rotationYaw;
        entityliving.onSpawnWithEgg(null);
        worldIn.spawnEntityInWorld(entity);
        entityliving.playLivingSound();
        return entity;
    }

    private Entity getEntity(World worldIn)
    {
        Entity entity = new EntityZombieChicken(worldIn);
        switch (mobName)
        {
            case "unborn_baby":
                entity = new EntityUnbornBaby(worldIn);
                break;
            case "climber":
                entity = new EntityClimber(worldIn);
                break;
            case "entrail":
                entity = new EntityEntrail(worldIn);
                break;
            case "freezer":
                entity = new EntityFreezer(worldIn);
                break;
            case "mutant_steve":
                entity = new EntityMutantSteve(worldIn);
                break;
            case "fallen_leader":
                entity = new EntityFallenLeader(worldIn);
                break;
            case "bloody_maiden":
                entity = new EntityBloodyMaiden(worldIn);
                break;
            case "zombie_chicken":
                entity = new EntityZombieChicken(worldIn);
                break;
            case "present":
                entity = new EntityPresent(worldIn);
                break;
            case "stranger":
                entity = new EntityStranger(worldIn);
                break;
            case "haunted_cow":
                entity = new EntityHauntedCow(worldIn);
                break;
            case "topielec":
                entity = new EntityTopielec(worldIn);
                break;
        }
        return entity;
    }
}
