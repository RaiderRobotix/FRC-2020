package com.raiderrobotix._2020.subsystems

import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.Spark
import edu.wpi.first.wpilibj.SpeedControllerGroup
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.Subsystem
import kotlin.math.acos
import kotlin.math.pow

object Shooter : Subsystem("Shooter") {
	private const val topChannel = 1 // TODO
	private const val bottomChannel = 0 // TODO
	private const val cowlChannel = 8 // TODO
	
	private val cowl = Spark(cowlChannel)
	private val cowlEncoder = Encoder(0, 1) // TODO
	private val group = SpeedControllerGroup(Spark(topChannel), Spark(bottomChannel))
	
	init {
//		cowlEncoder.distancePerPulse = 0.0 // TODO, in inches
		cowlEncoder.reset()
	}
	
	var speed: Double
		set(it) {
			group.set(it)
		}
		get() = group.get()
	
	var cowlSpeed: Double
		set(value) {
			cowl.speed = value
		}
		get() = cowl.speed
	
	val cowlDistance get() = cowlEncoder.distance
	
	override fun reset() {
		speed = 0.0
		cowlSpeed = 0.0
	}
	
	const val motor_to_pivot = 10.0
	const val anchor_to_pivot = 8.25
	val cowlAngle
		get() = acos((cowlDistance.pow(2) - motor_to_pivot.pow(2) - anchor_to_pivot.pow(2)) / (2 * motor_to_pivot * anchor_to_pivot))
	
}

suspend fun printDistance() {
	periodic {
		SmartDashboard.putNumber("Cowl Distance", Shooter.cowlDistance)
	}
}