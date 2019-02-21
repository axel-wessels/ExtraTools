package nl.axel.extratools.items;

import net.minecraft.item.ItemHoe;
import nl.axel.extratools.ExtraTools;

public class BasicToolHoe extends ItemHoe {

    private String name;
    public BasicToolHoe(ToolMaterial material, String name) {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(ExtraTools.creativeTab);
        this.name = name;
    }

    public void registerItemModel() {
        ExtraTools.proxy.registerItemRenderer(this, 0, name);
    }
}
