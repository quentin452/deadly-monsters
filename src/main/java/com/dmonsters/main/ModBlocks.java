package com.dmonsters.main;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import com.dmonsters.blocks.*;

public class ModBlocks
{

    public static StrengthenedStone strengthenedStone;
    public static StrengthenedCobblestone strengthenedCobblestone;
    public static BarbedWire barbedWire;
    public static MeshFence meshFence;
    public static MeshFencePole meshFencePole;
    public static Dump dump;
    public static SoulEye souleye;
    public static PresentBlock presentBlock;
    public static ChristmasTree christmasTree;
    public static PresentBox presentBox;

    public static void init()
    {
        strengthenedStone = new StrengthenedStone();
        strengthenedCobblestone = new StrengthenedCobblestone();
        barbedWire = new BarbedWire();
        meshFence = new MeshFence();
        meshFencePole = new MeshFencePole();
        dump = new Dump();
        souleye = new SoulEye();
        presentBlock = new PresentBlock();
        christmasTree = new ChristmasTree();
        presentBox = new PresentBox();
    }

    @Mod.EventBusSubscriber(modid = MainMod.MODID)
    public static class RegistrationHandler
    {

        private static IForgeRegistry<Item> itemRegistry;

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event)
        {
            final IForgeRegistry<Block> registry = event.getRegistry();
            init();
            registry.register(strengthenedStone);
            registry.register(strengthenedCobblestone);
            registry.register(souleye);
            registry.register(christmasTree);
            registry.register(dump);
            registry.register(barbedWire);
            registry.register(meshFence);
            registry.register(meshFencePole);
            registry.register(presentBlock);
            registry.register(presentBox);
        }

        @SubscribeEvent
        public static void registerItemBlocks(final RegistryEvent.Register<Item> event)
        {
            itemRegistry = event.getRegistry();
            registerItemBlock(strengthenedStone);
            registerItemBlock(strengthenedCobblestone);
            registerItemBlock(souleye);
            registerItemBlock(christmasTree);
            registerItemBlock(dump);
            registerItemBlock(barbedWire);
            registerItemBlock(meshFence);
            registerItemBlock(meshFencePole);
            registerItemBlock(presentBlock);
            registerItemBlock(presentBox);
        }

        private static void registerItemBlock(Block block)
        {
            final Item itemBlock = new ItemBlock(block);
            final ResourceLocation registryName = block.getRegistryName();
            itemRegistry.register(itemBlock.setRegistryName(registryName));
        }
    }
}
