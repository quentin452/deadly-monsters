package com.dmonsters.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import com.dmonsters.DeadlyMonsters;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.network.PacketClientFXUpdate;
import com.dmonsters.network.PacketHandler;

public class SoulEye extends Block
{
    public SoulEye()
    {
        super(Material.iron);
        setBlockTextureName(DeadlyMonsters.MOD_ID + ".soul_eye");
        setBlockName("soul_eye");
        setCreativeTab(DeadlyMonsters.MOD_CREATIVE_TAB);
        this.setHardness(3);
        this.setResistance(3);
        this.setTickRandomly(true);
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public void updateTick(World worldIn, int x, int y, int z, Random random)
    {
        Block block = worldIn.getBlock(x, y, z);

        if (!(block instanceof SoulEye))
            return;

        float lightLevel = worldIn.getBlockLightValue(x, y, z);
        int meta = worldIn.getBlockMetadata(x, y, z);
        EnumMode mode = EnumMode.getStateFromMeta(meta);

        if (lightLevel <= 12)
        {
            if (mode == EnumMode.SLEEP)
                worldIn.setBlock(x, y, z, ModBlocks.soul_eye, 1, 2);
            else if (mode == EnumMode.AWAKING)
                worldIn.setBlock(x, y, z, ModBlocks.soul_eye, 2, 2);
            else if (mode == EnumMode.AWAKE)
                killLivingNearby(worldIn, x, y, z);
        }
        else
        {
            if (mode == EnumMode.AWAKING)
                worldIn.setBlock(x, y, z, ModBlocks.soul_eye, 0, 2);
            else if (mode == EnumMode.AWAKE)
                worldIn.setBlock(x, y, z, ModBlocks.soul_eye, 1, 2);
        }
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World worldIn, int x, int y, int z, Random random)
    {
        ChunkCoordinates pos = new ChunkCoordinates(x, y, z);
        int meta = worldIn.getBlockMetadata(x, y, z);
        EnumMode mode = EnumMode.getStateFromMeta(meta);

        if (mode != EnumMode.AWAKE)
            return;

        for (int xOffset = -4; xOffset < 4; xOffset++)
        {
            for (int zOffset = -4; zOffset < 4; zOffset++)
            {
                for (int i = 0; i < 1; i++)
                {
                    double motionX = random.nextGaussian() * 0.001D;
                    double motionY = Math.abs(random.nextGaussian() * 0.02D);
                    double motionZ = random.nextGaussian() * 0.001D;
                    float randX = random.nextFloat();
                    float randY = random.nextFloat();
                    float randZ = random.nextFloat();

                    worldIn.spawnParticle("smoke",
                        pos.posX + xOffset + 0.5F + randX,
                        pos.posY + randY,
                        pos.posZ + zOffset + 0.5F + randZ,
                        motionX,
                        motionY,
                        motionZ
                    );
                }
            }
        }
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 1;
    }

    @Override
    public SoulEye setCreativeTab(CreativeTabs tab)
    {
        super.setCreativeTab(tab);
        return this;
    }

    private void killLivingNearby(World worldIn, int x, int y, int z) {
        if (!worldIn.isRemote) {
            int range = 4;
            AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox(x - range, y, z - range, x + range, y + range, z + range);
            List<EntityLiving> entities = worldIn.getEntitiesWithinAABB(EntityLiving.class, boundingBox);

            for (Entity entity : entities) {
                spawnItem(entity);
                entity.setDead();
                PacketHandler.INSTANCE.sendToAll(new PacketClientFXUpdate(
                    new ChunkCoordinates((int) entity.posX, (int) entity.posY, (int) entity.posZ),
                    PacketClientFXUpdate.Type.SOULEYE
                ));
            }
        }
    }


    private void spawnItem(Entity entity)
    {
        Random rnd = new Random();
        float rndFloat = rnd.nextFloat();
        if (rndFloat > 0.5F)
            return;
        List<Item> itemsList = createDropTable();
        Item item = getItemToSpawn(itemsList);
        entity.dropItem(item, 1);
    }

    private List<Item> createDropTable()
    {
        List<Item> items = new ArrayList<>();
        items.add(Items.emerald);
        items.add(Items.gold_nugget);
        items.add(Items.gunpowder);
        items.add(Items.redstone);
        items.add(Items.iron_ingot);
        items.add(Items.quartz);
        return items;
    }

    private Item getItemToSpawn(List<Item> items)
    {
        int itemsNumber = items.size();
        Random rnd = new Random();
        int randomItem = rnd.nextInt(itemsNumber);
        return items.get(randomItem);
    }

    public enum EnumMode
    {
        SLEEP(0, "sleep"),
        AWAKING(1, "awaking"),
        AWAKE(2, "awake");

        public static EnumMode getStateFromMeta(int meta)
        {
            EnumMode mode = EnumMode.SLEEP;
            switch (meta)
            {
                case 0:
                    break;
                case 1:
                    mode = EnumMode.AWAKING;
                    break;
                case 2:
                    mode = EnumMode.AWAKE;
                    break;
            }
            return mode;
        }

        private final int ID;
        private final String name;

        EnumMode(int ID, String name)
        {
            this.ID = ID;
            this.name = name;
        }


        public String getName()
        {
            return name;
        }

        public int getID()
        {
            return ID;
        }
    }
          @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
}
