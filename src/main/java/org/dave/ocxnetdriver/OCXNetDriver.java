package org.dave.ocxnetdriver;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.dave.ocxnetdriver.config.ConfigurationHandler;
import org.dave.ocxnetdriver.converter.ConverterBlockPos;
import org.dave.ocxnetdriver.driver.controller.DriverXnetController;

@Mod(
        modid = OCXNetDriver.MODID,
        version = OCXNetDriver.VERSION,
        guiFactory = OCXNetDriver.GUI_FACTORY,
        dependencies = "required-after:OpenComputers@[1.7,);required-after:xnet@[1.5,)",
        acceptedMinecraftVersions = "1.10.2"
)
public class OCXNetDriver {
    public static final String MODID = "ocxnetdriver";
    public static final String VERSION = "1.0";
    public static final String GUI_FACTORY = "org.dave.ocxnetdriver.config.GuiFactory";

    @Mod.Instance
    public static OCXNetDriver instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Logz.setLogger(event.getModLog());

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        li.cil.oc.api.Driver.add(new DriverXnetController());
        li.cil.oc.api.Driver.add(new ConverterBlockPos());
    }
}
