package com.dmonsters.main;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import com.dmonsters.block.*;

public class ModBlocks
{
    public static StrengthenedStone strengthened_stone;
    public static StrengthenedCobblestone strengthened_cobblestone;
    public static BarbedWire barbed_wire;
    public static MeshFence mesh_fence;
    public static MeshFencePole mesh_fence_pole;
    public static Dump dump;
    public static SoulEye soul_eye;
    public static PresentBlock present_block;
    public static ChristmasTree christmas_tree;
    public static PresentBox present_box;

    public static void init()
    {
        strengthened_stone = new StrengthenedStone();
        strengthened_cobblestone = new StrengthenedCobblestone();
        barbed_wire = new BarbedWire();
        mesh_fence = new MeshFence();
        mesh_fence_pole = new MeshFencePole();
        dump = new Dump();
        soul_eye = new SoulEye();
        present_block = new PresentBlock();
        christmas_tree = new ChristmasTree();
        present_box = new PresentBox();
    }

    @Mod.EventBusSubscriber(modid = MainMod.MOD_ID)
    public static class RegistrationHandler
    {

        private static IForgeRegistry<Item> itemRegistry;

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event)
        {
            final IForgeRegistry<Block> registry = event.getRegistry();
            init();
            registry.register(strengthened_stone);
            registry.register(strengthened_cobblestone);
            registry.register(soul_eye);
            registry.register(christmas_tree);
            registry.register(dump);
            registry.register(barbed_wire);
            registry.register(mesh_fence);
            registry.register(mesh_fence_pole);
            registry.register(present_block);
            registry.register(present_box);
        }

        @SubscribeEvent
        public static void registerItemBlocks(final RegistryEvent.Register<Item> event)
        {
            itemRegistry = event.getRegistry();
            registerItemBlock(strengthened_stone);
            registerItemBlock(strengthened_cobblestone);
            registerItemBlock(soul_eye);
            registerItemBlock(christmas_tree);
            registerItemBlock(dump);
            registerItemBlock(barbed_wire);
            registerItemBlock(mesh_fence);
            registerItemBlock(mesh_fence_pole);
            registerItemBlock(present_block);
            registerItemBlock(present_box);
        }

        private static void registerItemBlock(Block block)
        {
            final Item itemBlock = new ItemBlock(block);
            final ResourceLocation registryName = block.getRegistryName();
            itemRegistry.register(itemBlock.setRegistryName(registryName));
        }
    }
}