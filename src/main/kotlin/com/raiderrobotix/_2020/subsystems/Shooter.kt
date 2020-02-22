package com.raiderrobotix._2020.subsystems

import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.AnalogPotentiometer
import edu.wpi.first.wpilibj.Spark
import edu.wpi.first.wpilibj.SpeedControllerGroup
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.Subsystem
import com.raiderrobotix._2020.OperatorInterface.operator
import com.raiderrobotix._2020.OperatorInterface.get

object Shooter : Subsystem("Shooter") {
	private const val topChannel = 1
	private const val bottomChannel = 0
	private const val cowlChannel = 7
	
	private val cowl = Spark(cowlChannel)
	private val cowlEncoder = Encoder(9, 8) // TODO
	val potentiometer = AnalogPotentiometer(1)

	operator fun AnalogPotentiometer.invoke() = this.get()
	private val group = SpeedControllerGroup(Spark(topChannel), Spark(bottomChannel))

	private val safeRange = 0.04..0.8

	init {
//		cowlEncoder.distancePerPulse = 0.0 / 44.4 // TODO, in inches
		cowlEncoder.reset()
	}
	
	var speed: Double
		set(it) {
			group.set(it)
		}
		get() = group.get()
	
	var cowlSpeed: Double
		set(value) {
			cowl.speed = when {
				operator[4] ||
						potentiometer() in safeRange ||
						potentiometer() > safeRange.endInclusive && value < 0 ||
						potentiometer() < safeRange.start && value > 0
				-> value
				else -> 0.0
			}
		}
		get() = cowl.speed
	
	val cowlDistance get() = cowlEncoder.distance

	override fun reset() {
		speed = 0.0
		cowlSpeed = 0.0
	}
	
	override suspend fun default() {
		periodic {
			SmartDashboard.putNumber("Cowl Distance", cowlDistance)
			SmartDashboard.putNumber("Potent Distance", potentiometer.get())
		}
	}
}