package mathExt;

public class UMath {

    // clamp functions --->: clamps values between min-max
    public static double clamp(double value, double min, double max) // doubles
    {
        return Math.min(Math.max(value, min),max);
    }
    public static int clamp(int value, int min, int max) // integers
    {
        return Math.min(Math.max(value, min),max);
    }
    public static double clamp(double value, Range range) // doubles using range
    {
        return Math.min(Math.max(value, range.min),range.max);
    }
    public static int clamp(int value, Range range) // integers using range
    {
        return Math.min(Math.max(value, (int)range.min),(int) range.max);
    }
    public static double clamp_m(double value, double min) // doubles only min
    {
        return Math.max(value, min);
    }
    public static int clamp_m(int value, int min) // integers only min
    {
        return Math.max(value, min);
    }
    public static double clamp_M(double value, double max) // doubles only max
    {
        return Math.min(value, max);
    }
    public static int clamp_M(int value, int max) // integers only max
    {
        return Math.min(value, max);
    }

}
