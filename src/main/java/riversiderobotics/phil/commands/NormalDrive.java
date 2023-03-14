package riversiderobotics.phil.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import riversiderobotics.phil.subsystems.ArmSubsystem;
import riversiderobotics.phil.subsystems.DriveSubsystem;

public class NormalDrive extends CommandBase
{
    private DriveSubsystem drive;
    private ArmSubsystem arm;

    private double forward;
    private double rotation;
    private double arm_speed;

    public NormalDrive(DriveSubsystem drivetrain, ArmSubsystem a_arm, double speed, double rotate, double a_arm_speed)
    {
      drive = drivetrain;
      arm = a_arm;

      forward = speed;
      rotation = rotate;
      arm_speed = a_arm_speed;
    }

    @Override
    public void execute()
    {
      drive.drive(forward, rotation);
      arm.rotateArm(arm_speed);
    }
}