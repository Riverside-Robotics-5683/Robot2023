package riversiderobotics.twentythree.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import riversiderobotics.twentythree.Constants;

public class TeleOpSubsystem extends SubsystemBase
{
    private XboxController gamepad = new XboxController(Constants.DRIVER_PORT);

    private CANSparkMax motor_tl = new CANSparkMax(Constants.NEO_TL, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax motor_ml = new CANSparkMax(Constants.NEO_ML, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax motor_bl = new CANSparkMax(Constants.NEO_BL, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax motor_tr = new CANSparkMax(Constants.NEO_TR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax motor_mr = new CANSparkMax(Constants.NEO_MR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax motor_br = new CANSparkMax(Constants.NEO_BR, CANSparkMaxLowLevel.MotorType.kBrushless);



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
    }

    public void teleOpInit()
    {
        motor_tl.setIdleMode(CANSparkMax.IdleMode.kBrake);
        motor_ml.setIdleMode(CANSparkMax.IdleMode.kBrake);
        motor_bl.setIdleMode(CANSparkMax.IdleMode.kBrake);
        motor_tr.setIdleMode(CANSparkMax.IdleMode.kBrake);
        motor_mr.setIdleMode(CANSparkMax.IdleMode.kBrake);
        motor_br.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }
}
