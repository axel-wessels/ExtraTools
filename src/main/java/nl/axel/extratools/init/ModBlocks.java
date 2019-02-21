package nl.axel.extratools.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import nl.axel.extratools.blocks.BlockCounter;
import nl.axel.extratools.blocks.BlockForge;
import nl.axel.extratools.blocks.BlockOre;
import nl.axel.extratools.util.Names;

public class ModBlocks {

    public static BlockOre copperOre = new BlockOre(Names.Blocks.COPPER_ORE);
    public static BlockOre tinOre = new BlockOre(Names.Blocks.TIN_ORE);
    public static BlockCounter counter = new BlockCounter();
    public static BlockForge forge = new BlockForge();

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                copperOre,
                tinOre,
                counter,
                forge
        );

        GameRegistry.registerTileEntity(counter.getTileEntityClass(), counter.getRegistryName().toString());
        GameRegistry.registerTileEntity(forge.getTileEntityClass(), forge.getRegistryName().toString());

    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                copperOre.createItemBlock(),
                tinOre.createItemBlock(),
                counter.createItemBlock(),
                forge.createItemBlock()
        );
    }

    public static void registerModels() {
        copperOre.registerItemModel(Item.getItemFromBlock(copperOre));
        tinOre.registerItemModel(Item.getItemFromBlock(tinOre));
        counter.registerItemModel(Item.getItemFromBlock(counter));
        forge.registerItemModel(Item.getItemFromBlock(forge));
    }
}
