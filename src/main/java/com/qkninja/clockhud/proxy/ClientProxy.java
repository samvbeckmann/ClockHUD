package com.qkninja.clockhud.proxy;

import com.qkninja.clockhud.client.gui.GuiClock;
import com.qkninja.clockhud.client.settings.KeyBindings;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{

    private Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void registerRenderers()
    {
        MinecraftForge.EVENT_BUS.register(new GuiClock(mc));
    }

    public void registerKeyBindings()
    {
        ClientRegistry.registerKeyBinding(KeyBindings.toggle);
    }
}
