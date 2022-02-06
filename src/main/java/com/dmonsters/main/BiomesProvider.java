package com.dmonsters.main;

import java.util.List;

import com.google.common.collect.Lists;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class BiomesProvider
{
    public static Biome[] getCommonBiomes()
    {
        List<Biome> biomes = Lists.newArrayList();
        for (Biome biome : Biome.REGISTRY)
        {
            if (isValidBiome(biome))
            {
                biomes.add(biome);
            }
        }
        return biomes.toArray(new Biome[0]);
    }

    public static Biome[] getSnowBiomes()
    {
        List<Biome> biomes = Lists.newArrayList();
        for (Biome biome : Biome.REGISTRY)
        {
            if (isValidBiome(biome))
            {
                if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.SNOWY) || BiomeDictionary.hasType(biome, BiomeDictionary.Type.COLD))
                {
                    biomes.add(biome);
                }
            }
        }
        return biomes.toArray(new Biome[0]);
    }

    public static Biome[] getWaterBiomes()
    {
        List<Biome> biomes = Lists.newArrayList();
        for (Biome biome : Biome.REGISTRY)
        {
            if (isValidBiome(biome))
            {
                if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.WATER))
                {
                    biomes.add(biome);
                }
            }
        }
        return biomes.toArray(new Biome[0]);
    }

    public static boolean isValidBiome(Biome biome)
    {
        return !BiomeDictionary.hasType(biome, BiomeDictionary.Type.NETHER) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.END) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.MUSHROOM);
    }
}