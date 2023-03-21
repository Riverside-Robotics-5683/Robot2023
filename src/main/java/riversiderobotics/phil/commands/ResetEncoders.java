package riversiderobotics.phil.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import riversiderobotics.phil.subsystems.ArmSubsystem;
import riversiderobotics.phil.subsystems.DriveSubsystem;

public class ResetEncoders extends CommandBase
{
    private DriveSubsystem drive;
    private ArmSubsystem arm;

    public ResetEncoders(DriveSubsystem d_subsystem, ArmSubsystem a_subsystem)
    {
        addRequirements(d_subsystem, a_subsystem);

        drive = d_subsystem;
        arm = a_subsystem;
    }

    @Override
    public void initialize()
    {
        drive.resetEncoders();
        arm.resetEncoders();
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }
}
