package com.qkninja.clockhud.client.handler;

import com.qkninja.clockhud.client.settings.KeyBindings;
import com.qkninja.clockhud.reference.ConfigValues;
import com.qkninja.clockhud.reference.Key;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KeyInputEventHandler
{

    private static Key getPressedKeyBinding()
    {
        if (KeyBindings.toggle.isPressed())
            return Key.TOGGLE;
        else return Key.UNKNOWN;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        if (getPressedKeyBinding() == Key.TOGGLE)
        {
            if (ConfigValues.guiActive)
                ConfigValues.guiActive = false;
            else
                ConfigValues.guiActive = true;
        }


    }
}
