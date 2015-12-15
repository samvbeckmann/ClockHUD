package com.qkninja.clockhud.handler;

import com.qkninja.clockhud.reference.ConfigValues;
import com.qkninja.clockhud.reference.Reference;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler
{
    public static Configuration configuration;

    public static void init(File configFile)
    {
        if (configuration == null)
        {
            configuration =new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        ConfigValues.showDayCount = configuration.getBoolean("showDayCount", Configuration.CATEGORY_GENERAL, true, "Display the day count at the beginning of each day");
        ConfigValues.xCoord = configuration.get(Configuration.CATEGORY_GENERAL, "xCoord", 2, "starting x Coordinate of the clock").getInt();
        ConfigValues.yCoord = configuration.get(Configuration.CATEGORY_GENERAL, "yCoord", 2, "starting y Coordinate of the clock").getInt();
        ConfigValues.scale = configuration.getFloat("scale", Configuration.CATEGORY_GENERAL, .7F, 0F, 3F, "scale of the clock");

        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
        {
            loadConfiguration();
        }
    }
}
