package riversiderobotics.phil.commands;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import riversiderobotics.phil.subsystems.DriveSubsystem;

public class AutoBalance extends CommandBase
{
    private AHRS gyro;
    private DriveSubsystem drive;

    private int state = 0;
    private int debounceCount = 0;
    private double robotSpeedSlow;
    private double robotSpeedFast;
    private double onChargeStationDegree;
    private double levelDegree;
    private double debounceTime;

    private double speed;

    public AutoBalance(AHRS gyroscope, DriveSubsystem subsystem, double slowSpeed, double fastSpeed, double chargeDegree, double _levelDegree, double _debounceTime)
    {
        addRequirements(subsystem);

        gyro = gyroscope;
        drive = subsystem;
        robotSpeedSlow = slowSpeed;
        robotSpeedFast = fastSpeed;
        onChargeStationDegree = chargeDegree;
        levelDegree = _levelDegree;
        debounceTime = _debounceTime;
    }

    @Override
    public void initialize()
    {
        drive.gearShift(DoubleSolenoid.Value.kForward);
    }

    @Override
    public void execute()
    {
        speed = autoBalanceRoutine();
        drive.setPower(speed, speed);
    }

    @Override
    public boolean isFinished()
    {
        return (gyro.getPitch() == 0);
    }

    private double getPitch() {
        return Math.atan2((-gyro.getRawGyroX()),
                Math.sqrt(gyro.getRawGyroY() * gyro.getRawGyroY() + gyro.getRawGyroZ() * gyro.getRawGyroZ())) * 57.3;
    }

    private double getRoll() {
        return Math.atan2(gyro.getRawGyroY(), gyro.getRawGyroZ()) * 57.3;
    }

    // returns the magnititude of the robot's tilt calculated by the root of
    // pitch^2 + roll^2, used to compensate for diagonally mounted rio
    private double getTilt() {
        double pitch = getPitch();
        double roll = getRoll();
        if ((pitch + roll) >= 0) {
            return Math.sqrt(pitch * pitch + roll * roll);
        } else {
            return -Math.sqrt(pitch * pitch + roll * roll);
        }
    }

    private int secondsToTicks(double time) {
        return (int) (time * 50);
    }

    // routine for automatically driving onto and engaging the charge station.
    // returns a value from -1.0 to 1.0, which left and right motors should be set
    // to.
    private double autoBalanceRoutine() {
        switch (state) {
            // drive forwards to approach station, exit when tilt is detected
            case 0:
                if (getTilt() > onChargeStationDegree) {
                    debounceCount++;
                }
                if (debounceCount > secondsToTicks(debounceTime)) {
                    state = 1;
                    debounceCount = 0;
                    return robotSpeedSlow;
                }
                return robotSpeedFast;
            // driving up charge station, drive slower, stopping when level
            case 1:
                if (getTilt() < levelDegree) {
                    debounceCount++;
                }
                if (debounceCount > secondsToTicks(debounceTime)) {
                    state = 2;
                    debounceCount = 0;
                    return 0;
                }
                return robotSpeedSlow;
            // on charge station, stop motors and wait for end of auto
            case 2:
                if (Math.abs(getTilt()) <= levelDegree / 2) {
                    debounceCount++;
                }
                if (debounceCount > secondsToTicks(debounceTime)) {
                    state = 4;
                    debounceCount = 0;
                    return 0;
                }
                if (getTilt() >= levelDegree) {
                    return 0.1;
                } else if (getTilt() <= -levelDegree) {
                    return -0.1;
                }
            case 3:
                return 0;
        }
        return 0;
    }

}
