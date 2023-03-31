package riversiderobotics.phil.commands;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import riversiderobotics.phil.subsystems.DriveSubsystem;

public class AutoBalance extends CommandBase
{
    private AHRS gyro;
    private DriveSubsystem drive;

    private double dockingAngle = 13;
    private double slowAngle = 6;
    private double engagedAngle = 0;

    private double debounceTime = 0.2;

    private double fastSpeed = 0.25;
    private double mediumSpeed = 0.125;
    private double slowSpeed = 0.0625;

    private double currentSpeed;

    private boolean isDocked = false;
    private int state = 0;

    public AutoBalance(AHRS gyroscope, DriveSubsystem subsystem)
    {
        addRequirements(subsystem);
        gyro = gyroscope;
        drive = subsystem;
    }

    public AutoBalance(AHRS gyroscope, DriveSubsystem subsystem, double _fastSpeed, double _mediumSpeed, double _slowSpeed)
    {
        addRequirements(subsystem);
        gyro = gyroscope;
        drive = subsystem;

        fastSpeed = _fastSpeed;
        mediumSpeed = _mediumSpeed;
        slowSpeed = _slowSpeed;
    }

    public AutoBalance(AHRS gyroscope, DriveSubsystem subsystem, double _dockingAngle, double _slowAngle, double _engagedAngle, double _fastSpeed, double _mediumSpeed, double _slowSpeed, double _debounceTime)
    {
        addRequirements(subsystem);
        gyro = gyroscope;
        drive = subsystem;

        dockingAngle = _dockingAngle;
        slowAngle = _slowAngle;
        engagedAngle = _engagedAngle;

        fastSpeed = _fastSpeed;
        mediumSpeed = _mediumSpeed;
        slowSpeed = _slowSpeed;

        debounceTime = _debounceTime;
    }

    private double getPitch()
    {
        return gyro.getPitch();
    }

    private void autoBalanceUpdate()
    {
        switch (state)
        {
            case 0:
                if (getPitch() <= dockingAngle)
                {
                    currentSpeed = fastSpeed;
                }
                else
                {
                    state = 1;
                }
                break;
            case 1:
                if (getPitch() >= slowAngle)
                {
                    currentSpeed = mediumSpeed;
                }
                else
                {
                    state = 2;
                }
                break;
            case 2:
                if (getPitch() >= engagedAngle)
                {
                    currentSpeed = slowSpeed;
                }
                else
                {
                    currentSpeed = 0;
                    isDocked = true;
                }
                break;
        }
    }

    @Override
    public void initialize()
    {
        drive.gearShift(DoubleSolenoid.Value.kForward);
    }

    @Override
    public void execute()
    {
        autoBalanceUpdate();
        drive.setPower(currentSpeed, currentSpeed);
    }

    @Override
    public boolean isFinished()
    {
        return isDocked;
    }
}
