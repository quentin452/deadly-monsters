package com.dmonsters.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.network.PacketClientFXUpdate;
import com.dmonsters.network.PacketHandler;

public class BloodyMaidenHeart extends Item
{
    public BloodyMaidenHeart()
    {
        setUnlocalizedName("bloody_maiden_heart");
        setTextureName(DeadlyMonsters.MOD_ID + ".bloody_maiden_heart");
        this.setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
        this.maxStackSize = 1;
        this.setMaxDamage(11);
    }

    @Override
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (!p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_))
        {
            return false;
        }
        else
        {
            if (!p_77648_3_.isRemote)
            {
                if (p_77648_2_.isSneaking())
                {
                    p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, Blocks.water);
                }
                else
                {
                    p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, Blocks.lava);
                }
                PacketHandler.INSTANCE.sendToAll(new PacketClientFXUpdate(new ChunkCoordinates(p_77648_4_, p_77648_5_, p_77648_6_), PacketClientFXUpdate.Type.BLOODY_MAIDEN_HEART));
                p_77648_1_.damageItem(1, p_77648_2_);
                return true;
            }
            return true;
        }
    }
}
