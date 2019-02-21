package nl.axel.extratools.items;

import net.minecraft.item.ItemSpade;
import nl.axel.extratools.ExtraTools;

public class BasicToolShovel extends ItemSpade {

    private String name;

    public BasicToolShovel(ToolMaterial material, String name) {
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
