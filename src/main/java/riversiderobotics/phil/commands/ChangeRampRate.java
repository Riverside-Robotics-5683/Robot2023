package riversiderobotics.phil.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import riversiderobotics.phil.subsystems.DriveSubsystem;

public class ChangeRampRate extends CommandBase
{
    private DriveSubsystem drive;

    private double change;

    public ChangeRampRate(DriveSubsystem subsystem, double rate)
    {
        addRequirements(subsystem);
        change = rate;
        drive = subsystem;
    }

    @Override
    public void initialize()
    {
        drive.setRampRate(change);
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }
}
