package riversiderobotics.phil.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import riversiderobotics.phil.subsystems.DriveSubsystem;

public class GearShift extends CommandBase 
{
  private DriveSubsystem base;

  private Value value;

  public GearShift(DriveSubsystem subsystem, Value position) 
  {
    base = subsystem;
    value = position;
  }

  @Override
  public void initialize()
  {
    base.gearShift(value);
  }

  @Override
  public boolean isFinished()
  {
    return true;
  }
}