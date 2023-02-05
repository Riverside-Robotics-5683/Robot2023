package riversiderobotics.twentythree.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import riversiderobotics.twentythree.Constants;

public class TeleOpSubsystem extends SubsystemBase
{
    private XboxController gamepad = new XboxController(Constants.DRIVER_PORT);

//this sets up the Dpad on the xbox controller
    /*

    |THIS DID NOT WORK| I think it's because the dpad is noted as a series of buttons ie a joystick
   all together instead of just one button like "X" im just going to set pneumatics to
   the trigger buttons LB&LT Though I would like to revisit this at a later time.
   :) from: Tony


 public class DpadButton extends XboxController.Button {private joystick joystick; private Direction direction;
     public DpadButton(Joystick joystick, Direction direction)
     {
        this.joystick = joystick;
        this.direction = direction;
           }
     @Override
    public boolean get() {
         int degree = joystick.getPOV(0);
         return degree == direction.degree;
     }

    public enum Direction {
        Left(270),
        Right(90),
        Up(0),

        int degree;
        Direction(int degree){
            this.degree = degree;
        }


    }


}
*/
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
