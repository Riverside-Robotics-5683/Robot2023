package riversiderobotics.phil;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

public class AutoConstants
{
    public static final double ksVolts = 0.15685;
    public static final double kvVoltSecondsPerMeter = 1.3335;
    public static final double kaVoltSecondsSquaredMeter = 0.13269;

    public static final double kPDriveVel = 2.3113E-07;
    public static final double kTrackWidthMeters = 0.52705;
    public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(kTrackWidthMeters);

    public static final double kMaxSpeedMetersPerSecond = 4;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;

    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;
}
