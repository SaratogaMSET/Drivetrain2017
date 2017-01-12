package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DrivetrainSubsystem extends Subsystem {
    
	public DigitalInput limit;
	public Victor [] motors;
	public Encoder leftEncoder; 
	public Encoder rightEncoder;
	public Relay spike;
	public static final double powerRatio = 1;
	public Compressor compressCAN;
	public DoubleSolenoid driveSol;

	
	public DrivetrainSubsystem() {
//		limit = new DigitalInput(RobotMap.Drivetrain.TEST_LIMIT_PORT);
		leftEncoder = new Encoder(RobotMap.Drivetrain.LEFT_SIDE_ENCODER[0],RobotMap.Drivetrain.LEFT_SIDE_ENCODER[1],false);
		rightEncoder = new Encoder(RobotMap.Drivetrain.RIGHT_SIDE_ENCODER[0],RobotMap.Drivetrain.RIGHT_SIDE_ENCODER[1],true);
		leftEncoder.setDistancePerPulse(360.0 / 256.0
				* 20.0 / 50.0 * 20.0 / 48.0 * 16.0 / 48.0);
		rightEncoder.setDistancePerPulse(360.0 / 256.0
				* 20.0 / 50.0 * 20.0 / 48.0 * 16.0 / 48.0);
		compressCAN = new Compressor();
		spike = new Relay(RobotMap.Drivetrain.SPIKE_PORT);
		motors = new Victor[4];
		//FR,BR,BL,BR
		for(int i =0; i < motors.length; i++) {
			motors[i] = new Victor(RobotMap.Drivetrain.MOTOR_PORTS[i]);
		}
		driveSol  = new DoubleSolenoid(RobotMap.Drivetrain.DRIVE_SOL[0],
				RobotMap.Drivetrain.DRIVE_SOL[1]);
//		compressCAN.setClosedLoopControl(true);
		
	}
	public double leftEncoderSpeed(){
		return leftEncoder.getRate();
	}
	public double rightEncoderSpeed(){
		return rightEncoder.getRate();
	}
	public void driveFwdRot(double fwd, double rot) {
		double left = fwd + rot, right = fwd - rot;
        double max = Math.max(1, Math.max(Math.abs(left), Math.abs(right)));
        left /= max;
        right /= max;
        rawDrive(left, right);
    }
	public void shift(Boolean highGear){
		driveSol.set(highGear ? DoubleSolenoid.Value.kReverse : DoubleSolenoid.Value.kForward);
	}
	public boolean isPressed(){
		return limit.get();
	}
    public void rawDrive(double left, double right) {
        motors[0].set(powerRatio * right);
        motors[1].set(powerRatio * right);
        motors[2].set(-left);
        motors[3].set(-left);

//        SmartDashboard.putNumber("DriveMotorLeft", left);
//        SmartDashboard.putNumber("DriveMotorRight", right);
//    
        
        
    }
    public void spikey(){
    	if(compressCAN.getPressureSwitchValue()){
    		spike.set(Relay.Value.kOff);

    	}else{

    		spike.set(Relay.Value.kForward);

    	}

    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

