package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DrivetrainSubsystem extends PIDSubsystem {
    
	
	public static class DrivePID {
		public static final double k_P = 0.2;
		public static final double k_I = 0.0;
		public static final double k_D = 0.01;
		
		public static final double ABSOLUTE_TOLERANCE = 0.05;
		
		public static final double DISTANCE_PER_PULSE = 12.56 / 256 * 60.0/14.0; // assuming cuz 4pi is circumference???
		//tried 0.4, 0.9, 0.8;
		// low  60/14
		//high 44/32
	}
	public Encoder leftEncoder, rightEncoder;
	public DigitalInput limit;
	public PIDController drivePID;
	public Victor [] motors;
	public Relay spike; 
	public Compressor compressCAN;
	public DoubleSolenoid driveSol;
	double currentSpeedLeft;
	double currentSpeedRight;
	public boolean isAutoShiftTrue;
	Timer time;
	
	public DrivetrainSubsystem() {
		super ("Drivetrain PID", DrivePID.k_P, DrivePID.k_I, DrivePID.k_P);
		time = new Timer();
		isAutoShiftTrue = false;
		currentSpeedLeft = 0.0;
		currentSpeedRight = 0.0;
		
		leftEncoder = new Encoder(RobotMap.Drivetrain.LEFT_SIDE_ENCODER[0],RobotMap.Drivetrain.LEFT_SIDE_ENCODER[1],false);
		rightEncoder = new Encoder(RobotMap.Drivetrain.RIGHT_SIDE_ENCODER[0],RobotMap.Drivetrain.RIGHT_SIDE_ENCODER[1],true);
	    leftEncoder.setDistancePerPulse(DrivePID.DISTANCE_PER_PULSE);
		rightEncoder.setDistancePerPulse(DrivePID.DISTANCE_PER_PULSE);
		
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
		
		drivePID = this.getPIDController();
		
	}
	public void autoShift(){
		if((Math.abs(leftEncoder.getRate()) + Math.abs(rightEncoder.getRate())) > RobotMap.Drivetrain.MAX_LOW_SPEED*1.7){
			shift(true);
			isAutoShiftTrue = true;
			time.start();
		}else if(time.get() > 1.0){
			shift(false);
			isAutoShiftTrue = false;
			time.stop();
			time.reset();
		}
	}
	public void driveWithEncoder(double left, double right){
		double targetRPMLeft = RobotMap.Drivetrain.MAX_SPEED*left;
		double targetRPMRight = RobotMap.Drivetrain.MAX_SPEED*right;
		double curretEncoderLeft = leftEncoder.getRate();
		double curretEncoderRight = rightEncoder.getRate();
		if(targetRPMLeft < 0.0){
			if(curretEncoderLeft>targetRPMLeft){
				double diff = Math.abs(targetRPMLeft) - Math.abs(curretEncoderLeft);
				double powerDiff = diff/(RobotMap.Drivetrain.MAX_SPEED*2);
				currentSpeedLeft -= powerDiff;
				//go further negative
			}else{
				double diff = Math.abs(curretEncoderLeft) - Math.abs(targetRPMLeft);
				double powerDiff = diff/(RobotMap.Drivetrain.MAX_SPEED);
				currentSpeedLeft += powerDiff;
				//go more towards 0
			}
		}else if(targetRPMLeft > 0.0){
			if(curretEncoderLeft<targetRPMLeft){
				double diff = Math.abs(targetRPMLeft) - Math.abs(curretEncoderLeft);
				double powerDiff = diff/(RobotMap.Drivetrain.MAX_SPEED*2);
				currentSpeedLeft += powerDiff;
				//go more positive
			}else{
				double diff = Math.abs(curretEncoderLeft) - Math.abs(targetRPMLeft);
				double powerDiff = diff/(RobotMap.Drivetrain.MAX_SPEED);
				currentSpeedLeft -= powerDiff;
				//go more towards 0
			}
		}else{
			currentSpeedLeft = 0.0;
		}
		if(targetRPMRight < 0.0){
			if(curretEncoderRight>targetRPMRight){
				double diff = Math.abs(targetRPMRight) - Math.abs(curretEncoderRight);
				double powerDiff = diff/(RobotMap.Drivetrain.MAX_SPEED*2);
				currentSpeedRight -= powerDiff;
				//go further negative
			}else{
				double diff = Math.abs(curretEncoderRight) - Math.abs(targetRPMRight);
				double powerDiff = diff/(RobotMap.Drivetrain.MAX_SPEED);
				currentSpeedRight += powerDiff;
				//go more towards 0
			} 
		}else if(targetRPMRight > 0.0){
			if(curretEncoderRight<targetRPMRight){
				double diff = Math.abs(targetRPMRight) - Math.abs(curretEncoderRight);
				double powerDiff = diff/(RobotMap.Drivetrain.MAX_SPEED*2);
				currentSpeedRight += powerDiff;
				//go more positive
			}else{
				double diff = Math.abs(curretEncoderRight) - Math.abs(targetRPMRight);
				double powerDiff = diff/(RobotMap.Drivetrain.MAX_SPEED);
				currentSpeedRight -= powerDiff;
				//go more towards 0
			}
		}else{
			currentSpeedRight = 0.0;
		}
		if(currentSpeedLeft > 1.0){
			currentSpeedLeft = 1.0;
		}else if(currentSpeedLeft < -1.0){
			currentSpeedLeft = -1.0;
		}
		if(currentSpeedRight > 1.0){
			currentSpeedRight = 1.0;
		}else if(currentSpeedRight < -1.0){
			currentSpeedRight = -1.0;
		}
		motors[0].set(currentSpeedRight);
		motors[1].set(currentSpeedRight);
		motors[2].set(currentSpeedLeft);
		motors[3].set(currentSpeedLeft);
	}
	public double leftEncoderSpeed(){
		return leftEncoder.getRate();
	}
	public double rightEncoderSpeed(){
		return rightEncoder.getRate();
	}
	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
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
        motors[0].set(right);
        motors[1].set(right);
        motors[2].set(-left * 0.65);
        motors[3].set(-left * 0.65); //have used 0.8, 0.75, 0.72, 0.7
        // slight delay in left side
        // 0.65 at 0.5 power 
        
    }
    public void spikey(){
    	if(compressCAN.getPressureSwitchValue()){
    		spike.set(Relay.Value.kOff);

    	}else{

    		spike.set(Relay.Value.kForward);

    	}

    }
    public double getDistanceDTLeft() {
    	return leftEncoder.getDistance();
    }
    
    public double getDistanceDTRight() {
    	return rightEncoder.getDistance();
    }
    public double getDistanceDTBoth() {
    	return rightEncoder.getDistance()/2 + leftEncoder.getDistance()/2;
    }
    
    public boolean isOnTarget(double setpoint) {
    	return Math.abs(getDistanceDTBoth()-setpoint) < DrivePID.ABSOLUTE_TOLERANCE;
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands. 

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	public double getTranslationalDistanceForTurn(double angle) {
		System.out.println((angle/ 360.0) * (4 * Math.PI)); //wait if 25.125 is circumference of 8 inch wheels already
		return (angle/ 360.0) * (4 * Math.PI) * 1.08;
	}
	
	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		SmartDashboard.putNumber("PIDInput", getDistanceDTBoth());
		return getDistanceDTBoth();
	}
	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		SmartDashboard.putNumber("PIDOutput", output);
		rawDrive(output, output);
		
	}
}

