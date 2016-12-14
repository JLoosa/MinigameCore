package me.jrl1004.java.mgcore.utilities;

import java.util.Random;

public class MathUtils {

	private MathUtils () {}
	
	public static final Random random;
	
	static {
		random = new Random();
	}
	
	public static int inRange (int min, int max) {
		return min + random.nextInt(max);
	}
}
