package com.qkninja.clockhud.client.gui;

import com.qkninja.clockhud.reference.ConfigValues;
import com.qkninja.clockhud.reference.Reference;
import com.qkninja.clockhud.reference.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

/**
 * Creates the Clock Gui.
 * TODO: Clean up this class to better code standards.
 */
public class GuiClock extends Gui
{
    private Minecraft mc;

    public GuiClock(Minecraft mc)
    {
        super();

        this.mc = mc;
    }

    private static final int SUN_WIDTH = 24; // 48
    private static final int MOON_WIDTH = 16; // 31
    private static final int ICON_HEIGHT = 25; // 49
    private static final int BAR_LENGTH = 200; // 400
    private static final int BAR_HEIGHT = 5; // 10
    private static final int DOT = 5; // 10

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRenderExperienceBar(RenderGameOverlayEvent.Post event)
    {

        if (!ConfigValues.guiActive || event.isCancelable() ||
                event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE)
        {
            return;
        }

        this.mc.getTextureManager().bindTexture(Textures.Gui.HUD);

        GL11.glScalef(ConfigValues.scale, ConfigValues.scale, ConfigValues.scale);

        this.drawTexturedModalRect(ConfigValues.xCoord + SUN_WIDTH / 2 - (DOT / 2),
                ConfigValues.yCoord + ICON_HEIGHT / 2 - BAR_HEIGHT / 2, 0, 0, BAR_LENGTH, BAR_HEIGHT);
        if (isDay())
            this.drawTexturedModalRect(ConfigValues.xCoord + getScaledTime(), ConfigValues.yCoord, 0, BAR_HEIGHT,
                    SUN_WIDTH, ICON_HEIGHT);
        else
            this.drawTexturedModalRect(ConfigValues.xCoord + (SUN_WIDTH - MOON_WIDTH) / 2 + getScaledTime(),
                    ConfigValues.yCoord, SUN_WIDTH, BAR_HEIGHT, MOON_WIDTH, ICON_HEIGHT);

        GL11.glScalef(1 / ConfigValues.scale, 1 / ConfigValues.scale, 1 / ConfigValues.scale);
    }

    private int getScaledTime()
    {
        World world = Minecraft.getMinecraft().theWorld;
        long time = world.getWorldInfo().getWorldTime();
        int currentTime = (int) time % Reference.DAY_TICKS;

        if (currentTime >= 0 && currentTime <= Reference.NEW_NIGHT_TICK)
        {
            return currentTime * (BAR_LENGTH - DOT) / Reference.NEW_NIGHT_TICK;
        } else
        {
            return (currentTime - Reference.NEW_NIGHT_TICK) * (BAR_LENGTH - DOT) / (Reference.DAY_TICKS - Reference.NEW_NIGHT_TICK);
        }
    }

    private boolean isDay()
    {
        World world = Minecraft.getMinecraft().theWorld;
        long time = world.getWorldInfo().getWorldTime();
        int currentTime = (int) time % Reference.DAY_TICKS;

        return (currentTime >= 0 && currentTime <= Reference.NEW_NIGHT_TICK);
    }
}
