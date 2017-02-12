package org.usfirst.frc.team649.robot.commands.drivetrain;

import org.usfirst.frc.team649.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveForwardRotate extends Command {
	
	double fwd;
	double rot;
	
    public DriveForwardRotate(double fwd, double rot) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.fwd = fwd;
    	this.rot = rot;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.driveFwdRot(fwd, rot);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putBoolean("DriveFowardRotate Running", true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
