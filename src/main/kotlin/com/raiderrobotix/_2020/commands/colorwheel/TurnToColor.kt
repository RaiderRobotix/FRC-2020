package com.raiderrobotix._2020.commands.colorwheel

import com.raiderrobotix._2020.subsystems.ColorWheel
import com.raiderrobotix._2020.subsystems.ColorWheel.WheelColor
import com.raiderrobotix._2020.subsystems.ColorWheel.color
import edu.wpi.first.wpilibj.DriverStation
import org.team2471.frc.lib.coroutines.delay
import org.team2471.frc.lib.coroutines.suspendUntil
import org.team2471.frc.lib.framework.use

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
	use(ColorWheel) {
		ColorWheel.wheel.speed = 0.5
		suspendUntil { color == endColor }
		ColorWheel.wheel.speed = -0.1
		delay(1.0)
		ColorWheel.wheel.speed = 0.0
	}
	
}

suspend fun testPositionControl(endColor: WheelColor) {
	use(ColorWheel) {
		ColorWheel.wheel.speed = 0.5
		suspendUntil { color == endColor }
		ColorWheel.wheel.speed = 0.0
		delay(1.0)
		ColorWheel.wheel.speed = -0.3
		delay(100.0)
		println("ahahahaha")
	}
}
