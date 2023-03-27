package riversiderobotics.phil.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import riversiderobotics.phil.subsystems.DriveSubsystem;

public class ShiftGear extends CommandBase
{
    private DriveSubsystem drive;
    private DoubleSolenoid.Value value;

    public ShiftGear(DriveSubsystem subsystem, DoubleSolenoid.Value position)
    {
        addRequirements(subsystem);
        drive = subsystem;
        value = position;
    }

    @Override
    public void initialize()
    {
        drive.gearShift(value);
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }
}
