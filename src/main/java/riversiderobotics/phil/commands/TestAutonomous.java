package riversiderobotics.phil.commands;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPRamseteCommand;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TestAutonomous extends CommandBase
{
    private final PathPlannerTrajectory trajectory = PathPlanner.loadPath("TestAutonomous", 4, 3);
}
