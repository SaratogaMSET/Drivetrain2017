package org.usfirst.frc.team649.robot;


import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team649.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team649.robot.subsystems.TurretSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static DrivetrainSubsystem drive;
	OI oi;
	public static ShooterSubsystem shoot;
	public String shootState;
	public static TurretSubsystem turret;
	
	
    public void robotInit() {
    	drive = new DrivetrainSubsystem();
    	shoot = new ShooterSubsystem();
    	turret = new TurretSubsystem();
    	oi = new OI();
    	shootState = "low";

    }
	
	


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */

    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
	}

	
    public void autonomousInit() {
     
	} 
    	
    	
    



    
    

    public void teleopInit() {
    

    }
		
    public void teleopPeriodic() {
    	String solVal = drive.driveSol.get().toString();
    	SmartDashboard.putString("sol value", solVal);
    	SmartDashboard.putData("left einstein", shoot.leftEinstein);
    	SmartDashboard.putData("right einstein", shoot.rightEinstein);
    	SmartDashboard.putNumber("angle of hood shot",turret.hoodServo.getAngle());
    	drive.autoShift();
    	turret.rotate(oi.operator.getRot());
//    	if(oi.driver.shift()){
//    		drive.shift(true);
//    	}else{
//    		drive.shift(false);
//    	}
    	if(oi.operator.angleShotTowardsBackPressed())
    	{
    		turret.angleOfShooter = turret.hoodServo.getAngle() - 1;
    		//turret.angleShooter(turret.angleOfShooter);
    	}
    	if(oi.operator.angleShotTowardsBackPressed())
    	{
    		turret.angleOfShooter = turret.hoodServo.getAngle() + 1;
    		//turret.angleShooter(turret.angleOfShooter);
    	}
    	if(oi.operator.lowPowerStatePressed()){
    		shootState = "low";
    	}
    	if(oi.operator.middlePowerStatePressed()){
    		shootState = "mid";
    	}
    	if(oi.operator.maxPowerstatePressed()){
    		shootState = "high";
    	}
    	if(oi.operator.shootPressed()){
    		switch(shootState){
    		case "low":
    			shoot.shoot(RobotMap.Shooter.LEFT_SHOOTER_POWER_MIN, RobotMap.Shooter.RIGHT_SHOOTER_POWER_MIN);
    			break;
    		case "mid":
    			shoot.shoot(RobotMap.Shooter.LEFT_SHOOTER_POWER_MID, RobotMap.Shooter.RIGHT_SHOOTER_POWER_MID);
    			break;
    		case "high":
    			shoot.shoot(RobotMap.Shooter.LEFT_SHOOTER_POWER_MAX, RobotMap.Shooter.RIGHT_SHOOTER_POWER_MAX);
    			break;
    		}
    		
    	}
    	else{
    		shoot.shoot(0.0, 0.0);
    	}
    	
    	drive.spikey();
    	drive.driveFwdRot(oi.driver.getForward(), oi.driver.getRotation());

    	
    }

    public void testPeriodic() {

    }
}
