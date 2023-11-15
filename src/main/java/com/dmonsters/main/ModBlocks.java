package com.dmonsters.main;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

import com.dmonsters.DeadlyMonsters;
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

    public static class RegistrationHandler
    {

        private static Item itemRegistry;
        public static void register(FMLInitializationEvent e) {
            registerBlocks();
            registerItemBlocks();
        }
        public static void registerBlocks() {
            init();
            registerBlock(strengthened_stone);
            registerBlock(strengthened_cobblestone);
            registerBlock(soul_eye);
            registerBlock(christmas_tree);
            registerBlock(dump);
            registerBlock(barbed_wire);
            registerBlock(mesh_fence);
            registerBlock(mesh_fence_pole);
            registerBlock(present_block);
            registerBlock(present_box);
        }
        private static void registerBlock(Block block) {
            GameRegistry.registerBlock(block, block.getUnlocalizedName());
        }

        public static void registerItemBlocks() {
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

        private static void registerItemBlock(Block block) {
            ItemBlock itemBlock = new ItemBlock(block);
            GameRegistry.registerItem(itemBlock, block.getUnlocalizedName());
        }
    }
}
