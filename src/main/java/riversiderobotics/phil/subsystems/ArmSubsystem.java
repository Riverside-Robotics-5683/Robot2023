package riversiderobotics.phil.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
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

  public ArmSubsystem() 
  {
    motor_arm_left.setIdleMode(IdleMode.kBrake);
    motor_arm_right.setIdleMode(IdleMode.kBrake);
    motor_extend.setIdleMode(IdleMode.kBrake);
  }

  public void rotateArm(double speed)
  {
    motor_arm_left.set(speed * .45);
  }

  public void extendArm(double speed)
  {
    motor_extend.set(speed * .25);
  }

  public void setIntake(DoubleSolenoid.Value value)
  {
    intake.set(value);
  }
}