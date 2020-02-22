package com.raiderrobotix._2020.commands.colorwheel

import com.raiderrobotix._2020.subsystems.ColorWheel
import com.raiderrobotix._2020.subsystems.ColorWheel.WheelColor
import com.raiderrobotix._2020.subsystems.ColorWheel.color
import edu.wpi.first.wpilibj.DriverStation
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.use

suspend fun positionControl() {
	val endColor = when (
		try {
			DriverStation.getInstance().gameSpecificMessage[0] // Get first character
		} catch (e: Throwable) {
			'\u0000' // If string is null or empty, default to this
		}.toLowerCase()) {
		'r' -> WheelColor.Red
		'b' -> WheelColor.Cyan
		'g' -> WheelColor.Green
		'y' -> WheelColor.Yellow
		else -> {
			println("Color not received from Driver Station")
			return
		}
	}
	use(ColorWheel) {
		periodic {
			if (color != endColor) {
				ColorWheel.wheel.speed = 0.5
			} else stop()
		}
	}
	
}
