package nl.axel.extratools;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nl.axel.extratools.init.ModBlocks;
import nl.axel.extratools.handler.ModWorldGeneration;
import nl.axel.extratools.init.ModItems;
import nl.axel.extratools.init.ModRecipes;
import nl.axel.extratools.proxy.CommonProxy;
import nl.axel.extratools.util.ExtraToolsTab;
import nl.axel.extratools.util.Reference;
import net.minecraft.item.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_ID, version = Reference.VERSION)
public class ExtraTools {
    @Mod.Instance(Reference.MOD_ID)
    public static ExtraTools instance;

    @SidedProxy(clientSide = Reference.CLASS_PROXY_CLIENT, serverSide = Reference.CLASS_PROXY_SERVER)
    public static CommonProxy proxy;

    public static final CreativeTabs creativeTab = new ExtraToolsTab();

    public static final Logger logger = LogManager.getLogger(Reference.MOD_ID);

    public static final Item.ToolMaterial copperToolMaterial = EnumHelper.addToolMaterial("COPPER", 2, 200, 5.0F, 1.5F, 18);
    public static final Item.ToolMaterial tinToolMaterial = EnumHelper.addToolMaterial("TIN", 2, 280, 6.0F, 2.0F, 14);
    public static final Item.ToolMaterial bronzeToolMaterial = EnumHelper.addToolMaterial("BRONZE",3, 320,8.0f, 4f, 18 );


    @Mod.EventBusSubscriber
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            ModBlocks.register(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            ModItems.register(event.getRegistry());
            ModBlocks.registerItemBlocks(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {
            ModItems.registerModels();
            ModBlocks.registerModels();
        }


    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger.error("GOING TO GENERATE ORES");
        GameRegistry.registerWorldGenerator(new ModWorldGeneration(), 3);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModRecipes.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }



}
