package com.dmonsters.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

import com.dmonsters.DeadlyMonsters;

public class PresentBlock extends Block
{

    public PresentBlock()
    {
        super(Material.iron);
        setBlockTextureName(DeadlyMonsters.MOD_ID + ".present_block");
        setBlockName("present_block");
        this.setHardness(3);
        this.setResistance(50);
        this.setTickRandomly(true);
    }

    public void updateTick(World worldIn, int x, int y, int z, Random random)
    {
        worldIn.setBlockToAir(x,y,z);
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }

    public enum EnumMode
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
