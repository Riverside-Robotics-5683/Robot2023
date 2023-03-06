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
    private final CANSparkMax motor_tl = new CANSparkMax(Constants.MOTOR_TL, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_ml = new CANSparkMax(Constants.MOTOR_ML, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_bl = new CANSparkMax(Constants.MOTOR_BL, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_tr = new CANSparkMax(Constants.MOTOR_TR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_mr = new CANSparkMax(Constants.MOTOR_MR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_br = new CANSparkMax(Constants.MOTOR_BR, CANSparkMaxLowLevel.MotorType.kBrushless);

    private final CANSparkMax motor_arm_base_left = new CANSparkMax(Constants.MOTOR_ARM_BASE_LEFT, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_arm_base_right = new CANSparkMax(Constants.MOTOR_ARM_BASE_RIGHT, CANSparkMaxLowLevel.MotorType.kBrushless);

    //Pneumatics
    private final Compressor compressor = new Compressor(Constants.COMPRESSSOR, PneumaticsModuleType.REVPH);
  
    private final Solenoid left_gearbox = new Solenoid(PneumaticsModuleType.REVPH, Constants.GEARBOX_LEFT_CHANNEL);
    private final Solenoid right_gearbox = new Solenoid(PneumaticsModuleType.REVPH, Constants.GEARBOX_RIGHT_CHANNEL);

    //Drivetrain
    private final MotorControllerGroup left_motor_group = new MotorControllerGroup(motor_tl, motor_ml, motor_bl);
    private final MotorControllerGroup right_motor_group = new MotorControllerGroup(motor_tr, motor_mr, motor_br);

    private final DifferentialDrive drivetrain = new DifferentialDrive(left_motor_group, right_motor_group);
  
    //Other
    private final AHRS gyro = new AHRS(SPI.Port.kMXP);

    private final Telemetry telemetry = new Telemetry();

    public TeleOpSubsystem() {}

    @Override
    public void periodic()
    {
        double armPower = manipulator.getLeftY();

        if (driver.getLeftTriggerAxis() > 0.5f)
        {
          left_gearbox.set(true);
          right_gearbox.set(true);
        }

        if (driver.getRightTriggerAxis() > 0.5f)
        {
          left_gearbox.set(false);
          right_gearbox.set(false);
        }

        drivetrain.arcadeDrive(-driver.getLeftY(), -driver.getLeftX());
      
        motor_arm_base_left.set(-armPower);
        motor_arm_base_right.set(armPower);

        telemetry.putNavx(gyro);
    }

    public void initPneumatics()
    {
        compressor.enableDigital();
    }

    public void disablePneumatics()
    {
        compressor.disable();
    }
}
