package org.usfirst.frc.team649.robot.commands.drivetrain;

import org.usfirst.frc.team649.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetDriveSol extends Command {
	public Timer time;
	public DoubleSolenoid driveSol;
	boolean isHigh;
    public SetDriveSol(boolean high) {
    	time = new Timer();
    	driveSol  = new DoubleSolenoid(RobotMap.Drivetrain.DRIVE_SOL[0],
				RobotMap.Drivetrain.DRIVE_SOL[1]);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	isHigh = high;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	driveSol.set(isHigh ? DoubleSolenoid.Value.kReverse : DoubleSolenoid.Value.kForward); 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(time.get()>0.5){
            return true;
    	}else{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveSol.free();
    	time.stop();
    	time.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
