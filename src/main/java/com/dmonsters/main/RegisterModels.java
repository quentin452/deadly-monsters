package com.dmonsters.main;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class RegisterModels
{
    @SubscribeEvent
    public static void registerModels(final ModelRegistryEvent event)
    {
        registerItemModels();
        registerBlockItemModels();
    }

    private static void registerItemModels()
    {
        registerItemModel(ModItems.rebar);
        registerItemModel(ModItems.lucky_egg);
        registerItemModel(ModItems.unborn_baby_eye);
        registerItemModel(ModItems.bloody_maiden_heart);
        registerItemModel(ModItems.fallen_leader_spine);
        registerItemModel(ModItems.entrail_flesh);
        registerItemModel(ModItems.poopoo_pill);
        registerItemModel(ModItems.dagon);
        registerItemModel(ModItems.flying_dagon);
        registerItemModel(ModItems.mod_item);
        registerItemModel(ModItems.sunlight_drop);
        registerItemModel(ModItems.mob_spawner_item_unborn_baby);
        registerItemModel(ModItems.mob_spawner_item_climber);
        registerItemModel(ModItems.mob_spawner_item_entrail);
        registerItemModel(ModItems.mob_spawner_item_freezer);
        registerItemModel(ModItems.mob_spawner_item_mutant_steve);
        registerItemModel(ModItems.mob_spawner_item_fallen_leader);
        registerItemModel(ModItems.mob_spawner_item_bloody_maiden);
        registerItemModel(ModItems.mob_spawner_item_zombie_chicken);
        registerItemModel(ModItems.mob_spawner_item_present);
        registerItemModel(ModItems.mob_spawner_item_stranger);
        registerItemModel(ModItems.mob_spawner_item_haunted_cow);
        registerItemModel(ModItems.mob_spawner_item_topielec);
        registerItemModel(ModItems.harpoon_stone);
        registerItemModel(ModItems.harpoon_iron);
        registerItemModel(ModItems.harpoon_diamond);
        registerItemModel(ModItems.harpoon_obsidian);
    }

    private static void registerBlockItemModels()
    {
        registerBlockItemModel(ModBlocks.strengthened_stone);
        registerBlockItemModel(ModBlocks.strengthened_cobblestone);
        registerBlockItemModel(ModBlocks.soul_eye);
        registerBlockItemModel(ModBlocks.christmas_tree);
        registerBlockItemModel(ModBlocks.dump);
        registerBlockItemModel(ModBlocks.barbed_wire);
        registerBlockItemModel(ModBlocks.mesh_fence);
        registerBlockItemModel(ModBlocks.mesh_fence_pole);
        registerBlockItemModel(ModBlocks.present_block);
        registerBlockItemModel(ModBlocks.present_box);
    }

    private static void registerItemModel(Item item)
    {
        final ModelResourceLocation fullModelLocation = new ModelResourceLocation(item.getRegistryName().toString(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, fullModelLocation);
    }

    private static void registerBlockItemModel(Block block)
    {
        final Item item = Item.getItemFromBlock(block);
        final ModelResourceLocation fullModelLocation = new ModelResourceLocation(item.getRegistryName().toString(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, fullModelLocation);
    }
}