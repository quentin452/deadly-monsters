package com.dmonsters.item;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.network.PacketClientFXUpdate;
import com.dmonsters.network.PacketHandler;

public class PoopooPill extends Item
{
    public PoopooPill()
    {
        setRegistryName("poopoo_pill");
        setUnlocalizedName(MainMod.MOD_ID + ".poopoo_pill");
        this.setCreativeTab(MainMod.MOD_CREATIVE_TAB);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        ItemStack itemStackIn = playerIn.getHeldItem(hand);
        //System.out.println(itemStackIn);
        if (playerIn.canEat(true))
        {
            playerIn.setActiveHand(hand);
            return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
        }
        else
        {
            return new ActionResult(EnumActionResult.FAIL, itemStackIn);
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
                Style red = new Style().setColor(TextFormatting.DARK_RED);
                TextComponentTranslation errorMsg = new TextComponentTranslation("msg.dmonsters.poopoo_pill.error");
                errorMsg.setStyle(red);
                entityplayer.sendMessage(errorMsg);
                if (entityplayer.getHealth() > 1)
                    entityplayer.setHealth(1);
                else
                    entityplayer.attackEntityFrom(DamageSource.GENERIC, 999);
            }
            else
            {
                stack.shrink(1);
                this.onFoodEaten(worldIn, entityplayer);
                addDumpBlockUnderPlayer(worldIn, entityplayer);
                PacketHandler.INSTANCE.sendToAll(new PacketClientFXUpdate(entityplayer.getPosition(), PacketClientFXUpdate.Type.DUMP));
            }
        }
        return stack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.EAT;
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
            player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 100));
            player.getFoodStats().setFoodLevel(2);
            player.getFoodStats().setFoodSaturationLevel(0);
        }
    }

    private void addDumpBlockUnderPlayer(World worldIn, EntityPlayer playerIn)
    {
        BlockPos pos = playerIn.getPosition();
        worldIn.setBlockState(pos, ModBlocks.dump.getDefaultState());
    }
}