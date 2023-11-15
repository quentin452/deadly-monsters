package com.dmonsters.main;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

public class BiomesProvider
{
    public static BiomeGenBase[] getCommonBiomes()
    {
        List<BiomeGenBase> biomes = new ArrayList<>();
        for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray())
        {
            if (isValidBiome(biome))
            {
                biomes.add(biome);
            }
        }
        return biomes.toArray(new BiomeGenBase[0]);
    }

    public static BiomeGenBase[] getSnowBiomes()
    {
        List<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>();
        for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray())
        {
            if (isValidBiome(biome))
            {
                if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SNOWY) || BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.COLD))
                {
                    biomes.add(biome);
                }
            }
        }
        return biomes.toArray(new BiomeGenBase[0]);
    }

    public static BiomeGenBase[] getWaterBiomes()
    {
        List<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>();
        for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray())
        {
            if (isValidBiome(biome))
            {
                if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.WATER))
                {
                    biomes.add(biome);
                }
            }
        }
        return biomes.toArray(new BiomeGenBase[0]);
    }

    public static boolean isValidBiome(BiomeGenBase biome)
    {
        return !BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.NETHER) && !BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.END) && !BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MUSHROOM);
    }
}
