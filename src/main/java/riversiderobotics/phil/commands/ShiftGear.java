package riversiderobotics.phil.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import riversiderobotics.phil.subsystems.DriveSubsystem;

public class ShiftGear extends CommandBase
{
    public enum SHIFTING_GEARS
    {
        HIGH_SPEED,
        HIGH_TORQUE
    }

    private DriveSubsystem drive;
    private SHIFTING_GEARS value;

    public ShiftGear(DriveSubsystem subsystem, SHIFTING_GEARS gear)
    {
        drive = subsystem;
        value = gear;
        addRequirements(drive);
    }

    @Override
    public void initialize()
    {
        if (value == SHIFTING_GEARS.HIGH_TORQUE)
        {
            drive.gearShift(DoubleSolenoid.Value.kForward);
        }
        else if (value == SHIFTING_GEARS.HIGH_SPEED)
        {
            drive.gearShift(DoubleSolenoid.Value.kReverse);
        }
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }
}
