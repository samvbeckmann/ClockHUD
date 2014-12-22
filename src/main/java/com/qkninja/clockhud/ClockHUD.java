package com.qkninja.clockhud;

import com.qkninja.clockhud.client.handler.KeyInputEventHandler;
import com.qkninja.clockhud.proxy.IProxy;
import com.qkninja.clockhud.reference.Reference;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * A simple clock GUI to make telling the time easier.
 * @author QKninja
 * @version 1.7.10-1.0
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class ClockHUD
{

    @Mod.Instance(Reference.MOD_ID)
    public static ClockHUD instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.registerKeyBindings();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.registerRenderers();

        FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());
    }

}