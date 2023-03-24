package riversiderobotics.phil.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import riversiderobotics.phil.Constants;

public class ArmSubsystem extends SubsystemBase 
{
  // Motors
  private final CANSparkMax motor_arm_left = new CANSparkMax(Constants.MOTOR_ARM_BASE_LEFT, MotorType.kBrushless);
  private final CANSparkMax motor_arm_right = new CANSparkMax(Constants.MOTOR_ARM_BASE_RIGHT, MotorType.kBrushless);

  private final CANSparkMax motor_extend = new CANSparkMax(Constants.MOTOR_EXTEND, MotorType.kBrushless);

  // Pneumatics
  private final DoubleSolenoid intake = new DoubleSolenoid(Constants.PNEUMATICS_HUB, PneumaticsModuleType.REVPH, Constants.INTAKE_IN, Constants.INTAKE_OUT);

  //Encoder
  private final Encoder arm_rotate = new Encoder(0, 1);

  public ArmSubsystem() 
  {
    motor_arm_left.setIdleMode(IdleMode.kBrake);
    motor_arm_right.setIdleMode(IdleMode.kBrake);
    motor_extend.setIdleMode(IdleMode.kBrake);

    motor_arm_right.setInverted(false);
    motor_arm_left.setInverted(true);

    motor_extend.getEncoder().setPosition(0);

    arm_rotate.reset();
  }

  public void rotateArm(double speed)
  {
    motor_arm_left.set(speed * .2);
    motor_arm_right.set(speed * .2);
  }

  public void extendArm(double speed)
  {
    if (arm_rotate.get() >= Constants.ROTATE_FORWARD_START || arm_rotate.get() <= Constants.ROTATE_BACK_START)
    {
      if (motor_extend.getEncoder().getPosition() >= Constants.EXTEND_LIMIT)
      {
        return;
      }
    }
    motor_extend.set(speed * .15);
    //original motor extend speed .25

  }

  public void setIntake(DoubleSolenoid.Value value)
  {
    intake.set(value);
  }

  public void resetEncoders()
  {
    motor_extend.getEncoder().setPosition(0);
    arm_rotate.reset();
  }

  public void extendToPosition(double position, double speed)
  {
    if (motor_extend.getEncoder().getPosition() >= position)
    {
      motor_extend.setInverted(true);
    }
    else if (motor_extend.getEncoder().getPosition() <= position)
    {
      motor_extend.setInverted(false);
    }
    motor_extend.set(speed);
    while (motor_extend.getEncoder().getPosition() != position)
    {}
    motor_extend.set(0);
  }

  @Override
  public void periodic()
  {
    SmartDashboard.putNumber("Arm Encoder", arm_rotate.get());
    SmartDashboard.putNumber("Arm Extension Encoder", motor_extend.getEncoder().getPosition());
  }
}