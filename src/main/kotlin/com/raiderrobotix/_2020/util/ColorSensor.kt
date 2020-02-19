package com.raiderrobotix._2020.util

import com.revrobotics.ColorSensorV3
import edu.wpi.first.wpilibj.I2C
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj.util.Color
import org.team2471.frc.lib.coroutines.delay
import org.team2471.frc.lib.coroutines.periodic

val sensor = ColorSensorV3(I2C.Port.kOnboard)

var offset = Color(.0, .0, .0)

operator fun Color.minus(c: Color) = Color(red - c.red, green - c.green, blue - c.blue)

fun Color.toPrettyString(): String = "(R: %.5f, G: %.5f, B: %.5f)".format(red, green, blue)

suspend fun zeroOutColor(iter: Int) {
	val colors = mutableListOf<Color>()
	var i = 0
	periodic {
		colors += sensor.color
		if (i==iter) stop()
		else i++
	}
	
	offset = Color(
		colors.map { it.red }.average(),
		colors.map { it.green }.average(),
		colors.map { it.blue }.average()
	)
}

suspend fun printColor() {
	zeroOutColor(iter = 20)
	periodic(0.05) {
		SmartDashboard.putString("Color", WheelColor.color?.name ?: "Nothing")
		SmartDashboard.putString("Raw Color", (sensor.color - offset).toPrettyString())
	}
}

enum class WheelColor(val color: Color) {
	Red(Color(1.0, 0.0, 0.0)),
	Green(Color(0.0, 1.0, 0.0)),
	Cyan(Color(0.0, 1.0, 1.0)),
	Yellow(Color(1.0, 1.0, 0.0));
	
	companion object {
		
		private val chooser = SendableChooser<WheelColor>()
		
		val selectedColor: WheelColor? = chooser.selected
		
		val color: WheelColor?
			get() {
				val color = sensor.color - offset
				
				val threshold = 0.001
				
				return when {
					color.green >= threshold ->
						when {
							color.red >= threshold -> Yellow
							color.blue >= threshold -> Cyan
							else -> Green
						}
					color.red >= threshold -> Red
					else -> null
				}
			}
	}
}

