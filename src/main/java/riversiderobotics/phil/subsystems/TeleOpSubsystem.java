package riversiderobotics.phil.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import riversiderobotics.phil.Constants;
import riversiderobotics.phil.util.Pneumatics;
import riversiderobotics.phil.util.Telemetry;

public class TeleOpSubsystem extends SubsystemBase
{
    //Driver Station
    private final XboxController driver = new XboxController(Constants.DRIVER_PORT);
    private final XboxController manipulator = new XboxController(Constants.MANIPULATOR_PORT);

    //Motors
    private final CANSparkMax motor_lb = new CANSparkMax(Constants.MOTOR_LB, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_lf = new CANSparkMax(Constants.MOTOR_LF, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_lt = new CANSparkMax(Constants.MOTOR_LT, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_rb = new CANSparkMax(Constants.MOTOR_RB, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_rf = new CANSparkMax(Constants.MOTOR_RF, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_rt = new CANSparkMax(Constants.MOTOR_RT, CANSparkMaxLowLevel.MotorType.kBrushless);

    private final CANSparkMax motor_arm_base_left = new CANSparkMax(Constants.MOTOR_ARM_BASE_LEFT, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_arm_base_right = new CANSparkMax(Constants.MOTOR_ARM_BASE_RIGHT, CANSparkMaxLowLevel.MotorType.kBrushless);

    //Pneumatics
//    private final Compressor compressor = new Compressor(Constants.COMPRESSOR, PneumaticsModuleType.REVPH);
//
//    private final Solenoid left_gearbox = new Solenoid(PneumaticsModuleType.REVPH, Constants.GEARBOX_LEFT_CHANNEL);
//    private final Solenoid right_gearbox = new Solenoid(PneumaticsModuleType.REVPH, Constants.GEARBOX_RIGHT_CHANNEL);

    //Drivetrain
    private final MotorControllerGroup left_motor_group = new MotorControllerGroup(motor_lb, motor_lf, motor_lt);
    private final MotorControllerGroup right_motor_group = new MotorControllerGroup(motor_rb, motor_rf, motor_rt);

    private final DifferentialDrive drivetrain = new DifferentialDrive(left_motor_group, right_motor_group);
  
    //Other
    private final AHRS gyro = new AHRS(SPI.Port.kMXP);

    private final Telemetry telemetry = new Telemetry();

    public TeleOpSubsystem() {}

    @Override
    public void periodic()
    {
        double armPower = manipulator.getLeftY();

//        if (driver.getLeftTriggerAxis() > 0.5f)
//        {
//          left_gearbox.set(true);
//          right_gearbox.set(true);
//        }
//
//        if (driver.getRightTriggerAxis() > 0.5f)
//        {
//          left_gearbox.set(false);
//          right_gearbox.set(false);
//        }

        drivetrain.arcadeDrive(-driver.getLeftY(), driver.getRightX());
      
//        motor_arm_base_left.set(-armPower);
//        motor_arm_base_right.set(armPower);

        telemetry.putNavx(gyro);
    }

    public void initPneumatics()
    {
//        compressor.enableDigital();
        motor_lb.setIdleMode(CANSparkMax.IdleMode.kCoast);
        motor_lf.setIdleMode(CANSparkMax.IdleMode.kCoast);
        motor_lt.setIdleMode(CANSparkMax.IdleMode.kCoast);
        motor_rb.setIdleMode(CANSparkMax.IdleMode.kCoast);
        motor_rf.setIdleMode(CANSparkMax.IdleMode.kCoast);
        motor_rt.setIdleMode(CANSparkMax.IdleMode.kCoast);
        
        motor_lb.setInverted(true);
        motor_lf.setInverted(true);
        motor_lt.setInverted(true);
    }

    public void disablePneumatics()
    {
//        compressor.disable();
    }
}
