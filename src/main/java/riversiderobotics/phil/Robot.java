// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package riversiderobotics.phil;

import com.pathplanner.lib.server.PathPlannerServer;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


/**
 * The VM is configured to automatically run this class, and to call the methods corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot
{
    private Command autonomousCommand;

    private Container container;

    private UsbCamera camera;

    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit()
    {
        container = new Container();
        PathPlannerServer.startServer(5683);

        camera = new UsbCamera("Blur Cam 2.0", 0);
        camera.setResolution(640, 480);
        camera.setFPS(30);

        CameraServer.addCamera(camera);
    }
    
    
    /**
     * This method is called every 20 ms, no matter the mode. Use this for items like diagnostics
     * that you want ran during disabled, autonomous, teleoperated and test.
     *     * <p>This runs after the mode specific periodic methods, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic()
    {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }
    
    
    /** This method is called once each time the robot enters Disabled mode. */
    @Override
    public void disabledInit() {}
    
    
    @Override
    public void disabledPeriodic() {}
    

    @Override
    public void autonomousInit()
    {
        autonomousCommand = container.getAutonomous();

        if(autonomousCommand != null)
        {
            autonomousCommand.schedule();
        }
    }
    
    
    /** This method is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {}
    
    
    @Override
    public void teleopInit()
    {
        if (autonomousCommand != null)
        {
          autonomousCommand.cancel();
        }
        
    }
    
    
    /** This method is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {}
    
    
    @Override
    public void testInit()
    {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
    }
    
    
    /** This method is called periodically during test mode. */
    @Override
    public void testPeriodic() {}
    
    
    /** This method is called once when the robot is first started up. */
    @Override
    public void simulationInit() {}
    
    
    /** This method is called periodically whilst in simulation. */
    @Override
    public void simulationPeriodic() {}
}
