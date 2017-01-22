package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.RobotMap.Drivetrain;
import org.usfirst.frc.team649.robot.RobotMap.GyroShooter;
import org.usfirst.frc.team649.robot.RobotMap.Shooter;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class TurretGyro extends Subsystem {

	public Drivetrain drivetrain;
	public Shooter shooter;
	public GyroShooter gyrosub;
	public DrivetrainSubsystem dtsub;
	public ShooterSubsystem shootsub;
	public Gyro gyro;
	public double angleOfBoiler = 0;

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public TurretGyro() {
		gyro = new ADXRS450_Gyro();
		gyro.calibrate();

	}

	public void FaceBoiler() {//horiz angle
		for (double gameTime = Timer.getMatchTime(); gameTime >= 0; gameTime--) {
			if (gyro.getAngle() < angleOfBoiler) {
				for (double g = 360; g <= angleOfBoiler; g--) {
						//shootsub.
					}
			}
		}
	}

	public void AngleShooter() {//vert angle

	}
	public double getCurGyroAngle()
	{
		return gyro.getAngle();
	}

}
