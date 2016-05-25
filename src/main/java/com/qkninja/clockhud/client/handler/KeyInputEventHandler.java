package com.qkninja.clockhud.client.handler;

import com.qkninja.clockhud.client.settings.KeyBindings;
import com.qkninja.clockhud.reference.ConfigValues;
import com.qkninja.clockhud.reference.Key;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyInputEventHandler
{

    private static Key getPressedKeyBinding()
    {
        if (KeyBindings.TOGGLE.isPressed())
            return Key.TOGGLE;
        else return Key.UNKNOWN;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        if (getPressedKeyBinding() == Key.TOGGLE)
            ConfigValues.guiActive = !ConfigValues.guiActive;
    }
}
