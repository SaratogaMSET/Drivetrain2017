package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.RobotMap;
import org.usfirst.frc.team649.robot.subsystems.TurretSubsystem.PIDConstants;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TurretSubsystem extends PIDSubsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static class PIDConstants {
		public static final double k_P = 0.0;
		public static final double k_I = 0.0;
		public static final double k_D = 0.0;
		
		public static final double ENCODER_DEGREES_PER_PULSE = 0.0;
		
		public static final double MAX_MOTOR_OUTPUT = 0.05;
		public static final double MIN_MOTOR_OUTPUT = 0.01;
		public static final double MAX_MOTOR_DOWN_POWER = 0.0;
		public static final double ABSOLUTE_TOLERANCE = 0.05;
		
		public static double max_motor_up_power = 0.6;
	}
	public Victor turretMotor;
	public Encoder turretShaftEncoder;
	public PIDController pid;
	public Servo hoodServo;
	public double angleOfShooter;
	
	public TurretSubsystem(){
		super ("Turret Subsystem", PIDConstants.k_P, PIDConstants.k_I, PIDConstants.k_D);
		
		turretMotor = new Victor(RobotMap.Turret.PIVOT_VICTOR);
		
		hoodServo = new Servo(RobotMap.Turret.HOOD_SERVO);
		
		turretShaftEncoder = new Encoder(RobotMap.Turret.PIVOT_SHAFT_ENCODER[0],
				 RobotMap.Turret.PIVOT_SHAFT_ENCODER[1]);
		turretShaftEncoder.setDistancePerPulse(PIDConstants.ENCODER_DEGREES_PER_PULSE);
		
		pid = this.getPIDController();
		pid.enable();
		pid.setOutputRange(PIDConstants.MIN_MOTOR_OUTPUT, PIDConstants.MAX_MOTOR_OUTPUT);
		pid.setAbsoluteTolerance(PIDConstants.ABSOLUTE_TOLERANCE);
		
	}
	public void rotate(double power){
		turretMotor.set(power);
	}
	
	public boolean isOnTarget(double setpoint) {
		return (setpoint-turretShaftEncoder.getDistance()) > PIDConstants.ABSOLUTE_TOLERANCE;
	}
	
	public void setHoodAngle(double angle) {
		angleOfShooter = angle;
		hoodServo.setAngle(angleOfShooter);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		if (output > PIDConstants.MAX_MOTOR_OUTPUT) {
			output = PIDConstants.MAX_MOTOR_OUTPUT;
		} else if (output < PIDConstants.MIN_MOTOR_OUTPUT) {
			output = PIDConstants.MIN_MOTOR_OUTPUT;
		}
		rotate(output);	
	}
	
	public PIDController getPIDController() {
		return pid;
	}

}
