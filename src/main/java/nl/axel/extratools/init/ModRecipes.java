package nl.axel.extratools.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {

    public static void init(){
        //smelting recipes
        GameRegistry.addSmelting(ModBlocks.copperOre, new ItemStack(ModItems.copper_ingot), 0.7f);
        GameRegistry.addSmelting(ModBlocks.tinOre, new ItemStack(ModItems.tin_ingot), 0.7f);
        GameRegistry.addSmelting(ModItems.bronze_alloy, new ItemStack(ModItems.bronze_ingot, 4), 0.7f);
    }
}
