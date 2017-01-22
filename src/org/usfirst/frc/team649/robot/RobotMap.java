package org.usfirst.frc.team649.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int OPERATOR_JOYSTICK = 0;
	public static final int DRIVE_JOYSTICK_HORIZONTAL = 2;
	public static final int DRIVE_JOYSTICK_VERTICAL = 1;
	
	public static class Drivetrain {
		// FR,BR,BL,BR
//		public static final int TEST_LIMIT_PORT = 0;
		public static final int[] LEFT_SIDE_ENCODER = {0,1};
		public static final int[] RIGHT_SIDE_ENCODER = {2,3};
		public static final int[] MOTOR_PORTS = { 9, 8, 7, 6 };
		public static final int SPIKE_PORT = 0;
		public static final int COMPRESS_LIMIT_CAN = 9;
		public static final int[] DRIVE_SOL = {4,5};
		public static final double MAX_SPEED = 1500.0;
		public static final double MAX_LOW_SPEED = 700.0;
		
	}
	public static class Shooter{
		public static final int LEFT_SHOOTER_VICTOR = 3;//5
		public static final int RIGHT_SHOOTER_VICTOR = 2;//4
		public static final double LEFT_SHOOTER_POWER_MIN = 0.25;
		public static final double RIGHT_SHOOTER_POWER_MIN = 0.25;
		public static final int LEFT_ENISTEIN_PORT = 0;
		public static final int RIGHT_ENISTEIN_PORT = 1;
		public static final double LEFT_MAX_RPM_WALL = 2800;
		public static final double RIGHT_MAX_RPM_WALL = 2800;
		public static final double LEFT_MIN_RPM_WALL = 2400;
		public static final double RIGHT_MIN_RPM_WALL = 2400;
		public static final double LEFT_TARGET_RPM_WALL = 2600;
		public static final double RIGHT_TARGET_RPM_WALL = 2600;
		public static final double LEFT_MIN_POWER_WALL = 0.225;
		public static final double RIGHT_MIN_POWER_WALL = 0.225;
		public static final double LEFT_MAX_POWER_WALL = 0.25;
		public static final double RIGHT_MAX_POWER_WALL = 0.25;
		public static final double LEFT_SHOOTER_POWER_MID = 0;
		public static final double RIGHT_SHOOTER_POWER_MID = 0;
		public static final double LEFT_SHOOTER_POWER_MAX = 0;
		public static final double RIGHT_SHOOTER_POWER_MAX = 0;
	}
	public static class Turret{
		public static final int PIVOT_VICTOR = 4;
		public static final int[] PIVOT_SHAFT_ENCODER = {4,5};
	}
	public static class GyroShooter{
		public static final int GYRO_PORT = 0;
		public static final double GYRO_DEFAULT_ANGLE_VERTICAL = 0;
		public static final double GYRO_DEFAULT_ANGLE_HORIZONTAL = 0;
		public static final double GYRO_ANGLE_TO_FACE = 0;
		public static final int GYRO_TURRET_MOTOR_PORT=0;
	}

    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
