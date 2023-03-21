package riversiderobotics.phil.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import riversiderobotics.phil.Constants;


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
    private final DoubleSolenoid gearbox = new DoubleSolenoid(Constants.PNEUMATICS_HUB, PneumaticsModuleType.REVPH, Constants.GEARBOX_FORWARD, Constants.GEARBOX_REVERSE);

    //Drivetrain
    private final MotorControllerGroup left_motor_group = new MotorControllerGroup(motor_lb, motor_lf, motor_lt);
    private final MotorControllerGroup right_motor_group = new MotorControllerGroup(motor_rb, motor_rf, motor_rt);

    private final DifferentialDrive drivetrain = new DifferentialDrive(left_motor_group, right_motor_group);

    public DriveSubsystem()
    {
      motor_lb.setIdleMode(IdleMode.kBrake);
      motor_lf.setIdleMode(IdleMode.kBrake);
      motor_lt.setIdleMode(IdleMode.kBrake);
      motor_rb.setIdleMode(IdleMode.kBrake);
      motor_rf.setIdleMode(IdleMode.kBrake);
      motor_rt.setIdleMode(IdleMode.kBrake);

      motor_lb.setInverted(true);
      motor_lf.setInverted(true);
      motor_lt.setInverted(true);
    }

    public void drive(double forward, double rotation)
    {
      drivetrain.arcadeDrive(forward, rotation * .85);
    }

    public void gearShift(DoubleSolenoid.Value value)
    {
      gearbox.set(value);
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds()
    {
        return new DifferentialDriveWheelSpeeds(motor_lb.getEncoder().getVelocity(), motor_rb.getEncoder().getVelocity());
    }

    public void feedVolts(double left, double right)
    {
        left_motor_group.setVoltage(left);
        right_motor_group.setVoltage(right);
        drivetrain.feed();
    }

    public void resetEncoders()
    {
        motor_lb.getEncoder().setPosition(0);
        motor_lf.getEncoder().setPosition(0);
        motor_lt.getEncoder().setPosition(0);
        motor_rb.getEncoder().setPosition(0);
        motor_rf.getEncoder().setPosition(0);
        motor_rt.getEncoder().setPosition(0);
    }

    public void setPower(double leftPower, double rightPower)
    {
        left_motor_group.set(leftPower);
        right_motor_group.set(rightPower);
    }
}
