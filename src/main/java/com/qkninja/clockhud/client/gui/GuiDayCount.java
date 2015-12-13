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

    public GuiDayCount(Minecraft mc)
    {
        super();
        isRunning = false;
        this.mc = mc;
    }

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
                endAnimationTime = currentTime + 3000; // 3 second animation
            }

            float scaleFactor = 2; /* getScaleFactor((endAnimationTime - currentTime) / 3000F); */
            String dayString = formDayString();

            GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);

            GL11.glColor4f(1.0F, 1.0F, 1.0F, getOpacityFactor((endAnimationTime - currentTime) / 3000F));


            ScaledResolution scaled = new ScaledResolution(mc);

            mc.fontRendererObj.drawString(dayString,
                    (int) Math.floor((scaled.getScaledWidth() / 2 -
                            mc.fontRendererObj.getStringWidth(dayString) * scaleFactor / 2) / scaleFactor),
                    (int) Math.floor(25 / scaleFactor),
                    0xffffff);

            GL11.glScalef(1 / scaleFactor, 1 / scaleFactor, 1 / scaleFactor);

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);


            if (currentTime >= endAnimationTime)
                isRunning = false;
        }
    }

    private boolean isNewDay()
    {
        return Minecraft.getMinecraft().theWorld.getWorldTime() % Reference.DAY_TICKS == Reference.NEW_DAY_TICK;
    }

    private String formDayString()
    {
        return "Day " + Minecraft.getMinecraft().theWorld.getTotalWorldTime() / Reference.DAY_TICKS;
    }

    private float getScaleFactor(float percentRemaining)
    {
        return 3 - percentRemaining;
    }

    private float getOpacityFactor(float percentRemaining)
    {
        if (percentRemaining > .8)
            return (1 - percentRemaining) * 5;
        else if (percentRemaining < .2)
            return percentRemaining * 5;
        else
            return 1;
    }
}
