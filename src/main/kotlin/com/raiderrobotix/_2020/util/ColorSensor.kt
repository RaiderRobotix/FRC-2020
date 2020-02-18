package com.raiderrobotix._2020.util

import com.revrobotics.ColorSensorV3
import edu.wpi.first.wpilibj.I2C
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj.util.Color
import org.team2471.frc.lib.coroutines.periodic
import kotlin.math.hypot

val sensor = ColorSensorV3(I2C.Port.kOnboard)

suspend fun printColor() = periodic(0.02) {
	SmartDashboard.putString("Color", WheelColor.color.name)
	SmartDashboard.putString("Color", WheelColor.color.name)
	val color = sensor.color
	println("color loops")
	SmartDashboard.putString("Raw Color", "(${color.red}, ${color.green}, ${color.blue})")
}

enum class WheelColor(val color: Color) {
	Red(Color(1.0, 0.0, 0.0)),
	Green(Color(0.0, 1.0, 0.0)),
	Cyan(Color(0.0, 1.0, 1.0)),
	Yellow(Color(1.0, 1.0, 0.0));
	
	companion object {
		
		private val chooser = SendableChooser<WheelColor>()
		
		init {
			for (color in values()) {
				chooser.addOption(color.name, color)
			}
			SmartDashboard.putData("Selected Color", chooser)
		}
		
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

