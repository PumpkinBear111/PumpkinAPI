package finn.creates.pumpkin.mathematics;

/**
 * Useful tools for math.
 * @author Finn
 */
public class MathUtils {

    /**
     * Clamp the value between the minimum value and maximum value.
     * @param num The number to clamp
     * @param min The minimum value
     * @param max The maximum value
     * @return The clamped value
     */ public static int Clamp(int num, int min, int max) {
        return Math.max(Math.min(num, max), min);
    }
    /**
     * Clamp the value between the minimum value and maximum value.
     * @param num The number to clamp
     * @param min The minimum value
     * @param max The maximum value
     * @return The clamped value
     */ public static double Clamp(double num, double min, double max) {
        return Math.max(Math.min(num, max), min);
    }
    /**
     * Clamp the value between the minimum value and maximum value.
     * @param num The number to clamp
     * @param min The minimum value
     * @param max The maximum value
     * @return The clamped value
     */ public static float Clamp(float num, float min, float max) {
        return Math.max(Math.min(num, max), min);
    }
    /**
     * Clamp the value between the minimum value and maximum value.
     * @param num The number to clamp
     * @param min The minimum value
     * @param max The maximum value
     * @return The clamped value
     */ public static long Clamp(long num, long min, long max) {
        return Math.max(Math.min(num, max), min);
    }

    /**
     * Get a random number between two values
     * @param min the minimum value in the random number
     * @param max the maximum value of the random number
     * @return description
     */ public static int Random(int min, int max) {
        if(min == max) return min;
        return min + (int) Math.round(Math.random() * (max - min));
    }
    /**
     * Get a random number between two values
     * @param min the minimum value in the random number
     * @param max the maximum value of the random number
     * @return description
     */ public static double Random(double min, double max) {
        if(min == max) return min;
        return min + (double) Math.round(Math.random() * (max - min));
    }
    /**
     * Get a random number between two values
     * @param min the minimum value in the random number
     * @param max the maximum value of the random number
     * @return description
     */ public static float Random(float min, float max) {
        if(min == max) return min;
        return min + (float) Math.round(Math.random() * (max - min));
    }
    /**
     * Get a random number between two values
     * @param min the minimum value in the random number
     * @param max the maximum value of the random number
     * @return description
     */ public static long Random(long min, long max) {
        if(min == max) return min;
        return min + (long) Math.round(Math.random() * (max - min));
    }

    /**
     * Returns a multiplier for negative or positive based on the number
     * @param num The number
     * @return 1 if positive, -1 if negative
     */ public static int PosNeg(int num) {
         if (num >= 0) return 1;
         return -1;
    }
    /**
     * Returns a multiplier for negative or positive based on the number
     * @param num The number
     * @return 1 if positive, -1 if negative
     */ public static int PosNeg(double num) {
        if (num >= 0) return 1;
        return -1;
    }
    /**
     * Returns a multiplier for negative or positive based on the number
     * @param num The number
     * @return 1 if positive, -1 if negative
     */ public static int PosNeg(float num) {
        if (num >= 0) return 1;
        return -1;
    }
    /**
     * Returns a multiplier for negative or positive based on the number
     * @param num The number
     * @return 1 if positive, -1 if negative
     */ public static int PosNeg(long num) {
        if (num >= 0) return 1;
        return -1;
    }

}
