package com.dmonsters.item;

import net.minecraft.block.BlockFence;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.*;

public class MobSpawnerItem extends Item
{
    private final String mobName;

    public MobSpawnerItem(String name)
    {
        setRegistryName("mob_spawner_item_" + name);
        setUnlocalizedName(DeadlyMonsters.MOD_ID + ".mob_spawner_item_" + name);
        this.setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
        mobName = name;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack stack = playerIn.getHeldItem(hand);
        if (worldIn.isRemote)
        {
            return EnumActionResult.SUCCESS;
        }
        else if (!playerIn.canPlayerEdit(pos.offset(facing), facing, stack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            IBlockState iblockstate = worldIn.getBlockState(pos);

            pos = pos.offset(facing);
            double d0 = 0.0D;

            if (facing == EnumFacing.UP && iblockstate.getBlock() instanceof BlockFence) //Forge: Fix Vanilla bug comparing state instead of block
            {
                d0 = 0.5D;
            }

            Entity entity = spawnEntity(worldIn, (double) pos.getX() + 0.5D, (double) pos.getY() + d0, (double) pos.getZ() + 0.5D);

            if (entity instanceof EntityLivingBase && stack.hasDisplayName())
            {
                entity.setCustomNameTag(stack.getDisplayName());
            }

            if (!playerIn.capabilities.isCreativeMode)
            {
                stack.shrink(1);
            }

            return EnumActionResult.SUCCESS;
        }
    }

    private Entity spawnEntity(World worldIn, double x, double y, double z)
    {
        Entity entity = getEntity(worldIn);
        EntityLiving entityliving = (EntityLiving) entity;
        entity.setLocationAndAngles(x, y, z, MathHelper.wrapDegrees(worldIn.rand.nextFloat() * 360.0F), 0.0F);
        entityliving.rotationYawHead = entityliving.rotationYaw;
        entityliving.renderYawOffset = entityliving.rotationYaw;
        entityliving.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityliving)), null);
        worldIn.spawnEntity(entity);
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