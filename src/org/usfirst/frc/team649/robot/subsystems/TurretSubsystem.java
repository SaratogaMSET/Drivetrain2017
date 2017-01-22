package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TurretSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Victor turretMotor;
	public Encoder turretShaftEncoder;
	
	public TurretSubsystem(){
		turretMotor = new Victor(RobotMap.Turret.PIVOT_VICTOR);
		turretShaftEncoder = new Encoder(RobotMap.Turret.PIVOT_SHAFT_ENCODER[0],RobotMap.Turret.PIVOT_SHAFT_ENCODER[1]);
		
	}
	public void rotate(double power){
		turretMotor.set(power);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

