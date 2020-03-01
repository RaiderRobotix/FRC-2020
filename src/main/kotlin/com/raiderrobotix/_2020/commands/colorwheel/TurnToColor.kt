package com.raiderrobotix._2020.commands.colorwheel

import com.raiderrobotix._2020.subsystems.ColorWheel
import com.raiderrobotix._2020.subsystems.ColorWheel.WheelColor
import com.raiderrobotix._2020.subsystems.ColorWheel.color
import edu.wpi.first.wpilibj.DriverStation
import kotlinx.coroutines.delay
import org.team2471.frc.lib.coroutines.suspendUntil

suspend fun positionControl() {
	val endColor = when (
		try {
			DriverStation.getInstance().gameSpecificMessage[0] // Get first character
		} catch (e: Throwable) {
			error("Color not received from Driver Station")
		}) {
		'R' -> WheelColor.Red
		'B' -> WheelColor.Cyan
		'G' -> WheelColor.Green
		'Y' -> WheelColor.Yellow
		else -> error("Color not received from Driver Station")
	}
	ColorWheel.wheel.speed = 0.4
	suspendUntil { color == endColor }
	ColorWheel.wheel.speed = -0.35
	suspendUntil { color == endColor }
	delay(500)
	ColorWheel.wheel.speed = 0.0
}



