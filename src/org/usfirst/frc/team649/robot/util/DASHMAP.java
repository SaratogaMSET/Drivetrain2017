package org.usfirst.frc.team649.robot.util;

import java.util.HashMap;

// Distance Angle Speed Hash MAP
public class DASHMAP {
	public class SpAn {
		public double speed;
		public double angle;
		
		public boolean equals (SpAn other) {
			return (speed == other.speed && angle == other.angle);
		}
		
		public String toString () {
			return "Speed: " + speed + ", Angle: " + angle;
		}
	}
	
	// Hashmap with distance and values
	public HashMap<Double, SpAn> hashmap;
	
	public DASHMAP () {
		hashmap = new HashMap<Double, SpAn>();
	}
}
