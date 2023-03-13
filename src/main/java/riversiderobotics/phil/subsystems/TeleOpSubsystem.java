package riversiderobotics.phil.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import riversiderobotics.phil.Constants;
import riversiderobotics.phil.util.Pneumatics;
import riversiderobotics.phil.util.Telemetry;

import java.util.*;

public class TeleOpSubsystem extends SubsystemBase
{
    //Driver Station
    private final XboxController driver = new XboxController(Constants.DRIVER_PORT);
    // commented out manipulator controller since we are using dpad for arm rotation
    //private final XboxController manipulator = new XboxController(Constants.MANIPULATOR_PORT);

    //Motors
    private final CANSparkMax motor_lb = new CANSparkMax(Constants.MOTOR_LB, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_lf = new CANSparkMax(Constants.MOTOR_LF, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_lt = new CANSparkMax(Constants.MOTOR_LT, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_rb = new CANSparkMax(Constants.MOTOR_RB, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_rf = new CANSparkMax(Constants.MOTOR_RF, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_rt = new CANSparkMax(Constants.MOTOR_RT, CANSparkMaxLowLevel.MotorType.kBrushless);

    private final CANSparkMax motor_arm_base_left = new CANSparkMax(Constants.MOTOR_ARM_BASE_LEFT, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_arm_base_right = new CANSparkMax(Constants.MOTOR_ARM_BASE_RIGHT, CANSparkMaxLowLevel.MotorType.kBrushless);

    private final List<CANSparkMax> motors = Arrays.asList(motor_lb, motor_lf, motor_lt, motor_rb, motor_rf, motor_rt, motor_arm_base_left, motor_arm_base_right);

    //Pneumatics
    private final Compressor compressor = new Compressor(PneumaticsModuleType.REVPH);

    private final DoubleSolenoid left_gearbox = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.GEARBOX_LEFT_FORWARD, Constants.GEARBOX_LEFT_REVERSE);
    private final DoubleSolenoid right_gearbox = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.GEARBOX_RIGHT_FORWARD, Constants.GEARBOX_RIGHT_REVERSE);

    //Drivetrain
    private final MotorControllerGroup left_motor_group = new MotorControllerGroup(motor_lb, motor_lf, motor_lt);
    private final MotorControllerGroup right_motor_group = new MotorControllerGroup(motor_rb, motor_rf, motor_rt);
    //Set both arm rotation motors to a motor controller group
    //private final MotorControllerGroup armRotation_motor_group = new MotorControllerGroup(motor_arm_base_left, motor_arm_base_right);
    //dif drive 
    private final DifferentialDrive drivetrain = new DifferentialDrive(left_motor_group, right_motor_group);
    private final DifferentialDrive armTrain = new DifferentialDrive(motor_arm_base_left,motor_arm_base_right);
    //Other
    private final AHRS gyro = new AHRS(SPI.Port.kMXP);

    private final Telemetry telemetry = new Telemetry();

    public TeleOpSubsystem() {}

    @Override
    public void periodic()
    {
     
        //Pneumatics
        if (driver.getLeftTriggerAxis() > 0.5f)
        {
          left_gearbox.set(DoubleSolenoid.Value.kForward);
          right_gearbox.set(DoubleSolenoid.Value.kForward);
        }

        if (driver.getRightTriggerAxis() > 0.5f)
        {
          left_gearbox.set(DoubleSolenoid.Value.kReverse);
          right_gearbox.set(DoubleSolenoid.Value.kReverse);
        }
    
         //Drive base, DriveTrain 
         drivetrain.arcadeDrive(-driver.getLeftY(), driver.getRightX());
         //arm  
         armTrain.arcadeDrive(driver.getPOV(0), driver.getPOV(180));
       
      
         

        telemetry.putNavx(gyro);
    }

    public void init()
    {
        motor_lb.setIdleMode(CANSparkMax.IdleMode.kCoast);
        motor_lf.setIdleMode(CANSparkMax.IdleMode.kCoast);
        motor_lt.setIdleMode(CANSparkMax.IdleMode.kCoast);
        motor_rb.setIdleMode(CANSparkMax.IdleMode.kCoast);
        motor_rf.setIdleMode(CANSparkMax.IdleMode.kCoast);
        motor_rt.setIdleMode(CANSparkMax.IdleMode.kCoast);

        motor_lb.setInverted(true);
        motor_lf.setInverted(true);
        motor_lt.setInverted(true);

        motor_arm_base_left.setInverted(true);
        
    }

    public void disable()
    {
//        compressor.disable();
    }
}
