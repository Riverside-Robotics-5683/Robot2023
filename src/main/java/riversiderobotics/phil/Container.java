package riversiderobotics.phil;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import riversiderobotics.phil.commands.GearShift;
import riversiderobotics.phil.commands.IntakeManage;
import riversiderobotics.phil.commands.NormalDrive;
import riversiderobotics.phil.commands.TestAutonomous;
import riversiderobotics.phil.subsystems.ArmSubsystem;
import riversiderobotics.phil.subsystems.DriveSubsystem;

public class Container
{
  //Subsystems
  private final DriveSubsystem drive = new DriveSubsystem();
  private final ArmSubsystem arm = new ArmSubsystem();

  //Controllers
  private final XboxController driver = new XboxController(Constants.DRIVER_PORT);
  private final XboxController manipulator = new XboxController(Constants.MANIPULATOR_PORT);

  //SendableChooser
  private final SendableChooser<Command> chooser = new SendableChooser<>();

  private final TestAutonomous test_auto = new TestAutonomous();

  public Container()
  {
    //Run configure
    configure();
    //Set TeleOp command as default
    drive.setDefaultCommand(new NormalDrive(drive, arm, () -> -driver.getLeftY(), () -> -driver.getRightX(), () -> -manipulator.getLeftY(), () -> manipulator.getRightY()));

    chooser.setDefaultOption("Test", test_auto);
  }

  //Configure button bindings for driving
  private void configure()
  {
    new JoystickButton(driver, Button.kX.value).onTrue(new GearShift(drive, Value.kForward));
    new JoystickButton(driver, Button.kY.value).onTrue(new GearShift(drive, Value.kReverse));
    new JoystickButton(manipulator, Button.kLeftBumper.value).onTrue(new IntakeManage(arm, Value.kForward));
    new JoystickButton(manipulator, Button.kRightBumper.value).onTrue(new IntakeManage(arm, Value.kReverse));
  }
}