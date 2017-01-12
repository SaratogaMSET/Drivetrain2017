package org.usfirst.frc.team649.robot;

import org.usfirst.frc.team649.robot.OI.Driver;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public Joystick operatorJoystick;
	public Joystick driveJoystickHorizontal;
	public Joystick driveJoystickVertical;
	
	public Driver driver;
	public Operator operator;
	
	public OI() {
		operatorJoystick = new Joystick(RobotMap.OPERATOR_JOYSTICK);
		driveJoystickHorizontal = new Joystick(RobotMap.DRIVE_JOYSTICK_HORIZONTAL);
		driveJoystickVertical = new Joystick(RobotMap.DRIVE_JOYSTICK_VERTICAL);
		driver = new Driver();
		operator = new Operator();
	}
	

		public class Operator {
			public boolean shootPressed() {
				return operatorJoystick.getRawButton(1);
			}
		}
	
	    public class Driver {

			public double getForward() {
				// TODO Auto-generated method stub
				if(driveJoystickVertical.getY() >= 0.05 || driveJoystickVertical.getY() <= -0.05){
					return -driveJoystickVertical.getY();
				}else{
					return 0.0;
				}
				
			}
	    	
			
			public double getRotation() {
				if(driveJoystickHorizontal.getX() >= 0.05 || driveJoystickHorizontal.getX() <= -0.05){
					return driveJoystickHorizontal.getX();
				}else{
					return 0.0;
				}
			}
			public Boolean shift(){
				return driveJoystickVertical.getRawButton(1) || driveJoystickHorizontal.getRawButton(1);
			}

			
		
	    }
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

