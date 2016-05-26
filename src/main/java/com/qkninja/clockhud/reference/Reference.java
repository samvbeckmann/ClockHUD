package com.qkninja.clockhud.reference;

/**
 * Contains static final variables for reference throughout the mod.
 */
public class Reference
{
    public static final String MOD_ID = "clockhud";
    public static final String MOD_NAME = "ClockHUD";
    public static final String MOD_VERSION = "@VERSION@";
    public static final String SERVER_PROXY_CLASS = "com.qkninja.clockhud.proxy.ServerProxy";
    public static final String CLIENT_PROXY_CLASS = "com.qkninja.clockhud.proxy.ClientProxy";
    public static final String GUI_FACTORY_CLASS = "com.qkninja.clockhud.client.gui.GuiFactory";

    /*
     * Constants involving time.
     */
    public static final int DAY_TICKS = 24000;
    public static final int NEW_DAY_TICK = 50;
    public static final int NEW_NIGHT_TICK = 13000;
}
