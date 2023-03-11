package riversiderobotics.phil.util;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import riversiderobotics.phil.Constants;


public class Pneumatics
{
    private Compressor compressor = new Compressor(PneumaticsModuleType.REVPH);

    public void enableCompressor()
    {
        compressor.enableDigital();
    }

    public void disableCompressor()
    {
        compressor.disable();
    }

    public double returnPressure()
    {
        return compressor.getPressure();
    }

    public void putSmartDashboard()
    {
        SmartDashboard.putNumber("Compressor Pressure", compressor.getPressure());
    }
}
