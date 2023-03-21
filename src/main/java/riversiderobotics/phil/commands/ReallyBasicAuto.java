package riversiderobotics.phil.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.*;
import riversiderobotics.phil.subsystems.DriveSubsystem;

public class ReallyBasicAuto extends CommandBase
{
    private DriveSubsystem drive;

    public ReallyBasicAuto(DriveSubsystem subsystem)
    {
        addRequirements(subsystem);

        drive = subsystem;
    }

    @Override
    public void initialize()
    {
        drive.gearShift(DoubleSolenoid.Value.kForward);
    }
    @Override
    public void execute()
    {
        drive.setPower(0.125, 0.125);
    }
}
