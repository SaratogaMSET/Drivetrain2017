package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.RobotMap;
import org.usfirst.frc.team649.robot.subsystems.drivetrain.DrivetrainSubsystem.PIDConstants;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DrivetrainSubsystem extends PIDSubsystem {
    
	public Encoder leftEncoder, rightEncoder;
	public DigitalInput limit;
	public Victor [] motors;
	public Relay spike;
	public Compressor compressCAN;
	public DoubleSolenoid driveSol;
	double currentSpeedLeft;
	double currentSpeedRight;
	public boolean isAutoShiftTrue;
	public PIDController encoderDrivePID;
	public Timer time;
	public boolean shiftState; //false == low true == high
	Timer timeUp;
	
	public static class PIDConstants {
		public static final double PID_ABSOLUTE_TOLERANCE =3.0;
		public static final double ABS_TOLERANCE = 3.0;
		public static  double k_P = .05; 
		public static double k_I = 0;
		public static double k_D = 0.03;
	}
	
	public DrivetrainSubsystem() {
		super ("Drivetrain", PIDConstants.k_P, PIDConstants.k_I, PIDConstants.k_D);
		shiftState = false;
		time = new Timer();
		timeUp = new Timer();
		timeUp.start();

		isAutoShiftTrue = false;
		currentSpeedLeft = 0.0;
		currentSpeedRight = 0.0;
		leftEncoder = new Encoder(RobotMap.Drivetrain.LEFT_SIDE_ENCODER[0],RobotMap.Drivetrain.LEFT_SIDE_ENCODER[1],false);
		rightEncoder = new Encoder(RobotMap.Drivetrain.RIGHT_SIDE_ENCODER[0],RobotMap.Drivetrain.RIGHT_SIDE_ENCODER[1],true);
//		leftEncoder.setDistancePerPulse(360.0 / 256.0
//				* 20.0 / 50.0 * 20.0 / 48.0 * 16.0 / 48.0);
//		rightEncoder.setDistancePerPulse(360.0 / 256.0
//				* 20.0 / 50.0 * 20.0 / 48.0 * 16.0 / 48.0);
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
		encoderDrivePID = this.getPIDController();
		encoderDrivePID.setAbsoluteTolerance(PIDConstants.PID_ABSOLUTE_TOLERANCE);
		encoderDrivePID.setOutputRange(-.65, .65);
		
		
	}
	public void autoShift(){
		//have a deadzone of no change
		if(shiftState){
			shift(true);
			timeUp.stop();
			timeUp.reset();
			time.start();
		}else{
			shift(false);
			timeUp.start();
			time.stop();
			time.reset();
		}
		if(((Math.abs(leftEncoder.getRate()) + Math.abs(rightEncoder.getRate())) < RobotMap.Drivetrain.MAX_LOW_SPEED*1.85)
				&&((Math.abs(leftEncoder.getRate()) + Math.abs(rightEncoder.getRate())) > RobotMap.Drivetrain.MAX_LOW_SPEED*1.8)){
			
		}else if(((Math.abs(leftEncoder.getRate()) + Math.abs(rightEncoder.getRate())) > RobotMap.Drivetrain.MAX_LOW_SPEED*1.75)&&timeUp.get()>0.1){
			shiftState = true;		
		}else if(time.get() > 0.1){
			shiftState = false;
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
        motors[2].set(-left);
        motors[3].set(-left);
       
        
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
	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}
}

