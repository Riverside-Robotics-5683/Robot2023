package riversiderobotics.phil.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import riversiderobotics.phil.Constants;

import java.util.*;

public class DriveSubsystem extends SubsystemBase
{
    //Motors
    private final CANSparkMax motor_lb = new CANSparkMax(Constants.MOTOR_LB, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_lf = new CANSparkMax(Constants.MOTOR_LF, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_lt = new CANSparkMax(Constants.MOTOR_LT, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_rb = new CANSparkMax(Constants.MOTOR_RB, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_rf = new CANSparkMax(Constants.MOTOR_RF, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_rt = new CANSparkMax(Constants.MOTOR_RT, CANSparkMaxLowLevel.MotorType.kBrushless);

    //Pneumatics
    private final DoubleSolenoid left_gearbox = new DoubleSolenoid(Constants.PNEUMATICS_HUB, PneumaticsModuleType.REVPH, Constants.GEARBOX_LEFT_FORWARD, Constants.GEARBOX_LEFT_REVERSE);
    private final DoubleSolenoid right_gearbox = new DoubleSolenoid(Constants.PNEUMATICS_HUB, PneumaticsModuleType.REVPH, Constants.GEARBOX_RIGHT_FORWARD, Constants.GEARBOX_RIGHT_REVERSE);

    //Drivetrain
    private final MotorControllerGroup left_motor_group = new MotorControllerGroup(motor_lb, motor_lf, motor_lt);
    private final MotorControllerGroup right_motor_group = new MotorControllerGroup(motor_rb, motor_rf, motor_rt);

    private final DifferentialDrive drivetrain = new DifferentialDrive(left_motor_group, right_motor_group);

    public DriveSubsystem()
    {
      motor_lb.setIdleMode(IdleMode.kCoast);
      motor_lf.setIdleMode(IdleMode.kCoast);
      motor_lt.setIdleMode(IdleMode.kCoast);
      motor_rb.setIdleMode(IdleMode.kCoast);
      motor_rf.setIdleMode(IdleMode.kCoast);
      motor_rt.setIdleMode(IdleMode.kCoast);

      motor_lb.setInverted(true);
      motor_lf.setInverted(true);
      motor_lt.setInverted(true);
    }

    public void drive(double forward, double rotation)
    {
      drivetrain.arcadeDrive(forward, rotation);
    }

    public void gearShift(DoubleSolenoid.Value value)
    {
      if (value == Value.kForward)
      {
        left_gearbox.set(value);
        right_gearbox.set(Value.kReverse);
      }
      else
      {
        left_gearbox.set(value);
        right_gearbox.set(Value.kForward);
      }
    }
}
