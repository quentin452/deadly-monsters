package com.dmonsters.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.dmonsters.main.MainMod;
import com.dmonsters.network.PacketClientFXUpdate;
import com.dmonsters.network.PacketHandler;

public class WomanHeart extends Item
{
    public WomanHeart()
    {
        setRegistryName("womanHeart");
        setUnlocalizedName(MainMod.MODID + ".womanHeart");
        this.setCreativeTab(MainMod.MOD_CREATIVETAB);
        this.maxStackSize = 1;
        this.setMaxDamage(11);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack stack = playerIn.getHeldItem(hand);
        if (!playerIn.canPlayerEdit(pos, facing, stack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            if (!worldIn.isRemote)
            {
                Block blockIn = worldIn.getBlockState(pos).getBlock();
                if (playerIn.isSneaking())
                {
                    worldIn.setBlockState(pos, Blocks.WATER.getDefaultState());
                }
                else
                {
                    worldIn.setBlockState(pos, Blocks.LAVA.getDefaultState());
                }
                PacketHandler.INSTANCE.sendToAll(new PacketClientFXUpdate(pos, PacketClientFXUpdate.Type.WOMAN_HEART));
                stack.damageItem(1, playerIn);
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.SUCCESS;
        }
    }
}