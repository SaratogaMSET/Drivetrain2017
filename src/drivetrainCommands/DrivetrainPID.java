package drivetrainCommands;

import org.usfirst.frc.team649.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainPID extends Command {
	
	double distance;
	double tolerance = 1.0;
	public PIDController drivePID;

	double setpoint;
	
    public DrivetrainPID(double distance) {
    	requires(Robot.drive);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	drivePID = Robot.drive.getPIDController();
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drivePID.enable();

    	double setpoint = distance + Robot.drive.getPosition();
    	drivePID.setSetpoint(setpoint);

    	//drivePIDRight.enable();
//    	Robot.isPIDActive = true;
//    	double setpoint = Robot.drivetrain.getPosition() + distance;
//    	drivePID.setSetpoint(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {


        return drivePID.onTarget() || Robot.drive.isOnTarget(distance);

    }

    // Called once after isFinished returns true
    protected void end() {

    	drivePID.disable();
    	Robot.drive.rawDrive(0, 0);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    	end();
    }
}
