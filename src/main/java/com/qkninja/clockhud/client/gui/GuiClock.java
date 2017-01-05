package com.qkninja.clockhud.client.gui;

import com.qkninja.clockhud.reference.ConfigValues;
import com.qkninja.clockhud.reference.Reference;
import com.qkninja.clockhud.reference.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Creates the Clock Gui.
 */
public class GuiClock extends Gui
{
    private static final int TEXTURE_SCALE = 2;

    private static final int SUN_WIDTH = 48 / TEXTURE_SCALE;
    private static final int MOON_WIDTH = 32 / TEXTURE_SCALE;
    private static final int ICON_HEIGHT = 50 / TEXTURE_SCALE;
    private static final int BAR_LENGTH = 400 / TEXTURE_SCALE;
    private static final int BAR_HEIGHT = 10 / TEXTURE_SCALE;
    private static final int DOT = 10 / TEXTURE_SCALE;

    private Minecraft mc;

    public GuiClock(Minecraft mc)
    {
        super();
        this.mc = mc;
    }

    /**
     * Renders the clock GUI on the screen.
     *
     * @param event Variables associated with the event.
     */
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRenderExperienceBar(RenderGameOverlayEvent.Post event)
    {
        if (!ConfigValues.guiActive || event.isCancelable() || event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE)
            return;

        this.mc.getTextureManager().bindTexture(Textures.Gui.HUD);

        GlStateManager.scale(ConfigValues.scale, ConfigValues.scale, ConfigValues.scale);

        int xCoord;
        if (ConfigValues.centerClock)
        {
            ScaledResolution scaled = new ScaledResolution(mc);
            xCoord = (int) ((scaled.getScaledWidth() - (BAR_LENGTH + SUN_WIDTH - DOT) * ConfigValues.scale) / (2 * ConfigValues.scale));
        } else
        {
            xCoord = ConfigValues.xCoord;
        }

        int startX = xCoord + SUN_WIDTH / 2 - (DOT / 2);
        int startY = ConfigValues.yCoord + ICON_HEIGHT / 2 - BAR_HEIGHT / 2;

        // Draw bar
        this.drawTexturedModalRect(startX, startY, 0, 0, BAR_LENGTH, BAR_HEIGHT);

        if (isDay()) // Draw sun
        {
            this.drawTexturedModalRect(xCoord + getScaledTime(), ConfigValues.yCoord, 0, BAR_HEIGHT,
                    SUN_WIDTH, ICON_HEIGHT);
        }
        else // Draw moon
        {
            this.drawTexturedModalRect(xCoord + (SUN_WIDTH - MOON_WIDTH) / 2 + getScaledTime(),
                    ConfigValues.yCoord, SUN_WIDTH, BAR_HEIGHT, MOON_WIDTH, ICON_HEIGHT);
        }

        GlStateManager.scale(1 / ConfigValues.scale, 1 / ConfigValues.scale, 1 / ConfigValues.scale);
    }

    /**
     * Scales the current time to the length of the bar.
     *
     * @return Integer offset to be used when rendering the sun or moon.
     */
    private int getScaledTime()
    {
        int currentTime = getCurrentTime();

        if (isDay(currentTime))
        {
            return currentTime * (BAR_LENGTH - DOT) / Reference.NEW_NIGHT_TICK;
        } else
        {
            return (currentTime - Reference.NEW_NIGHT_TICK) * (BAR_LENGTH - DOT) / (Reference.DAY_TICKS - Reference.NEW_NIGHT_TICK);
        }
    }

    /**
     * Determines if the world is currently in daytime.
     *
     * @return True if it is day, else false.
     */
    private boolean isDay()
    {
        return isDay(getCurrentTime());
    }

    /**
     * Determines if the world is currently in daytime.
     *
     * @param currentTime current tick of the day.
     * @return Ture if it is day, else false.
     */
    private boolean isDay(int currentTime)
    {
        return (currentTime >= 0 && currentTime <= Reference.NEW_NIGHT_TICK);
    }

    /**
     * Gets the current time of day.
     *
     * @return Current tick of the day.
     */
    private int getCurrentTime()
    {
        World world = Minecraft.getMinecraft().world;
        long time = world.getWorldInfo().getWorldTime();
        return  (int) time % Reference.DAY_TICKS;
    }
}