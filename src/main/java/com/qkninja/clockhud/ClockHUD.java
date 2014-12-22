package com.qkninja.clockhud;

import com.qkninja.clockhud.proxy.IProxy;
import com.qkninja.clockhud.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

/**
 * Created by Sam on 2014-12-20.
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class ClockHUD
{

    @Mod.Instance(Reference.MOD_ID)
    public static ClockHUD instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event)
    {
        proxy.registerRenderers();
    }

}