package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TurretHead extends Subsystem {

	Victor turretMotor;
	double rotationValue;
	double turretMotorPower;

	public TurretHead() {
		turretMotor = new Victor(RobotMap.GyroShooter.GYRO_TURRET_MOTOR_PORT);
	}

	public void rotByDegreeAtSpeed(double rotValue, double speedOfMotor) {
		rotationValue = rotValue;
		turretMotorPower = speedOfMotor;
		if (rotationValue > 180) {
			for (double rot1 = rotationValue; rot1 >= 180; rot1--) {
				turretMotor.set(-1);
			}
		}
		if (rotationValue < 180) {
			for (double rot2 = rotationValue; rot2 <= 180; rot2++) {
				turretMotor.set(1);
			}
		} else {
			turretMotor.set(0);
		}
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
