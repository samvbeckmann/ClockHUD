package com.qkninja.clockhud.client.settings;

import com.qkninja.clockhud.reference.Names;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyBindings
{
    public static final KeyBinding TOGGLE = new KeyBinding(Names.Keys.TOGGLE, Keyboard.KEY_COMMA, Names.Keys.CATEGORY);
}
