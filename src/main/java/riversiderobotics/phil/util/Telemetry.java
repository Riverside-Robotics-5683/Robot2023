package riversiderobotics.phil.util;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Telemetry
{
    public void putNavx(AHRS navx)
    {
        SmartDashboard.putNumber("NavX Heading", navx.getCompassHeading());
        SmartDashboard.putNumber("NavX Roll", navx.getRoll());
        SmartDashboard.putNumber("NavX Pitch", navx.getPitch());
    }
}