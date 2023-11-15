package com.dmonsters.item;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.network.PacketClientFXUpdate;
import com.dmonsters.network.PacketHandler;

public class PoopooPill extends Item
{
    public PoopooPill()
    {
        setUnlocalizedName("poopoo_pill");
        setTextureName(DeadlyMonsters.MOD_ID + ".poopoo_pill");
        this.setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player)
    {
        ItemStack heldItem = player.getHeldItem();
        // System.out.println(heldItem);
        if (player.canEat(true))
        {
            player.setItemInUse(heldItem, this.getMaxItemUseDuration(heldItem));
            return new ItemStack(itemStackIn.getItem(), 1, itemStackIn.getItemDamage());
        }
        else
        {
            return new ItemStack(itemStackIn.getItem(), 0, itemStackIn.getItemDamage());
        }
    }

    @Nullable
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer && !worldIn.isRemote)
        {
            EntityPlayer entityplayer = (EntityPlayer) entityLiving;
            if (entityplayer.getFoodStats().getFoodLevel() < 20)
            {
                // Use predefined color and style from EnumChatFormatting
                String errorMsg = EnumChatFormatting.DARK_RED + "msg.dmonsters.poopoo_pill.error";
                entityplayer.addChatMessage(new ChatComponentText(errorMsg));

                if (entityplayer.getHealth() > 1)
                    entityplayer.setHealth(1);
                else
                    entityplayer.attackEntityFrom(DamageSource.generic, 999);
            }
            else
            {
                stack.stackSize--;
                this.onFoodEaten(worldIn, entityplayer);
                addDumpBlockUnderPlayer(worldIn, entityplayer);
                PacketHandler.INSTANCE.sendToAll(new PacketClientFXUpdate(new ChunkCoordinates((int) entityplayer.posX, (int) entityplayer.posY, (int) entityplayer.posZ), PacketClientFXUpdate.Type.DUMP));
            }
        }
        return stack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.eat;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }

    protected void onFoodEaten(World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
            player.addPotionEffect(new PotionEffect(17, 100));

            player.getFoodStats().setFoodLevel(2);
            player.getFoodStats().setFoodSaturationLevel(0);
        }
    }

    private void addDumpBlockUnderPlayer(World worldIn, EntityPlayer playerIn)
    {
        int posX = MathHelper.floor_double(playerIn.posX);
        int posY = MathHelper.floor_double(playerIn.posY);
        int posZ = MathHelper.floor_double(playerIn.posZ);

        worldIn.setBlock(posX, posY, posZ, ModBlocks.dump);
    }
}
