package com.qkninja.clockhud.proxy;

import com.qkninja.clockhud.client.gui.GuiClock;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by Sam on 2014-12-20.
 */
public class ClientProxy extends CommonProxy
{

    private Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void registerRenderers()
    {
        MinecraftForge.EVENT_BUS.register(new GuiClock(mc));
    }
}
