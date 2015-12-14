package com.qkninja.clockhud.client.gui;

import com.qkninja.clockhud.reference.ConfigValues;
import com.qkninja.clockhud.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

/**
 * Renders the day count on the screen after each new day.
 *
 * @author Sam Beckmann
 */
public class GuiDayCount extends Gui
{
    private Minecraft mc;

    private long endAnimationTime;
    private boolean isRunning;
    private static final int ANIMATION_TIME = 3000; // 3 second animation

    public GuiDayCount(Minecraft mc)
    {
        super();
        isRunning = false;
        this.mc = mc;
    }

    /**
     * Renders the Day Count on the screen.
     *
     * @param event variables associated with the event. Not used in this case.
     */
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRenderExperienceBar(RenderGameOverlayEvent.Post event)
    {

        if (ConfigValues.showDayCount && event.type == RenderGameOverlayEvent.ElementType.EXPERIENCE &&
                (isRunning || isNewDay()))
        {
            long currentTime = Minecraft.getSystemTime();

            if (!isRunning)
            {
                isRunning = true;
                endAnimationTime = currentTime + ANIMATION_TIME;
            }

            float scaleFactor = getScaleFactor((endAnimationTime - currentTime) / (float) ANIMATION_TIME);
            String dayString = formDayString();

            GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, getOpacityFactor((endAnimationTime - currentTime) / (float) ANIMATION_TIME));

            ScaledResolution scaled = new ScaledResolution(mc);

            mc.fontRendererObj.drawString(dayString,
                    (scaled.getScaledWidth() / 2 -
                            mc.fontRendererObj.getStringWidth(dayString) * scaleFactor / 2) / scaleFactor,
                    scaled.getScaledHeight() / 7 / scaleFactor,
                    0xffffff, false);

            GL11.glScalef(1 / scaleFactor, 1 / scaleFactor, 1 / scaleFactor); // set scale to previous value
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);


            if (currentTime >= endAnimationTime)
                isRunning = false;
        }
    }

    /**
     * Tests if it's a new day.
     *
     * @return if the dayTime is the specified time of a new day.
     */
    private boolean isNewDay()
    {
        return Minecraft.getMinecraft().theWorld.getWorldTime() % Reference.DAY_TICKS == Reference.NEW_DAY_TICK;
    }

    /**
     * Creates the day string based on total world time
     *
     * @return String of "Day " + day number
     */
    private String formDayString()
    {
        return "Day " + Minecraft.getMinecraft().theWorld.getTotalWorldTime() / Reference.DAY_TICKS;
    }

    /**
     * Gets the factor that the text should be scaled by.
     * Ensures even scaling throughout the time of the animation.
     *
     * @param percentRemaining scaled value between 0-1 indicating percent of the animation remaining.
     * @return Value evenly scaled between 2 and 2.5 based on input.
     */
    private float getScaleFactor(float percentRemaining)
    {
        return 2.5F - percentRemaining / 2;
    }

    /**
     * Gets the opacity at which the text should be displayed.
     * Handles fade in/out of text.
     *
     * @param percentRemaining scaled value between 0-1 indicating percent of the animation remaining.
     * @return value between 0 and 1. 1 --> Fully visible
     */
    private float getOpacityFactor(float percentRemaining)
    {
        if (percentRemaining > .8)
            return (1 - percentRemaining) * 4;
        else if (percentRemaining < .2)
            return percentRemaining * 4;
        else
            return 0.8F;
    }
}
