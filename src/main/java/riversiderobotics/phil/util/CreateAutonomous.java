package riversiderobotics.phil.util;

import com.kauailabs.navx.frc.AHRS;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.FollowPathWithEvents;
import com.pathplanner.lib.commands.PPRamseteCommand;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj2.command.Command;
import riversiderobotics.phil.AutoConstants;
import riversiderobotics.phil.subsystems.DriveSubsystem;

import java.util.HashMap;

public class CreateAutonomous
{
    private final PPRamseteCommand ramsete;

    private final PathPlannerTrajectory traj;
    private final FollowPathWithEvents path;

    private final AHRS gyro;

    private final DifferentialDriveOdometry odometry;

    private final DriveSubsystem drive;

    public CreateAutonomous(String file, int maxVel, int maxAccel, AHRS _gyro, DriveSubsystem _drive, HashMap<String, Command> event_map)
    {
        gyro = _gyro;
        drive = _drive;

        traj = PathPlanner.loadPath(file, maxVel, maxAccel, false);

        drive.resetEncoders();

        odometry = new DifferentialDriveOdometry(gyro.getRotation2d(), 0, 0, traj.getInitialPose());

        ramsete = new PPRamseteCommand(
                traj,
                odometry::getPoseMeters,
                new RamseteController(),
                new SimpleMotorFeedforward(AutoConstants.ksVolts, AutoConstants.kvVoltSecondsPerMeter, AutoConstants.kaVoltSecondsSquaredMeter),
                AutoConstants.kDriveKinematics,
                drive::getWheelSpeeds,
                new PIDController(AutoConstants.kPDriveVel, 0, 0),
                new PIDController(AutoConstants.kPDriveVel, 0, 0),
                drive::feedVolts,
                drive
        );

        path = new FollowPathWithEvents(
                ramsete,
                traj.getMarkers(),
                event_map
        );
    }

    public Command returnCommand()
    {
        return path;
    }
}
