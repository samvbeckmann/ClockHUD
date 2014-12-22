package com.qkninja.clockhud.utility;

import com.qkninja.clockhud.reference.Reference;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Sam on 2014-12-20.
 */
public class ResourceLocationHelper
{
    public static ResourceLocation getResourceLocation(String modId, String path)
    {
        return new ResourceLocation(modId, path);
    }

    public static ResourceLocation getResourceLocation(String path)
    {
        return getResourceLocation(Reference.MOD_ID.toLowerCase(), path);
    }
}
