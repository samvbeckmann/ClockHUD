package com.qkninja.clockhud.reference;

import com.qkninja.clockhud.utility.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

/**
 * Stores locations of textures for ClockHUD.
 */
public class Textures
{
    public static final String RESOURCE_PREFIX = Reference.MOD_ID.toLowerCase() + ":";

    public static final class Gui
    {
        private static final String GUI_SHEET_LOCATION = "textures/gui/";
        public static final ResourceLocation HUD = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "gui_clock.png");
    }
}
