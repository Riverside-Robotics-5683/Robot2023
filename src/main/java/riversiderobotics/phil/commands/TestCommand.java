package riversiderobotics.phil.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import riversiderobotics.phil.subsystems.TestSubsystem;

public class TestCommand extends CommandBase
{
    private final TestSubsystem subsystem = new TestSubsystem();

    public TestCommand()
    {
        addRequirements(subsystem);
    }

    @Override
    public void initialize()
    {
        subsystem.startLogging();
    }
}
