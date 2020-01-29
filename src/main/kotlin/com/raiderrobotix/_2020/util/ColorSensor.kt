package com.raiderrobotix._2020.util

import com.revrobotics.ColorSensorV3
import edu.wpi.first.wpilibj.I2C
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj.util.Color
import org.team2471.frc.lib.coroutines.periodic
import kotlin.math.hypot

val sensor = ColorSensorV3(I2C.Port.kOnboard)

suspend fun printColor() = periodic {
	SmartDashboard.putString("Color", sensor.color.toString())
}

enum class WheelColor(val color: Color) {
	Red(Color(1.0, 0.0, 0.0)),
	Green(Color(0.0, 1.0, 0.0)),
	Cyan(Color(0.0, 1.0, 1.0)),
	Yellow(Color(1.0, 1.0, 0.0));
	
	companion object {
		val color: WheelColor
			get() {
				val color = sensor.color
				return values().toList().minBy {
					hypot(hypot((color.green - it.color.green), (color.red - it.color.red)), (color.blue - it.color.blue))
				}!!
			}
	}
}
