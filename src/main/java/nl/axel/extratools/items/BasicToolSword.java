package nl.axel.extratools.items;

import net.minecraft.item.ItemSword;
import nl.axel.extratools.ExtraTools;

public class BasicToolSword extends ItemSword {

    private String name;

    public BasicToolSword(ToolMaterial material, String name) {
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
