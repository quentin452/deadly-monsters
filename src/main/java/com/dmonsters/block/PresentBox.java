package com.dmonsters.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.main.ModItems;

public class PresentBox extends Block
{
    protected static final AxisAlignedBB AABB = AxisAlignedBB.getBoundingBox(0.13, 0.0D, 0.13, 0.87, 0.75, 0.87);

    public PresentBox()
    {
        super(Material.cactus);
        setBlockTextureName(DeadlyMonsters.MOD_ID + ".present_box");
        setBlockName("present_box");
        setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
        this.setHardness(1);
        this.setResistance(50);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return true;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int x, int y, int z)
    {
        return AABB;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 1;
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ)
    {
        if (!worldIn.isRemote)
        {
            Random rand = new Random();
            float rndNum = rand.nextFloat();

            if (rndNum < 0.7F)
            {
                worldIn.setBlockToAir(x, y, z);
                worldIn.createExplosion(null, player.posX, player.posY, player.posZ, 1, true);
            }
            else if (rndNum > 0.7F && rndNum < 0.8F)
            {
                Item spawnedItem = spawnRandomItem();
                ItemStack newItem = new ItemStack(spawnedItem, 1);
                EntityItem item = new EntityItem(worldIn, player.posX, player.posY, player.posZ, newItem);
                worldIn.spawnEntityInWorld(item);
                worldIn.setBlockToAir(x, y, z);
            }
            else if (rndNum > 0.8F && rndNum < 0.95F)
            {
                EntityLiving entity = spawnMonster(worldIn);
                entity.setLocationAndAngles(player.posX, player.posY, player.posZ, player.rotationYaw, 0.0F);
                worldIn.spawnEntityInWorld(entity);
                worldIn.setBlockToAir(x, y, z);
            }
            else
            {
                int newMetadata = rand.nextInt(16);
                worldIn.setBlockMetadataWithNotify(x, y, z, newMetadata, 3);
            }
        }
        return true;
    }

    @Override
    public PresentBox setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(tab);
        return this;
    }

    private Item spawnRandomItem()
    {
        List<Item> itemsList = new ArrayList<>();
        itemsList.add(Items.apple);
        itemsList.add(Items.gold_nugget);
        itemsList.add(Items.leather_helmet);
        itemsList.add(Items.fish);
        itemsList.add(Items.redstone);
        itemsList.add(ModItems.mob_spawner_item_present);
        itemsList.add(Items.gunpowder);
        itemsList.add(Items.redstone);
        itemsList.add(Items.iron_ingot);
        itemsList.add(Items.iron_sword);
        Random rand = new Random();
        int rndNum = rand.nextInt(itemsList.size());
        return itemsList.get(rndNum);
    }

    private EntityLiving spawnMonster(World worldIn)
    {
        List<EntityLiving> monstersList = new ArrayList<>();
        monstersList.add(new EntityCreeper(worldIn));
        monstersList.add(new EntityZombie(worldIn));
        monstersList.add(new EntitySkeleton(worldIn));
        monstersList.add(new EntitySilverfish(worldIn));
        monstersList.add(new EntityBlaze(worldIn));
        monstersList.add(new EntityMagmaCube(worldIn));
        monstersList.add(new EntityPigZombie(worldIn));
        Random rand = new Random();
        int rndNum = rand.nextInt(monstersList.size());
        return monstersList.get(rndNum);
    }
}
