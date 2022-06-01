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
     */ public static int clamp(int num, int min, int max) {
        return Math.max(Math.min(num, max), min);
    }
    /**
     * Clamp the value between the minimum value and maximum value.
     * @param num The number to clamp
     * @param min The minimum value
     * @param max The maximum value
     * @return The clamped value
     */ public static double clamp(double num, double min, double max) {
        return Math.max(Math.min(num, max), min);
    }
    /**
     * Clamp the value between the minimum value and maximum value.
     * @param num The number to clamp
     * @param min The minimum value
     * @param max The maximum value
     * @return The clamped value
     */ public static float clamp(float num, float min, float max) {
        return Math.max(Math.min(num, max), min);
    }
    /**
     * Clamp the value between the minimum value and maximum value.
     * @param num The number to clamp
     * @param min The minimum value
     * @param max The maximum value
     * @return The clamped value
     */ public static long clamp(long num, long min, long max) {
        return Math.max(Math.min(num, max), min);
    }

    /**
     * Get a random number between two values
     * @param min the minimum value in the random number
     * @param max the maximum value of the random number
     * @return description
     */ public static int random(int min, int max) {
        if(min == max) return min;
        if(max < min) {
            int temp = min;
            min = max;
            max = temp;
        }
        return min + (int) Math.round(Math.random() * (max - min));
    }
    /**
     * Get a random number between two values
     * @param min the minimum value in the random number
     * @param max the maximum value of the random number
     * @return description
     */ public static double random(double min, double max) {
        if(min == max) return min;
        if(max < min) {
            double temp = min;
            min = max;
            max = temp;
        }
        return min + (double) Math.round(Math.random() * (max - min));
    }
    /**
     * Get a random number between two values
     * @param min the minimum value in the random number
     * @param max the maximum value of the random number
     * @return description
     */ public static float random(float min, float max) {
        if(min == max) return min;
        if(max < min) {
            float temp = min;
            min = max;
            max = temp;
        }
        return min + (float) Math.round(Math.random() * (max - min));
    }
    /**
     * Get a random number between two values
     * @param min the minimum value in the random number
     * @param max the maximum value of the random number
     * @return description
     */ public static long random(long min, long max) {
        if(min == max) return min;
        if(max < min) {
            long temp = min;
            min = max;
            max = temp;
        }
        return min + (long) Math.round(Math.random() * (max - min));
    }

    /**
     * Returns a multiplier for negative or positive based on the number
     * @param num The number
     * @return 1 if positive, -1 if negative
     */ public static int posNeg(int num) {
         if (num >= 0) return 1;
         return -1;
    }
    /**
     * Returns a multiplier for negative or positive based on the number
     * @param num The number
     * @return 1 if positive, -1 if negative
     */ public static int posNeg(double num) {
        if (num >= 0) return 1;
        return -1;
    }
    /**
     * Returns a multiplier for negative or positive based on the number
     * @param num The number
     * @return 1 if positive, -1 if negative
     */ public static int posNeg(float num) {
        if (num >= 0) return 1;
        return -1;
    }
    /**
     * Returns a multiplier for negative or positive based on the number
     * @param num The number
     * @return 1 if positive, -1 if negative
     */ public static int posNeg(long num) {
        if (num >= 0) return 1;
        return -1;
    }

    /**
     * Loops the value. Ex. if it is larger than the max it becomes the minimum.
     * @param num The number to be looped
     * @param min The minimum
     * @param max The maximum
     * @return The final value
     */ public static int loop(int num, int min, int max) {
        if (num >= max) return min;
        if (num <= min) return max;
        return num;
    }
    /**
     * Loops the value. Ex. if it is larger than the max it becomes the minimum.
     * @param num The number to be looped
     * @param min The minimum
     * @param max The maximum
     * @return The final value
     */ public static double loop(double num, double min, double max) {
        if (num >= max) return min;
        if (num <= min) return max;
        return num;
    }
    /**
     * Loops the value. Ex. if it is larger than the max it becomes the minimum.
     * @param num The number to be looped
     * @param min The minimum
     * @param max The maximum
     * @return The final value
     */ public static float loop(float num, float min, float max) {
        if (num >= max) return min;
        if (num <= min) return max;
        return num;
    }
    /**
     * Loops the value. Ex. if it is larger than the max it becomes the minimum.
     * @param num The number to be looped
     * @param min The minimum
     * @param max The maximum
     * @return The final value
     */ public static long loop(long num, long min, long max) {
        if (num >= max) return min;
        if (num <= min) return max;
        return num;
    }

    /**
     * Gets the biggest of the numbers provided
     * @param nums An array of numbers
     * @return The largest of the provided numbers
     */ public static int biggest(int... nums) {
         int num = nums[0];
         for (int number : nums) {
             num = Math.max(number, num);
         }
         return num;
    }
    /**
     * Gets the biggest of the numbers provided
     * @param nums An array of numbers
     * @return The largest of the provided numbers
     */ public static double biggest(double... nums) {
        double num = nums[0];
        for (double number : nums) {
            num = Math.max(number, num);
        }
        return num;
    }
    /**
     * Gets the biggest of the numbers provided
     * @param nums An array of numbers
     * @return The largest of the provided numbers
     */ public static float biggest(float... nums) {
        float num = nums[0];
        for (float number : nums) {
            num = Math.max(number, num);
        }
        return num;
    }
    /**
     * Gets the biggest of the numbers provided
     * @param nums An array of numbers
     * @return The largest of the provided numbers
     */ public static long biggest(long... nums) {
        long num = nums[0];
        for (long number : nums) {
            num = Math.max(number, num);
        }
        return num;
    }

    /**
     * Gets the smallest of the numbers provided
     * @param nums An array of numbers
     * @return The largest of the provided numbers
     */ public static int smallest(int... nums) {
        int num = nums[0];
        for (int number : nums) {
            num = Math.min(number, num);
        }
        return num;
    }
    /**
     * Gets the smallest of the numbers provided
     * @param nums An array of numbers
     * @return The largest of the provided numbers
     */ public static double smallest(double... nums) {
        double num = nums[0];
        for (double number : nums) {
            num = Math.min(number, num);
        }
        return num;
    }
    /**
     * Gets the smallest of the numbers provided
     * @param nums An array of numbers
     * @return The largest of the provided numbers
     */ public static float smallest(float... nums) {
        float num = nums[0];
        for (float number : nums) {
            num = Math.min(number, num);
        }
        return num;
    }
    /**
     * Gets the smallest of the numbers provided
     * @param nums An array of numbers
     * @return The largest of the provided numbers
     */ public static long smallest(long... nums) {
        long num = nums[0];
        for (long number : nums) {
            num = Math.min(number, num);
        }
        return num;
    }

    /*
     public static double roundTo(double num, int precision) {

    }
    */

}
