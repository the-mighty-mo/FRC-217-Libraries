package org.team217;

/**
 * Converts between encoder ticks, angles, and distances.
 * 
 * @author ThunderChickens
 */
public class Converter {

    /**
     * Converts radians to encoder ticks
     * 
     * @param rad
     *        Angle in radians
     * @param encPerRot
     *        Number of encoder ticks per rotation
     * @return
     *        Number of encoder ticks to complete the rotation
     */
    public static double radToEnc(double rad, int encPerRot) {
        return rad / (2 * Math.PI) * (double) encPerRot; // Take angle in radians, convert to revolutions, convert to encoder ticks
    }

    /**
     * Converts encoder ticks to radians
     * 
     * @param enc
     *        Encoder ticks
     * @param encPerRot
     *        Number of encoder ticks per rotation
     * @return
     *        Angle in radians
     */
    public static double encToRad(double enc, int encPerRot) {
        return enc / (double) encPerRot * (2 * Math.PI); // Take encoder ticks, convert to revolutions, convert to radians
    }

    /**
     * Converts degrees to encoder ticks
     * 
     * @param deg
     *        Angle in degrees
     * @param encPerRot
     *        Number of encoder ticks per rotation
     * @return
     *        Number of encoder ticks to complete the rotation
     */
    public static double degToEnc(double deg, int encPerRot) {
        return deg / 360 * (double) encPerRot; // Take angle in degrees, convert to revolutions, convert to encoder ticks
    }

    /**
     * Converts encoder ticks to degrees
     * 
     * @param enc
     *        Encoder ticks
     * @param encPerRot
     *        Number of encoder ticks per rotation
     * @return
     *        Angle in degrees
     */
    public static double encToDeg(double enc, int encPerRot) {
        return enc / (double) encPerRot * 360; // Take encoder ticks, convert to revolutions, convert to degrees
    }
    
    /**
     * Converts inches to encoder ticks.
     * 
     * @param inches
     *        Inches to convert
     * @param diameter
     *        Size of diameter in inches
     * @param ticksPerRev
     *        Number of encoder ticks per one revolution
     * @return
     *        Number of encoder ticks to move the distance
     */
    public static double inchToEnc(double inches, double diameter, double ticksPerRev) {
        return inches / (Math.PI * diameter) * ticksPerRev; // Take inches, convert to revolutions, convert to encoder ticks
    }
    
    /**
     * Converts encoder ticks to inches.
     * 
     * @param encTicks
     *        Encoder Ticks to convert
     * @param diameter
     *        Size of diameter in inches
     * @param ticksPerRev
     *        Number of encoder ticks per one revolution
     * @return
     *        Distance in inches
     */
    public static double encToInch(double encTicks, double diameter, double ticksPerRev) {
        return encTicks / ticksPerRev * Math.PI * diameter; // Take encoder ticks, convert to revolutions, convert to inches
    }
    
    /**
     * Modifies an angle to be within the range [-1/2 revolution, 1/2 revolution].
     * 
     * @param angle
     *        Angle to modify
     * @param unitsPerRev
     *        Number of units per one revolution
     * @return
     *        Modified angle
     * 
     * @exception IllegalArgumentException if {@code unitsPerRev} is not positive
     */
    public static double partialAngle(double angle, double unitsPerRev) {
        if (unitsPerRev <= 0) {
            throw new IllegalArgumentException("Illegal unitsPerRev value: " + unitsPerRev + "\nValue must be positive");
        }

        double halfRev = unitsPerRev / 2.0;
        while (!Num.isWithinRange(angle, halfRev)) {
            if (angle > halfRev) {
                angle -= unitsPerRev;
            }
            else if (angle < -halfRev) {
                angle += unitsPerRev;
            }
        }

        return angle;
    }
}