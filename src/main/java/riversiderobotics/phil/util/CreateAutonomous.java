package riversiderobotics.phil.util;

import com.kauailabs.navx.frc.AHRS;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.FollowPathWithEvents;
import com.pathplanner.lib.commands.PPRamseteCommand;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import riversiderobotics.phil.AutoConstants;
import riversiderobotics.phil.subsystems.DriveSubsystem;

import java.util.HashMap;
import java.util.function.Supplier;

public class CreateAutonomous
{
    private PPRamseteCommand ramsete;

    private PathPlannerTrajectory traj;
    private FollowPathWithEvents path;

    private AHRS gyro;

    private DifferentialDriveOdometry odometry;

    private DriveSubsystem drive;

    public CreateAutonomous(String file, int maxVel, int maxAccel, AHRS gyro, DriveSubsystem drive, HashMap<String, Command> event_map)
    {
        this.gyro = gyro;
        this.drive = drive;

        traj = PathPlanner.loadPath(file, maxVel, maxAccel, false);

        odometry = new DifferentialDriveOdometry(gyro.getRotation2d(), 0, 0, traj.getInitialPose());

        ramsete = new PPRamseteCommand(
                traj,
                (Supplier<Pose2d>) odometry,
                new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
                new SimpleMotorFeedforward(AutoConstants.ksVolts, AutoConstants.kvVoltSecondsPerMeter, AutoConstants.kaVoltSecondsSquaredMeter),
                AutoConstants.kDriveKinematics,
                drive::getWheelSpeeds,
                new PIDController(0, 0, 0),
                new PIDController(0, 0, 0),
                drive::feedVolts,
                drive
        );

        path = new FollowPathWithEvents(
                ramsete,
                traj.getMarkers(),
                event_map
        );
    }

    public CommandBase returnCommand()
    {
        return path;
    }
}
