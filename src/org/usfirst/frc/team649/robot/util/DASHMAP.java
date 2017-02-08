package org.usfirst.frc.team649.robot.util;

import java.util.HashMap;

// Distance Angle Speed Hash MAP
public class DASHMAP {
	public class SpAn {
		public double speed;
		public double angle;
		
		public SpAn (double speed, double angle) {
			this.speed = speed;
			this.angle = angle;
		}
		
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
		
		hashmap.put(3.5, new SpAn(10, 10));
		hashmap.put(10.0, new SpAn(10, 10));
		hashmap.put(15.0, new SpAn(10, 10));
		hashmap.put(25.0, new SpAn(10, 10));
	}
}
