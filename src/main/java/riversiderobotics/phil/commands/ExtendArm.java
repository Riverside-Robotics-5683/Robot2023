package riversiderobotics.phil.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import riversiderobotics.phil.subsystems.ArmSubsystem;

public class ExtendArm extends CommandBase
{
    private double target;
    private double speed;

    private ArmSubsystem arm;
    private boolean reset;

    public ExtendArm(ArmSubsystem subsystem, double _target, double _speed, boolean resetEncoders)
    {
        addRequirements(subsystem);

        arm = subsystem;
        target = _target;
        speed = _speed;
        reset = resetEncoders;
    }

    @Override
    public void initialize()
    {
        if(reset)
        {
            arm.resetEncoders();
        }
        arm.extendToPosition(target, speed);
    }
}
