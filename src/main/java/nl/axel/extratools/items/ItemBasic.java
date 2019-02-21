package nl.axel.extratools.items;

import net.minecraft.item.Item;
import nl.axel.extratools.ExtraTools;

public class ItemBasic extends Item {

    protected String name;
    public ItemBasic(String name) {
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ExtraTools.creativeTab);
    }

    public void registerItemModel() {
        ExtraTools.proxy.registerItemRenderer(this, 0, name);
    }
}
