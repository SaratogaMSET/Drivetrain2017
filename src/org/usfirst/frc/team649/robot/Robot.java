
package org.usfirst.frc.team649.robot;

import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team649.robot.subsystems.HoodSubsystem;
import org.usfirst.frc.team649.robot.subsystems.IntakeSubsytem;
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
	public TurretSubsystem turret;
	public String shootState;
	public IntakeSubsytem intake;
	public HoodSubsystem hood;

	public void robotInit() {
		drive = new DrivetrainSubsystem();
		shoot = new ShooterSubsystem();
		turret = new TurretSubsystem();
//		hood = new HoodSubsystem();
		intake = new IntakeSubsytem();
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

	public void disabledInit() {

	}

	public void disabledPeriodic() {
	}

	public void autonomousInit() {

	}

	public void teleopInit() {

		shoot.resetEins();

	}

	public void teleopPeriodic() {
		// String solVal = drive.driveSol.get().toString();
		 SmartDashboard.putNumber("LEFT RPM", shoot.leftCount());
		 SmartDashboard.putNumber("RIGHT RPM", shoot.rightCount());
		 SmartDashboard.putNumber("Left Count", shoot.leftGet());
		 SmartDashboard.putNumber("Right Count", shoot.rightGet());
		 SmartDashboard.putNumber("Slider Value", oi.operator.getSlider());
		 SmartDashboard.putNumber("Intake Speed", intake.intakeMotor.getSpeed());
		 SmartDashboard.putString("Intake State", intake.intakeState());
		 SmartDashboard.putNumber("Turret Speed", turret.getTurretSpeed());
		// SmartDashboard.putString("sol value", solVal);
		// SmartDashboard.putBoolean("isAutoShiftTrue", drive.isAutoShiftTrue);
		// SmartDashboard.putData("Right Drive",drive.rightEncoder);
		// SmartDashboard.putNumber("Right Encoder Rate",drive.rightEncoder.getRate());
		// SmartDashboard.putNumber("Left Encoder Rate",
		// drive.leftEncoder.getRate());
		// SmartDashboard.putData("Left Drive", drive.leftEncoder);
//		SmartDashboard.putNumber("Lidar", hood.getDistance());
		intake.setIntakeVictor(oi.operatorJoystick.getY());
		if (oi.driver.shiftDown()) {
			drive.shift(false);
		} else if (oi.driver.shiftUp()) {
			drive.shift(true);
		} else {
			drive.autoShift();
		}
		turret.rotate(oi.operator.getRot());
		if (oi.operator.runFeedPressed()) {
			shoot.runFeederMotor(RobotMap.Shooter.FEEDER_SPEED);
		} else {
			shoot.runFeederMotor(0);
		}
		// if(oi.driver.shift()){
		// drive.shift(true);
		// }else{
		// drive.shift(false);
		if(oi.operator.shootPressed()){
		shoot.targetRPMFlywheels(RobotMap.Shooter.MIN_POWER_LEFT, RobotMap.Shooter.MIN_POWER_RIGHT, RobotMap.Shooter.MAX_POWER_LEFT, RobotMap.Shooter.MAX_POWER_RIGHT, RobotMap.Shooter.WALL_TARGET_RPM, RobotMap.Shooter.WALL_TARGET_RPM);
//			 shoot.shoot(oi.operator.getSlider(), oi.operator.getSlider());
		}else{
			shoot.shoot(0.0, 0.0);
		}
//		if (oi.operator.shootPressed()) {
//			shoot.shoot(RobotMap.Shooter.LEFT_SHOOTER_POWER_MIN, RobotMap.Shooter.RIGHT_SHOOTER_POWER_MIN);
//
//		} else {
//			shoot.shoot(0.0, 0.0);
//		}
		drive.spikey();
		drive.driveFwdRot(oi.driver.getForward(), oi.driver.getRotation());

	}

	public void testPeriodic() {

	}
}
