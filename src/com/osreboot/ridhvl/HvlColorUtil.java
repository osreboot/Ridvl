package com.osreboot.ridhvl;

import org.newdawn.slick.Color;

public class HvlColorUtil {
	public static Color lerpColor(Color min, Color max, float lerp)
	{
		return new Color(min.r + ((max.r - min.r) * lerp),
				min.g + ((max.g - min.g) * lerp),
				min.b + ((max.b - min.b) * lerp),
				min.a + ((max.a - min.a) * lerp));
	}
}