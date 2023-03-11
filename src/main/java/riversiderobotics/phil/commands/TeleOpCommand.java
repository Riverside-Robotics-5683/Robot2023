package riversiderobotics.phil.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import riversiderobotics.phil.subsystems.TeleOpSubsystem;

public class TeleOpCommand extends CommandBase
{
    private TeleOpSubsystem subsystem = new TeleOpSubsystem();

    public TeleOpCommand()
    {
        addRequirements(subsystem);
    }

    @Override
    public void initialize()
    {
        subsystem.init();
    }

   @Override
   public void execute()
   {
       subsystem.periodic();
   }

   public void end()
   {
       subsystem.disable();
   }
}
