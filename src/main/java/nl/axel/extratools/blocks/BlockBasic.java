package nl.axel.extratools.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import nl.axel.extratools.ExtraTools;

public class BlockBasic extends Block {

    protected String name;

    public BlockBasic(Material material, String name) {
        super(material);

        this.name = name;

        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ExtraTools.creativeTab);
    }

    public void registerItemModel(Item itemBlock) {
        ExtraTools.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

}
