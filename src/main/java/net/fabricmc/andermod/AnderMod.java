package net.fabricmc.andermod;

import net.fabricmc.andermod.armor.ApronArmorMaterial;
import net.fabricmc.andermod.block.DoorbellBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AnderMod implements ModInitializer {
        public static final String MOD_ID = "andermod";

        // Sound Effects
        public static final Identifier DOORBELL_SOUND = new Identifier("andermod:doorbell");
        public static SoundEvent DOORBELL_EVENT = new SoundEvent(DOORBELL_SOUND);

        public static final Identifier APRON_SOUND = new Identifier("andermod:item.armor.equip_apron");
        public static SoundEvent APRON_SOUND_EVENT = new SoundEvent(APRON_SOUND);

        // Creative tab for mod
        public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
                        new Identifier(MOD_ID, "general"),
                        () -> new ItemStack(AnderMod.FLOOR_TILES_BLOCK));

        // Wearable Apron
        public static final ArmorMaterial APRON_MATERIAL = new ApronArmorMaterial();
        public static final Item APRON_MATERIAL_CHESTPLATE = new ArmorItem(APRON_MATERIAL, EquipmentSlot.CHEST,
                        new Item.Settings().group(AnderMod.ITEM_GROUP));

        // Floor Tiles Block
        public static final Block FLOOR_TILES_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).strength(0.5f));

        // Doorbell Variants
        public static final DoorbellBlock OAK_DOORBELL = new DoorbellBlock(
                        FabricBlockSettings.of(Material.WOOD).strength(0.5f).nonOpaque());
        public static final DoorbellBlock SPRUCE_DOORBELL = new DoorbellBlock(
                        FabricBlockSettings.of(Material.WOOD).strength(0.5f).nonOpaque());
        public static final DoorbellBlock BIRCH_DOORBELL = new DoorbellBlock(
                        FabricBlockSettings.of(Material.WOOD).strength(0.5f).nonOpaque());
        public static final DoorbellBlock JUNGLE_DOORBELL = new DoorbellBlock(
                        FabricBlockSettings.of(Material.WOOD).strength(0.5f).nonOpaque());
        public static final DoorbellBlock ACACIA_DOORBELL = new DoorbellBlock(
                        FabricBlockSettings.of(Material.WOOD).strength(0.5f).nonOpaque());
        public static final DoorbellBlock DARK_OAK_DOORBELL = new DoorbellBlock(
                        FabricBlockSettings.of(Material.WOOD).strength(0.5f).nonOpaque());

        // Currency Items
        public static final Item PENNY_ITEM = new Item(new FabricItemSettings().group(AnderMod.ITEM_GROUP));
        public static final Item ONE_DOLLAR_ITEM = new Item(new FabricItemSettings().group(AnderMod.ITEM_GROUP));
        public static final Item FIVE_DOLLAR_ITEM = new Item(new FabricItemSettings().group(AnderMod.ITEM_GROUP));
        public static final Item TEN_DOLLAR_ITEM = new Item(new FabricItemSettings().group(AnderMod.ITEM_GROUP));
        public static final Item TWENTY_DOLLAR_ITEM = new Item(new FabricItemSettings().group(AnderMod.ITEM_GROUP));
        public static final Item FIFTY_DOLLAR_ITEM = new Item(new FabricItemSettings().group(AnderMod.ITEM_GROUP));
        public static final Item HUNDRED_DOLLAR_ITEM = new Item(new FabricItemSettings().group(AnderMod.ITEM_GROUP));

        @Override
        public void onInitialize() {
                // Sound Effects
                Registry.register(Registry.SOUND_EVENT, AnderMod.DOORBELL_SOUND, DOORBELL_EVENT);
                Registry.register(Registry.SOUND_EVENT, AnderMod.APRON_SOUND, APRON_SOUND_EVENT);

                // Wearable Apron
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "apron"), APRON_MATERIAL_CHESTPLATE);

                // Doorbell Variants
                Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "oak_doorbell"), OAK_DOORBELL);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "oak_doorbell"),
                                new BlockItem(OAK_DOORBELL, new FabricItemSettings().group(AnderMod.ITEM_GROUP)));
                Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "spruce_doorbell"), SPRUCE_DOORBELL);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "spruce_doorbell"),
                                new BlockItem(SPRUCE_DOORBELL, new FabricItemSettings().group(AnderMod.ITEM_GROUP)));
                Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "birch_doorbell"), BIRCH_DOORBELL);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "birch_doorbell"),
                                new BlockItem(BIRCH_DOORBELL, new FabricItemSettings().group(AnderMod.ITEM_GROUP)));
                Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "jungle_doorbell"), JUNGLE_DOORBELL);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "jungle_doorbell"),
                                new BlockItem(JUNGLE_DOORBELL, new FabricItemSettings().group(AnderMod.ITEM_GROUP)));
                Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "acacia_doorbell"), ACACIA_DOORBELL);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "acacia_doorbell"),
                                new BlockItem(ACACIA_DOORBELL, new FabricItemSettings().group(AnderMod.ITEM_GROUP)));
                Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "dark_oak_doorbell"), DARK_OAK_DOORBELL);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_oak_doorbell"),
                                new BlockItem(DARK_OAK_DOORBELL, new FabricItemSettings().group(AnderMod.ITEM_GROUP)));

                // Floor Tiles Block
                Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "floor_tiles"), FLOOR_TILES_BLOCK);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "floor_tiles"),
                                new BlockItem(FLOOR_TILES_BLOCK, new FabricItemSettings().group(AnderMod.ITEM_GROUP)));

                // Currency Items
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "penny"), PENNY_ITEM);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "one_dollar"), ONE_DOLLAR_ITEM);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "five_dollar"), FIVE_DOLLAR_ITEM);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ten_dollar"), TEN_DOLLAR_ITEM);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "twenty_dollar"), TWENTY_DOLLAR_ITEM);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "fifty_dollar"), FIFTY_DOLLAR_ITEM);
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, "hundred_dollar"), HUNDRED_DOLLAR_ITEM);
        }
}
