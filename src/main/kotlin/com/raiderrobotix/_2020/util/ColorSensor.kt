package com.raiderrobotix._2020.util

import com.revrobotics.ColorSensorV3
import edu.wpi.first.wpilibj.I2C
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj.util.Color
import org.team2471.frc.lib.coroutines.delay
import org.team2471.frc.lib.coroutines.periodic
import kotlin.math.hypot

val sensor = ColorSensorV3(I2C.Port.kOnboard)

var offset = Color(.0, .0, .0)

operator fun Color.minus(c: Color) = Color(red - offset.red, green - offset.green, blue - offset.blue)

fun Color.toPrettyString(): String = "(R: ${red}, G: ${green}, B: ${blue})"

suspend fun zeroOutColor(iter: Int) {
	val colors = mutableListOf<Color>()
	repeat(iter) {
		colors += sensor.color
		delay(0.02)
	}
	val avgRed = colors.sumByDouble { it.red } / colors.size
	val avgGreen = colors.sumByDouble { it.green } / colors.size
	val avgBlue = colors.sumByDouble { it.blue } / colors.size
	
	offset = Color(avgRed, avgGreen, avgBlue)
}

suspend fun printColor() {
	zeroOutColor(iter = 20)
	periodic(0.05) {
		SmartDashboard.putString("Color", WheelColor.color.name)
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
		
		val color: WheelColor
			get() {
				val color = sensor.color
				val preempt = setOf(Red, Green).minBy {
					hypot(hypot((color.green - it.color.green), (color.red - it.color.red)), (color.blue - it.color.blue))
				}!!
				
				return when {
					preempt == Red && color.green >= 0.4 -> Yellow
					preempt == Green && color.blue >= 0.4 -> Cyan
					else -> preempt
				}
			}
	}
}

