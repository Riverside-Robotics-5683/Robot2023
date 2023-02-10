package riversiderobotics.phil.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import riversiderobotics.phil.Constants;
import riversiderobotics.phil.util.Pneumatics;

public class TeleOpSubsystem extends SubsystemBase
{
    private XboxController gamepad = new XboxController(Constants.DRIVER_PORT);

    private CANSparkMax motor_tl = new CANSparkMax(Constants.MOTOR_TL, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax motor_ml = new CANSparkMax(Constants.MOTOR_ML, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax motor_bl = new CANSparkMax(Constants.MOTOR_BL, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax motor_tr = new CANSparkMax(Constants.MOTOR_TR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax motor_mr = new CANSparkMax(Constants.MOTOR_MR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax motor_br = new CANSparkMax(Constants.MOTOR_BR, CANSparkMaxLowLevel.MotorType.kBrushless);

    private Pneumatics compressor = new Pneumatics();

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

        SmartDashboard.putNumber("Left Output", lMotorPower);
        SmartDashboard.putNumber("Right Output", rMotorPower);
        compressor.putSmartDashboard();
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
