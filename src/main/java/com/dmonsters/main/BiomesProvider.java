package com.dmonsters.main;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class BiomesProvider
{
    public static Biome[] getBiomes()
    {
        List<Biome> biomes = Lists.newArrayList();
        for (Biome b : Biome.REGISTRY)
        {
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(b);
            if (!types.contains(BiomeDictionary.Type.WATER) || !types.contains(BiomeDictionary.Type.NETHER) || !types.contains(BiomeDictionary.Type.END))
            {
                biomes.add(b);
            }
        }
        return biomes.toArray(new Biome[0]);
    }

    public static Biome[] getSnowBiomes()
    {
        List<Biome> biomes = Lists.newArrayList();
        for (Biome b : Biome.REGISTRY)
        {
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(b);
            if (types.contains(BiomeDictionary.Type.SNOWY) || types.contains(BiomeDictionary.Type.COLD))
            {
                biomes.add(b);
            }
        }
        return biomes.toArray(new Biome[0]);
    }

    public static Biome[] getWaterBiomes()
    {
        List<Biome> biomes = Lists.newArrayList();
        for (Biome b : Biome.REGISTRY)
        {
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(b);
            if (types.contains(BiomeDictionary.Type.WATER))
            {
                biomes.add(b);
            }
        }
        return biomes.toArray(new Biome[0]);
    }
}