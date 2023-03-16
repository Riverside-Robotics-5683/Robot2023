package riversiderobotics.phil.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import riversiderobotics.phil.subsystems.ArmSubsystem;

public class IntakeManage extends CommandBase
{
    private ArmSubsystem arm;
    private DoubleSolenoid.Value pos;

    public IntakeManage(ArmSubsystem subsystem, DoubleSolenoid.Value _pos)
    {
        arm = subsystem;
        pos = _pos;
        addRequirements(subsystem);
    }

    @Override
    public void initialize()
    {
        arm.setIntake(pos);
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }
}
