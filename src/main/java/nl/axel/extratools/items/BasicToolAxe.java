package nl.axel.extratools.items;

import net.minecraft.item.ItemAxe;
import nl.axel.extratools.ExtraTools;

public class BasicToolAxe extends ItemAxe {

    private String name;

    public BasicToolAxe(ToolMaterial material, float damage, float speed, String name) {
        super(material, damage, speed);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(ExtraTools.creativeTab);
        this.name = name;
    }

    public void registerItemModel() {
        ExtraTools.proxy.registerItemRenderer(this, 0, name);
    }

}
