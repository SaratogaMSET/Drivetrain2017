package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class LeftDTPID extends PIDSubsystem {

public PIDController encoderDriveLeftPID;
    
    
    public LeftDTPID() {
    	super("DT Left", 0.6, 0, 0.05);
       	
    	encoderDriveLeftPID = this.getPIDController();
    	encoderDriveLeftPID.setAbsoluteTolerance(0.8);
    	
    	encoderDriveLeftPID.setOutputRange(-0.7, 0.7);
        
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	@Override
	protected double returnPIDInput() {
		return Robot.drive.getDistanceDTLeft();
	}

	@Override
	protected void usePIDOutput(double output) {
        Robot.drive.motors[2].set(output);
        Robot.drive.motors[3].set(output);

	}

    @Override
	public void initDefaultCommand() {
    }
}



