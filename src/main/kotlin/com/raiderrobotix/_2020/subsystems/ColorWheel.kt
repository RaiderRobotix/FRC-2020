package com.raiderrobotix._2020.subsystems

import com.revrobotics.ColorSensorV3
import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.I2C
import edu.wpi.first.wpilibj.Spark
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj.util.Color
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.Subsystem

object ColorWheel : Subsystem("ColorWheel") {
	private const val channel = 6
	internal val wheel = Spark(6)
	val sensor = ColorSensorV3(I2C.Port.kOnboard)
	var offset = Color(.0, .0, .0)
		private set

	val color: WheelColor?
		get() {
			val color = sensor.color - offset

			val threshold = 0.001

			return when {
				color.green >= threshold ->
					when {
						color.red >= threshold -> WheelColor.Yellow
						color.blue >= threshold -> WheelColor.Cyan
						else -> WheelColor.Green
					}
				color.red >= threshold -> WheelColor.Red
				else -> null
			}
		}

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

	override fun reset() {
		wheel.speed = 0.0
	}

	override suspend fun default() {
		zeroOutColor(iter = 20)
		periodic(0.05) {
			SmartDashboard.putString("Color", color?.name ?: "Nothing")
			SmartDashboard.putString("Raw Color", (sensor.color - offset).toPrettyString())
		}
	}

	enum class WheelColor {
		Red,
		Green,
		Cyan,
		Yellow
	}
}