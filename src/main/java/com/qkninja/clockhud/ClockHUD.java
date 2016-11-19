package com.qkninja.clockhud;

import com.qkninja.clockhud.client.handler.KeyInputEventHandler;
import com.qkninja.clockhud.handler.ConfigurationHandler;
import com.qkninja.clockhud.proxy.CommonProxy;
import com.qkninja.clockhud.reference.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * A simple clock GUI to make telling the time easier.
 * @author QKninja
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, acceptedMinecraftVersions="[1.11,1.12)", guiFactory = Reference.GUI_FACTORY_CLASS)
public class ClockHUD
{
    @Mod.Instance(Reference.MOD_ID)
    public static ClockHUD instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());

        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();

        MinecraftForge.EVENT_BUS.register(new KeyInputEventHandler());
    }
}