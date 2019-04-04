package nl.axel.extratools.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import nl.axel.extratools.blocks.BlockCounter;
import nl.axel.extratools.blocks.BlockForge;
import nl.axel.extratools.blocks.BlockOre;
import nl.axel.extratools.blocks.BlockSmeltery;
import nl.axel.extratools.util.Names;
import scala.xml.PrettyPrinter;

public class ModBlocks {

    public static BlockOre copperOre = new BlockOre(Names.Blocks.COPPER_ORE);
    public static BlockOre tinOre = new BlockOre(Names.Blocks.TIN_ORE);
    public static BlockForge forge = new BlockForge();
    public static BlockSmeltery smeltery = new BlockSmeltery();

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                copperOre,
                tinOre,
                forge,
                smeltery
        );

        GameRegistry.registerTileEntity(forge.getTileEntityClass(), forge.getRegistryName().toString());
        GameRegistry.registerTileEntity(smeltery.getTileEntityClass(), smeltery.getRegistryName().toString());

    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                copperOre.createItemBlock(),
                tinOre.createItemBlock(),
                forge.createItemBlock(),
                smeltery.createItemBlock()
        );
    }

    public static void registerModels() {
        copperOre.registerItemModel(Item.getItemFromBlock(copperOre));
        tinOre.registerItemModel(Item.getItemFromBlock(tinOre));
        forge.registerItemModel(Item.getItemFromBlock(forge));
        smeltery.registerItemModel(Item.getItemFromBlock(smeltery));
    }
}
