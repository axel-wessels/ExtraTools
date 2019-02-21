package nl.axel.extratools.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import nl.axel.extratools.init.ModBlocks;

public class ExtraToolsTab extends CreativeTabs {
    public ExtraToolsTab() {
        super(Reference.MOD_ID);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModBlocks.copperOre);
    }
}
