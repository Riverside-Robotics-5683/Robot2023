package riversiderobotics.phil.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import riversiderobotics.phil.Constants;
import riversiderobotics.phil.util.Pneumatics;
import riversiderobotics.phil.util.Telemetry;

public class TeleOpSubsystem extends SubsystemBase
{
    private final XboxController gamepad = new XboxController(Constants.DRIVER_PORT);

    private final CANSparkMax motor_tl = new CANSparkMax(Constants.MOTOR_TL, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_ml = new CANSparkMax(Constants.MOTOR_ML, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_bl = new CANSparkMax(Constants.MOTOR_BL, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_tr = new CANSparkMax(Constants.MOTOR_TR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_mr = new CANSparkMax(Constants.MOTOR_MR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_br = new CANSparkMax(Constants.MOTOR_BR, CANSparkMaxLowLevel.MotorType.kBrushless);

    private final Pneumatics compressor = new Pneumatics();

    private final AHRS gyro = new AHRS(SPI.Port.kMXP);

    private final Telemetry telemetry = new Telemetry();

    public TeleOpSubsystem() {}

    @Override
    public void periodic()
    {
        double lMotorPower = gamepad.getLeftY(), rMotorPower = gamepad.getLeftY();

        motor_tl.set(lMotorPower);
        motor_ml.set(lMotorPower);
        motor_bl.set(lMotorPower);
        motor_tr.set(rMotorPower);
        motor_mr.set(rMotorPower);
        motor_br.set(rMotorPower);

        telemetry.putNavx(gyro);
    }

    public void initPneumatics()
    {
        compressor.enableCompressor();
    }

    public void disablePneumatics()
    {
        compressor.disableCompressor();
    }
}
