package com.qkninja.clockhud.handler;

import com.qkninja.clockhud.reference.ConfigValues;
import com.qkninja.clockhud.reference.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

/**
 * Sets up the config file for ClockHUD.
 */
public class ConfigurationHandler
{
    public static Configuration configuration;

    public static void init(File configFile)
    {
        if (configuration == null)
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        ConfigValues.showDayCount = configuration.getBoolean("showDayCount", Configuration.CATEGORY_GENERAL, true, "Display the day count at the beginning of each day");
        ConfigValues.centerClock = configuration.getBoolean("centeredClock", Configuration.CATEGORY_GENERAL, false, "If true, ignore xCoord and always lock the clock the center of the screen");
        ConfigValues.xCoord = configuration.get(Configuration.CATEGORY_GENERAL, "xCoord", 2, "Starting x Coordinate of the clock").getInt();
        ConfigValues.yCoord = configuration.get(Configuration.CATEGORY_GENERAL, "yCoord", 2, "Starting y Coordinate of the clock").getInt();
        ConfigValues.scale = configuration.getFloat("scale", Configuration.CATEGORY_GENERAL, .7F, 0F, 3F, "Scale of the clock");

        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equalsIgnoreCase(Reference.MOD_ID))
        {
            loadConfiguration();
        }
    }
}
