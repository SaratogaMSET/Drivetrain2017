package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.RobotMap;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HoodSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public I2C lidar;
	public Servo hoodServo;
	public double turretValues[][];
	
	public HoodSubsystem(){
		lidar = new I2C(I2C.Port.kMXP,RobotMap.Hood.LIDAR_I2C);
		//hoodServo = new Servo(RobotMap.Hood.HOOD_SERVO);
	}
	public int getDistance(){
		byte[] buffer; 
		buffer = new byte[2];

		lidar.write(0x00, 0x04);
		Timer.delay(0.04);
		lidar.read(0x8f, 2, buffer); 
		return (int)Integer.toUnsignedLong(buffer[0] << 8) + Byte.toUnsignedInt(buffer[1]);	
	}
	public void getHashValue(){
		//[0][] is servoValues
		//[1][] is turretSpeeds in RPM
		//[2][] is turretFlyMinSpeed
		//[3][] is turretFlyMaxSpeed
		turretValues[0][0] = 0.5;
		turretValues[1][0] = 2000;
		turretValues[2][0] = 0.2;
		turretValues[3][0] = 0.3;
	}
	public void setServo(){
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

