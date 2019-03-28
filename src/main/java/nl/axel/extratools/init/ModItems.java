package nl.axel.extratools.init;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import nl.axel.extratools.ExtraTools;
import nl.axel.extratools.items.*;
import nl.axel.extratools.util.Names;


public class ModItems {

    //Basic Items
    public static final ItemBasic copper_ingot = new ItemBasic( Names.Items.COPPER_INGOT);
    public static final ItemBasic tin_ingot = new ItemBasic(Names.Items.TIN_INGOT);
    public static final ItemBasic bronze_alloy = new ItemBasic(Names.Items.BRONZE_ALLOY);
    public static final ItemBasic bronze_ingot = new ItemBasic(Names.Items.BRONZE_INGOT);
    public static final ItemBasic obsidian_pickaxehead = new ItemBasic(Names.Items.OBSIDIAN_PICKAXEHEAD);
    public static final ItemBasic obsidian_swordblade = new ItemBasic(Names.Items.OBSIDIAN_SWORDBLADE);
    public static final ItemBasic obsidian_shovelhead = new ItemBasic(Names.Items.OBSIDIAN_SHOVELHEAD);
    public static final ItemBasic obsidian_axehead = new ItemBasic(Names.Items.OBSIDIAN_AXEHEAD);
    public static final ItemBasic obsidian_hoehead = new ItemBasic(Names.Items.OBSIDIAN_HOEHEAD);
    public static final ItemBasic iron_rod = new ItemBasic(Names.Items.IRON_ROD);
    public static final ItemBasic bronze_gear = new ItemBasic(Names.Items.BRONZE_GEAR);

    /*
     * ToDo: Make Diamond pickaxe toolparts
     */

    //Pickaxes
    public static final BasicToolPickaxe copper_pickaxe = new BasicToolPickaxe(ExtraTools.copperToolMaterial, Names.Items.COPPER_PICKAXE);
    public static final BasicToolPickaxe tin_pickaxe = new BasicToolPickaxe(ExtraTools.tinToolMaterial, Names.Items.TIN_PICKAXE);
    public static final BasicToolPickaxe bronze_pickaxe = new BasicToolPickaxe(ExtraTools.bronzeToolMaterial, Names.Items.BRONZE_PICKAXE);
    public static final BasicToolPickaxe obsidian_pickaxe = new BasicToolPickaxe(ExtraTools.obsidianToolMaterial, Names.Items.OBSIDIAN_PICKAXE);


    //Swords
    public static final BasicToolSword copper_sword = new BasicToolSword(ExtraTools.copperToolMaterial, Names.Items.COPPER_SWORD);
    public static final BasicToolSword tin_sword = new BasicToolSword(ExtraTools.tinToolMaterial, Names.Items.TIN_SWORD);
    public static final BasicToolSword bronze_sword = new BasicToolSword(ExtraTools.bronzeToolMaterial, Names.Items.BRONZE_SWORD);
    public static final BasicToolSword obsidian_sword = new BasicToolSword(ExtraTools.obsidianToolMaterial, Names.Items.OBSIDIAN_SWORD);

    //Axes
    public static final BasicToolAxe copper_axe = new BasicToolAxe(ExtraTools.copperToolMaterial, 8f, -3.1f, Names.Items.COPPER_AXE);
    public static final BasicToolAxe tin_axe = new BasicToolAxe(ExtraTools.tinToolMaterial, 8f, -3.1f, Names.Items.TIN_AXE);
    public static final BasicToolAxe bronze_axe = new BasicToolAxe(ExtraTools.bronzeToolMaterial, 8f, -3.0f, Names.Items.BRONZE_AXE);
    public static final BasicToolAxe obsidian_axe = new BasicToolAxe(ExtraTools.obsidianToolMaterial,16f, -10.0f, Names.Items.OBSIDIAN_AXE);

    //Hoes
    public static final BasicToolHoe copper_hoe = new BasicToolHoe(ExtraTools.copperToolMaterial, Names.Items.COPPER_HOE);
    public static final BasicToolHoe tin_hoe = new BasicToolHoe(ExtraTools.tinToolMaterial, Names.Items.TIN_HOE);
    public static final BasicToolHoe bronze_hoe = new BasicToolHoe(ExtraTools.bronzeToolMaterial, Names.Items.BRONZE_HOE);
    public static final BasicToolHoe obsidian_hoe = new BasicToolHoe(ExtraTools.obsidianToolMaterial, Names.Items.OBSIDIAN_HOE);

    //Shovel
    public static final BasicToolShovel copper_shovel = new BasicToolShovel(ExtraTools.copperToolMaterial, Names.Items.COPPER_SHOVEL);
    public static final BasicToolShovel tin_shovel = new BasicToolShovel(ExtraTools.tinToolMaterial, Names.Items.TIN_SHOVEL);
    public static final BasicToolShovel bronze_shovel = new BasicToolShovel(ExtraTools.bronzeToolMaterial, Names.Items.BRONZE_SHOVEL);
    public static final BasicToolShovel obsidian_shovel = new BasicToolShovel(ExtraTools.obsidianToolMaterial, Names.Items.OBSIDIAN_SHOVEL);

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                copper_ingot,
                tin_ingot,
                bronze_ingot,
                bronze_alloy,
                obsidian_pickaxehead,
                obsidian_swordblade,
                obsidian_shovelhead,
                obsidian_axehead,
                obsidian_hoehead,
                iron_rod,
                bronze_gear,

                copper_pickaxe,
                tin_pickaxe,
                bronze_pickaxe,
                obsidian_pickaxe,

                copper_sword,
                tin_sword,
                bronze_sword,
                obsidian_sword,

                copper_axe,
                tin_axe,
                bronze_axe,
                obsidian_axe,

                copper_hoe,
                tin_hoe,
                bronze_hoe,
                obsidian_hoe,

                copper_shovel,
                tin_shovel,
                bronze_shovel,
                obsidian_shovel
        );
    }

    public static void registerModels() {
        copper_ingot.registerItemModel();
        tin_ingot.registerItemModel();
        bronze_ingot.registerItemModel();
        bronze_alloy.registerItemModel();
        obsidian_pickaxehead.registerItemModel();
        obsidian_axehead.registerItemModel();
        obsidian_hoehead.registerItemModel();
        obsidian_shovelhead.registerItemModel();
        obsidian_swordblade.registerItemModel();
        iron_rod.registerItemModel();
        bronze_gear.registerItemModel();


        copper_pickaxe.registerItemModel();
        tin_pickaxe.registerItemModel();
        bronze_pickaxe.registerItemModel();
        obsidian_pickaxe.registerItemModel();

        copper_sword.registerItemModel();
        tin_sword.registerItemModel();
        bronze_sword.registerItemModel();
        obsidian_sword.registerItemModel();

        copper_axe.registerItemModel();
        tin_axe.registerItemModel();
        bronze_axe.registerItemModel();
        obsidian_axe.registerItemModel();

        copper_hoe.registerItemModel();
        tin_hoe.registerItemModel();
        bronze_hoe.registerItemModel();
        obsidian_hoe.registerItemModel();

        copper_shovel.registerItemModel();
        tin_shovel.registerItemModel();
        bronze_shovel.registerItemModel();
        obsidian_shovel.registerItemModel();
    }
}
