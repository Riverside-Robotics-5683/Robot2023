package riversiderobotics.twentythree.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import riversiderobotics.twentythree.subsystems.TeleOpSubsystem;

public class TeleOpCommand extends CommandBase
{
    private TeleOpSubsystem subsystem = new TeleOpSubsystem();

    public TeleOpCommand()
    {
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {}

   @Override
   public void execute()
   {
       subsystem.periodic();
   }
}
