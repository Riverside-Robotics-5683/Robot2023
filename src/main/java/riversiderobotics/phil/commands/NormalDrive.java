package riversiderobotics.phil.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import riversiderobotics.phil.subsystems.ArmSubsystem;
import riversiderobotics.phil.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class NormalDrive extends CommandBase
{
    private DriveSubsystem drive;
    private ArmSubsystem arm;

    private DoubleSupplier forward;
    private DoubleSupplier rotation;
    private DoubleSupplier arm_speed;
    private DoubleSupplier extend_speed;

    public NormalDrive(DriveSubsystem drivetrain, ArmSubsystem _arm, DoubleSupplier speed, DoubleSupplier rotate, DoubleSupplier _arm_speed, DoubleSupplier _extend_speed)
    {
      addRequirements(drivetrain, _arm);
      drive = drivetrain;
      arm = _arm;

      forward = speed;
      rotation = rotate;
      arm_speed = _arm_speed;
      extend_speed = _extend_speed;
    }

    @Override
    public void initialize()
    {
        drive.gearShift(DoubleSolenoid.Value.kForward);
    }


    @Override
    public void execute()
    {
      drive.drive(forward.getAsDouble(), rotation.getAsDouble());
      arm.rotateArm(arm_speed.getAsDouble());
      arm.extendArm(extend_speed.getAsDouble());
    }
}