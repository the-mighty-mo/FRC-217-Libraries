package org.team217.rev;

/**
 * A class that manages the {@code CANSparkMax} motor controller for FRC.
 * 
 * @author ThunderChickens 217, RevRobotics
 */
public class CANSparkMax extends com.revrobotics.CANSparkMax {
    protected double zeroPosition = 0;
    protected int invertEnc = 1;
    
    /**
     * Constructor for creating a {@code CANSparkMax} motor controller for FRC.
     * 
     * @param deviceNumber
     *        The Device ID of the motor controller
     * @param type
     *        The type of motor
     * 
     * @author ThunderChickens 217, RevRobotics
     */
    public CANSparkMax(int deviceNumber, MotorType type) {
        super(deviceNumber, type);
    }

    /**
     * Creates a brushless {@code CANSparkMax} motor controller for FRC.
     * 
     * @param deviceNumber
     *        The Device ID of the motor controller
     * 
     * @author ThunderChickens 217, RevRobotics
     */
    public CANSparkMax(int deviceNumber) {
        super(deviceNumber, MotorType.kBrushless);
    }
    
    /**
     * Inverts the direction of the encoders.
     * 
     * @param isInverted
     *        {@code true} if the encoder value should be multiplied by -1
     */
    public void invertEncoder(boolean isInverted) {
        invertEnc = isInverted ? -1 : 1;
    }
    
    /** Gets the encoder velocity, modified so {@code invertEncoder()}, {@code setEncoder()}, and {@code resetEncoder()} affect the return value. */
    public double getVelocity() {
        return invertEnc * getEncoder().getVelocity();
    }

    /** Gets the encoder position, modified so {@code invertEncoder()}, {@code setEncoder()}, and {@code resetEncoder()} affect the return value. */
    public double getPosition() {
        return invertEnc * (getEncoder().getPosition() - zeroPosition);
    }

    /** Sets the encoder value to the given position. This does not save to the motor controller and must be called each time code is deployed. */
    public void setEncoder(double position) {
        zeroPosition = getEncoder().getPosition() - invertEnc * position;
    }

    /** Resets the encoder value to 0. This does not save to the motor controller and must be called each time code is deployed. */
    public void resetEncoder() {
        setEncoder(0);
    }
}