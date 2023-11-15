package com.dmonsters.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.entity.EntityTopielec;
import com.dmonsters.main.ModConfig;

public class Harpoon extends Item
{
    private final int attackDamage;

    public Harpoon(String _harpoonType, int maxDamage, int _attackDamage)
    {
        setUnlocalizedName("harpoon_" + _harpoonType);
        setTextureName(DeadlyMonsters.MOD_ID + ".harpoon_" + _harpoonType);
        this.setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
        this.maxStackSize = 1;
        attackDamage = _attackDamage;
        if (maxDamage > -1)
            this.setMaxDamage(maxDamage);
    }

    @Override
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (!p_77648_2_.capabilities.isCreativeMode) {
            p_77648_1_.stackSize--;
        }

        if (!p_77648_3_.isRemote) {
            if (p_77648_2_.isInWater()) {
                p_77648_1_.damageItem(1, p_77648_2_);
                Random rnd = new Random();
                float rndFloat = rnd.nextFloat();
                if (rndFloat < 0.25F) {
                    List<Item> itemsList = createDropTable();
                    Item item = getItemToSpawn(itemsList);
                    p_77648_2_.dropItem(item, 1);
                }
            }
        }
        return true;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        float damage = 1;
        if (target instanceof EntityTopielec)
            damage = attackDamage;
        target.attackEntityFrom(DamageSource.generic, damage);
        stack.damageItem(1, attacker);
        return true;
    }


    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List tooltip, boolean p_77624_4_) {
        String msg = I18n.format("item.dmonsters.add_information.harpoon_1");
        tooltip.add(msg);

        if (ModConfig.topielecHarpoonOnly) {
            msg = I18n.format("item.dmonsters.add_information.harpoon_2");
            tooltip.add(msg);
        }
    }

    private List<Item> createDropTable()
    {
        List<Item> items = new ArrayList<>();
        items.add(new ItemStack(Items.fish, 1, 0).getItem());
        items.add(new ItemStack(Items.fish, 1, 1).getItem());
        items.add(new ItemStack(Items.fish, 1, 2).getItem());
        items.add(new ItemStack(Items.fish, 1, 3).getItem());
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
