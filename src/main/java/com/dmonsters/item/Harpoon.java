package com.dmonsters.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import com.dmonsters.entity.EntityTopielec;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModConfig;

public class Harpoon extends Item
{
    private final int attackDamage;

    public Harpoon(String _harpoonType, int maxDamage, int _attackDamage)
    {
        setRegistryName("harpoon_" + _harpoonType);
        setUnlocalizedName(MainMod.MOD_ID + ".harpoon_" + _harpoonType);
        this.setCreativeTab(MainMod.MOD_CREATIVE_TAB);
        this.maxStackSize = 1;
        attackDamage = _attackDamage;
        if (maxDamage > -1)
            this.setMaxDamage(maxDamage);
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
                if (playerIn.isInWater())
                {
                    stack.damageItem(1, playerIn);
                    Random rnd = new Random();
                    float rndFloat = rnd.nextFloat();
                    if (rndFloat < 0.25F)
                    {
                        List<Item> itemsList = createDropTable();
                        Item item = getItemToSpawn(itemsList);
                        playerIn.dropItem(item, 1);
                    }
                }
            }
            return EnumActionResult.SUCCESS;
        }
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        float damage = 1;
        if (target instanceof EntityTopielec)
            damage = attackDamage;
        target.attackEntityFrom(DamageSource.GENERIC, damage);
        stack.damageItem(1, attacker);
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        TextComponentTranslation msg = new TextComponentTranslation("item.dmonsters.add_information.harpoon_1");
        tooltip.add(msg.getUnformattedText());
        if (ModConfig.CATEGORY_TOPIELEC.topielecHarpoonOnly)
        {
            msg = new TextComponentTranslation("item.dmonsters.add_information.harpoon_2");
            tooltip.add(msg.getUnformattedText());
        }
    }

    private List<Item> createDropTable()
    {
        List<Item> items = new ArrayList<>();
        items.add(new ItemStack(Items.FISH, 1, 0).getItem());
        items.add(new ItemStack(Items.FISH, 1, 1).getItem());
        items.add(new ItemStack(Items.FISH, 1, 2).getItem());
        items.add(new ItemStack(Items.FISH, 1, 3).getItem());
        return items;
    }

    private Item getItemToSpawn(List<Item> items)
    {
        int itemsNumber = items.size();
        Random rnd = new Random();
        int randomItem = rnd.nextInt(itemsNumber);
        return items.get(randomItem);
    }
}