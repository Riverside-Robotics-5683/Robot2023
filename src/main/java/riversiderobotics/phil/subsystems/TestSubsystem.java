package riversiderobotics.phil.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import riversiderobotics.phil.Constants;
import riversiderobotics.phil.util.Telemetry;


public class TestSubsystem extends SubsystemBase
{
    //Control
    private final XboxController gamepad = new XboxController(Constants.DRIVER_PORT);

    //Motors
    private final CANSparkMax motor_tl = new CANSparkMax(Constants.MOTOR_TL, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_ml = new CANSparkMax(Constants.MOTOR_ML, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_bl = new CANSparkMax(Constants.MOTOR_BL, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_tr = new CANSparkMax(Constants.MOTOR_TR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_mr = new CANSparkMax(Constants.MOTOR_MR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax motor_br = new CANSparkMax(Constants.MOTOR_BR, CANSparkMaxLowLevel.MotorType.kBrushless);

    //NavX
    private final AHRS navx = new AHRS(SPI.Port.kMXP);

    //Telemetry
    private final Telemetry telemetry = new Telemetry();

    @Override
    public void periodic()
    {
        double leftPower = gamepad.getLeftY(), rightPower = gamepad.getLeftY();

        motor_tl.set(leftPower);
        motor_ml.set(leftPower);
        motor_bl.set(leftPower);
        motor_tr.set(rightPower);
        motor_mr.set(rightPower);
        motor_br.set(rightPower);

        telemetry.putNavx(navx);
    }

    public void startLogging()
    {
        DataLogManager.start();
        DriverStation.startDataLog(DataLogManager.getLog());
    }
}
