package riversiderobotics.phil;


public final class Constants
{
    //DS
    public static final int DRIVER_PORT = 0;
    public static final int MANIPULATOR_PORT = 1;

    //Motors
    public static final int MOTOR_LB = 1;
    public static final int MOTOR_LF = 2;
    public static final int MOTOR_LT = 3;
    public static final int MOTOR_RB = 4;
    public static final int MOTOR_RF = 5;
    public static final int MOTOR_RT = 6;
    
    public static final int MOTOR_EXTEND = 9;

    public static final int EXTEND_LIMIT = 100000;
    public static final int ROTATE_BACK_START = 100000;
    public static final int ROTATE_FORWARD_START = 100000;

    public static final int MOTOR_ARM_BASE_LEFT = 7;
    public static final int MOTOR_ARM_BASE_RIGHT = 8;

    //Pneumatics

    public static final int PNEUMATICS_HUB = 11;

    public static final int GEARBOX_FORWARD = 0;
    public static final int GEARBOX_REVERSE = 1;

    public static final int INTAKE_OUT = 2;
    public static final int INTAKE_IN = 3;
}
