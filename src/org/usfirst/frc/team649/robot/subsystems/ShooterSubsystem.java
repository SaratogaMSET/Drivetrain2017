package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
	Victor leftMotor;
	Victor rightMotor;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public ShooterSubsystem(){
		leftMotor = new Victor(RobotMap.Shooter.LEFT_SHOOTER_VICTOR);
		rightMotor = new Victor(RobotMap.Shooter.RIGHT_SHOOTER_VICTOR);
	}
	public void shoot(double leftRatio, double rightRatio){
		leftMotor.set(-leftRatio);
		rightMotor.set(-rightRatio);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

