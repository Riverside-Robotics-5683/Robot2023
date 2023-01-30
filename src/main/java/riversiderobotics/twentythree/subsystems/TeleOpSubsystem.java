package riversiderobotics.twentythree.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import riversiderobotics.twentythree.Constants;

public class TeleOpSubsystem extends SubsystemBase
{
    private XboxController gamepad = new XboxController(Constants.DRIVER_PORT);

    private TalonFX motor_tl = new TalonFX(Constants.MOTOR_TL);
    private TalonFX motor_ml = new TalonFX(Constants.MOTOR_ML);
    private TalonFX motor_bl = new TalonFX(Constants.MOTOR_BL);
    private TalonFX motor_tr = new TalonFX(Constants.MOTOR_TR);
    private TalonFX motor_mr = new TalonFX(Constants.MOTOR_MR);
    private TalonFX motor_br = new TalonFX(Constants.MOTOR_BR);

    public TeleOpSubsystem() {}

    @Override
    public void periodic()
    {
        double lMotorPower = gamepad.getLeftY(), rMotorPower = gamepad.getLeftY();

        motor_tl.set(TalonFXControlMode.PercentOutput, lMotorPower);
        motor_ml.set(TalonFXControlMode.PercentOutput, lMotorPower);
        motor_bl.set(TalonFXControlMode.PercentOutput, lMotorPower);
        motor_tr.set(TalonFXControlMode.PercentOutput, rMotorPower);
        motor_mr.set(TalonFXControlMode.PercentOutput, rMotorPower);
        motor_br.set(TalonFXControlMode.PercentOutput, rMotorPower);
    }
}
