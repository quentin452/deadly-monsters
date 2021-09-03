package com.dmonsters.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.dmonsters.main.IMetaBlockName;
import com.dmonsters.main.MainMod;

public class PresentBlock extends Block implements IMetaBlockName
{

    public static final PropertyEnum COLOR = PropertyEnum.create("color", EnumMode.class);

    public PresentBlock()
    {
        super(Material.IRON);
        setUnlocalizedName(MainMod.MODID + ".presentblock");
        setRegistryName("presentblock");
        this.setHardness(3);
        this.setResistance(50);
        this.setTickRandomly(true);
    }

    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(COLOR, EnumMode.getStateFromMeta(meta));
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((EnumMode) state.getValue(COLOR)).getID();
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        Random random = new Random();
        float rndNum = random.nextFloat();
        worldIn.setBlockToAir(pos);
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, COLOR);
    }

    @Override
    public String getSpecialName(ItemStack stack)
    {
        return EnumMode.getColorFromMeta(stack.getMetadata());
    }

    public enum EnumMode implements IStringSerializable
    {
        GREEN(0, "green"),
        YELLOW(1, "yellow");

        public static String getColorFromMeta(int meta)
        {
            String value = null;
            switch (meta)
            {
                case 0:
                    value = "green";
                    break;
                case 1:
                    value = "yellow";
                    break;
            }
            return value;
        }

        public static EnumMode getStateFromMeta(int meta)
        {
            EnumMode mode = EnumMode.GREEN;
            switch (meta)
            {
                case 0:
                    mode = EnumMode.GREEN;
                    break;
                case 1:
                    mode = EnumMode.YELLOW;
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

        @Override
        public String getName()
        {
            return name;
        }

        public int getID()
        {
            return ID;
        }
    }
}
