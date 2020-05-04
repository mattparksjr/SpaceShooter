package codes.matthewp.space.util;

public class MathHelp {

    /**
     * Helper function to clamp a number between a minimum and a maximum
     * @param val int Value to clamp
     * @param min int the minimum the value can be
     * @param max int the maximum the value can be
     * @return
     */
    public static int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }
}
