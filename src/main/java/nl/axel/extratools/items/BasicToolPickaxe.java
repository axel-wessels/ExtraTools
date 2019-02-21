package nl.axel.extratools.items;

import net.minecraft.item.ItemPickaxe;
import nl.axel.extratools.ExtraTools;

public class BasicToolPickaxe extends ItemPickaxe {

    private String name;

    public BasicToolPickaxe(ToolMaterial material, String name) {
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
