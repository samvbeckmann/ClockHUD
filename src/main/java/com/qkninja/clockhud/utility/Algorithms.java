package com.qkninja.clockhud.utility;

/**
 * Contains useful algorithms for calculations in the mod.
 */
public class Algorithms
{
    /**
     * Scales a value from one range to another.
     *
     * @param valueIn value to be scaled.
     * @param baseMin Minimum of base range
     * @param baseMax Maximum of base range
     * @param limitMin Minimum of new range
     * @param limitMax Maximum of new range
     * @return value scaled from [baseMin, baseMax] to [limitMin, limitMax]
     */
    public static double scale(final double valueIn, final double baseMin, final double baseMax, final double limitMin, final double limitMax)
    {
        return ((limitMax - limitMin) * (valueIn - baseMin) / (baseMax - baseMin)) + limitMin;
    }
}
