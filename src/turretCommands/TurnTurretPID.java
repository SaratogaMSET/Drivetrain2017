package turretCommands;

import org.usfirst.frc.team649.robot.Robot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class TurnTurretPID extends Command {
	double angle;
	public PIDController turretPID;

    public TurnTurretPID(double angle) {
    	
//    	requires(Robot.turret);
    	turretPID = Robot.turret.getPIDController();

    	this.angle = angle;
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }


    // Called just before this Command runs the first time
    protected void initialize() {
    	turretPID.enable();
    	turretPID.setSetpoint(angle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return turretPID.onTarget() || Robot.turret.isOnTarget(angle);
    }

    // Called once after isFinished returns true
    protected void end() {
    	turretPID.disable();
    	Robot.turret.rotate(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
