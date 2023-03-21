package riversiderobotics.phil;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import riversiderobotics.phil.commands.*;
import riversiderobotics.phil.subsystems.ArmSubsystem;
import riversiderobotics.phil.subsystems.DriveSubsystem;
import riversiderobotics.phil.util.CreateAutonomous;

import java.util.HashMap;

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

  private final AHRS gyro = new AHRS(SPI.Port.kMXP);

  //Autos
  private HashMap<String, Command> events = new HashMap<>();

  private final Command test_auto = new CreateAutonomous("TestAutonomous", 4, 3, gyro, drive, events).returnCommand();
  private final Command basic_left = new CreateAutonomous("BasicLeft", 4, 3, gyro, drive, events).returnCommand();
  private final Command basic_platform = new CreateAutonomous("BasicPlatform", 4, 3, gyro, drive, events).returnCommand();
  private final Command basic_center_left = new CreateAutonomous("BasicCenterLeft", 4, 3, gyro, drive, events).returnCommand();
  private final Command basic_center_right = new CreateAutonomous("BasicCenterRight", 4, 3, gyro, drive, events).returnCommand();
  private final Command basic_right = new CreateAutonomous("BasicRight", 4, 3, gyro, drive, events).returnCommand();
  private final Command really_basic = new ReallyBasicAuto(drive);

  public Container()
  {
    gyro.calibrate();
    //Run configure
    configure();
    //Put all the autonomous markers
    events.put("printPass", new PrintCommand("Passed marker!"));
    events.put("printNearFinish", new PrintCommand("Near the finish!"));
    events.put("autoBalance", new AutoBalance(gyro, drive, 0.125, 0.25, 13, 6, 0.2));
    drive.resetEncoders();
    arm.resetEncoders();

    //Set TeleOp command as default
    drive.setDefaultCommand(new NormalDrive(drive, arm, () -> -driver.getLeftY(), () -> -driver.getRightX(), () -> manipulator.getLeftY(), () -> manipulator.getRightY()));

    chooser.setDefaultOption("Basic Left", basic_left);
    chooser.addOption("Basic Center Left", basic_center_left);
    chooser.addOption("Basic Center Right", basic_center_right);
    chooser.addOption("Basic Right", basic_right);
    chooser.addOption("Basic Platform", basic_platform);
    chooser.addOption("Really Basic", really_basic);
    chooser.addOption("No Auto", null);
    chooser.addOption("Test Auto", test_auto);

    SmartDashboard.putData(chooser);
  }
  //Configure button bindings for driving
  private void configure()
  {
    new JoystickButton(driver, Button.kX.value).onTrue(new GearShift(drive, Value.kForward));
    new JoystickButton(driver, Button.kY.value).onTrue(new GearShift(drive, Value.kReverse));
    new JoystickButton(manipulator, Button.kRightBumper.value).onTrue(new IntakeManage(arm, Value.kForward));
    new JoystickButton(manipulator, Button.kLeftBumper.value).onTrue(new IntakeManage(arm, Value.kReverse));
  }

  public Command getAutonomous()
  {
    return chooser.getSelected();
  }
}