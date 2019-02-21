package nl.axel.extratools.init;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import nl.axel.extratools.ExtraTools;
import nl.axel.extratools.items.*;
import nl.axel.extratools.util.Names;


public class ModItems {

    //Basic Items
    public static ItemBasic copper_ingot = new ItemBasic( Names.Items.COPPER_INGOT);
    public static ItemBasic tin_ingot = new ItemBasic(Names.Items.TIN_INGOT);
    public static ItemBasic bronze_alloy = new ItemBasic(Names.Items.BRONZE_ALLOY);
    public static ItemBasic bronze_ingot = new ItemBasic(Names.Items.BRONZE_INGOT);

    //Pickaxes
    public static BasicToolPickaxe copper_pickaxe = new BasicToolPickaxe(ExtraTools.copperToolMaterial, Names.Items.COPPER_PICKAXE);
    public static BasicToolPickaxe tin_pickaxe = new BasicToolPickaxe(ExtraTools.tinToolMaterial, Names.Items.TIN_PICKAXE);
    public static BasicToolPickaxe bronze_pickaxe = new BasicToolPickaxe(ExtraTools.bronzeToolMaterial, Names.Items.BRONZE_PICKAXE);


    //Swords
    public static BasicToolSword copper_sword = new BasicToolSword(ExtraTools.copperToolMaterial, Names.Items.COPPER_SWORD);
    public static BasicToolSword tin_sword = new BasicToolSword(ExtraTools.tinToolMaterial, Names.Items.TIN_SWORD);
    public static BasicToolSword bronze_sword = new BasicToolSword(ExtraTools.bronzeToolMaterial, Names.Items.BRONZE_SWORD);

    //Axes
    public static final BasicToolAxe copper_axe = new BasicToolAxe(ExtraTools.copperToolMaterial, 8f, -3.1f, Names.Items.COPPER_AXE);
    public static final BasicToolAxe tin_axe = new BasicToolAxe(ExtraTools.tinToolMaterial, 8f, -3.1f, Names.Items.TIN_AXE);
    public static final BasicToolAxe bronze_axe = new BasicToolAxe(ExtraTools.bronzeToolMaterial, 8f, -3.0f, Names.Items.BRONZE_AXE);

    //Hoes
    public static final BasicToolHoe copper_hoe = new BasicToolHoe(ExtraTools.copperToolMaterial, Names.Items.COPPER_HOE);
    public static final BasicToolHoe tin_hoe = new BasicToolHoe(ExtraTools.tinToolMaterial, Names.Items.TIN_HOE);
    public static final BasicToolHoe bronze_hoe = new BasicToolHoe(ExtraTools.bronzeToolMaterial, Names.Items.BRONZE_HOE);

    //Shovel
    public static final BasicToolShovel copper_shovel = new BasicToolShovel(ExtraTools.copperToolMaterial, Names.Items.COPPER_SHOVEL);
    public static final BasicToolShovel tin_shovel = new BasicToolShovel(ExtraTools.tinToolMaterial, Names.Items.TIN_SHOVEL);
    public static final BasicToolShovel bronze_shovel = new BasicToolShovel(ExtraTools.bronzeToolMaterial, Names.Items.BRONZE_SHOVEL);

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                copper_ingot,
                tin_ingot,
                bronze_ingot,
                bronze_alloy,

                copper_pickaxe,
                tin_pickaxe,
                bronze_pickaxe,

                copper_sword,
                tin_sword,
                bronze_sword,

                copper_axe,
                tin_axe,
                bronze_axe,

                copper_hoe,
                tin_hoe,
                bronze_hoe,

                copper_shovel,
                tin_shovel,
                bronze_shovel
        );
    }

    public static void registerModels() {
        copper_ingot.registerItemModel();
        tin_ingot.registerItemModel();
        bronze_ingot.registerItemModel();
        bronze_alloy.registerItemModel();

        copper_pickaxe.registerItemModel();
        tin_pickaxe.registerItemModel();
        bronze_pickaxe.registerItemModel();

        copper_sword.registerItemModel();
        tin_sword.registerItemModel();
        bronze_sword.registerItemModel();

        copper_axe.registerItemModel();
        tin_axe.registerItemModel();
        bronze_axe.registerItemModel();

        copper_hoe.registerItemModel();
        tin_hoe.registerItemModel();
        bronze_hoe.registerItemModel();

        copper_shovel.registerItemModel();
        tin_shovel.registerItemModel();
        bronze_shovel.registerItemModel();
    }
}