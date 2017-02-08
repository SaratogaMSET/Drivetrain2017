package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.RobotMap;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsytem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public TalonSRX intakeMotor;
	public IntakeSubsytem(){
		intakeMotor = new TalonSRX(RobotMap.Intake.INTAKE_VICTOR);
	}
	public void setIntakeVictor(double speed){
		intakeMotor.set(speed);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public String intakeState()
    {
    	if(intakeMotor.getSpeed()>0)
    	{
    		return "purging";
    	}
    	else if(intakeMotor.getSpeed()<0)
    	{
    		return "intaking";
    	}
    	else
    	{
    		return "null";
    	}
    }
}

