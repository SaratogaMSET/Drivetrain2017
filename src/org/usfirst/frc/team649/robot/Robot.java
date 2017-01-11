

package org.usfirst.frc.team649.robot;


import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem;

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
	


	
    public void robotInit() {
    	drive = new DrivetrainSubsystem();
    	oi = new OI();

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
//    	SmartDashboard.putBoolean("limit", drive.isPressed());
    	SmartDashboard.putData("leftDrive", drive.leftEncoder);
    	SmartDashboard.putData("rightDrive", drive.rightEncoder);
    	SmartDashboard.putBoolean("Shift", oi.driveJoystickVertical.getRawButton(1));
    	if(oi.driver.shift()){
    		drive.shift(true);
    	}else{
    		drive.shift(false);
    	}

    	drive.spikey();
    	drive.driveFwdRot(oi.driver.getForward(), oi.driver.getRotation());

    	
    }

    public void testPeriodic() {

    }
}
