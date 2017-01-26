package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.RobotMap;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
	Victor leftMotor;
	Victor rightMotor;
	public Counter leftEinstein, rightEinstein;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public ShooterSubsystem(){
		leftEinstein = new Counter(RobotMap.Shooter.LEFT_ENISTEIN_PORT);
		rightEinstein = new Counter(RobotMap.Shooter.RIGHT_ENISTEIN_PORT);
		leftMotor = new Victor(RobotMap.Shooter.LEFT_SHOOTER_VICTOR);
		rightMotor = new Victor(RobotMap.Shooter.RIGHT_SHOOTER_VICTOR);
	}
	public void shoot(double leftRatio, double rightRatio){
		leftMotor.set(-leftRatio);
		rightMotor.set(rightRatio);
	}
	public double leftCount(){
		return 60/leftEinstein.getPeriod();
	}
	public double rightCount(){
		return 60/rightEinstein.getPeriod();
	}
	public void setPowerLeft(double power){
		leftMotor.set(-power);
	}
	public void setPowerRight(double power){
		rightMotor.set(power);
	}
	public void targetRPMFlywheels(double minPowerLeft, double minPowerRight, double maxPowerLeft, double maxPowerRight, double targetRPMLeft, double targetRPMRight){
		if(leftCount()<targetRPMLeft){
			setPowerLeft(maxPowerLeft);
		}else{
			setPowerRight(minPowerLeft);
		}
		if(rightCount()<targetRPMRight){
			setPowerRight(maxPowerRight);
		}else{
			setPowerRight(minPowerRight);
		}
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
}

