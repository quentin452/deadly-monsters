package com.dmonsters.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.dmonsters.main.MainMod;

public class PresentBlock extends Block
{
    public static final PropertyEnum<EnumMode> COLOR = PropertyEnum.create("color", EnumMode.class);

    public PresentBlock()
    {
        super(Material.IRON);
        setUnlocalizedName(MainMod.MODID + ".present_block");
        setRegistryName("present_block");
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
        return state.getValue(COLOR).getID();
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
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