package com.raiderrobotix._2020.subsystems

import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.AnalogPotentiometer
import edu.wpi.first.wpilibj.Spark
import edu.wpi.first.wpilibj.SpeedControllerGroup
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.Subsystem

object Shooter : Subsystem("Shooter") {
	private const val topChannel = 1
	private const val bottomChannel = 0
	private const val cowlChannel = 7
	
	private val cowl = Spark(cowlChannel)
	private val cowlEncoder = Encoder(9, 8) // TODO
	private val potentiometer = AnalogPotentiometer(1)
	private val group = SpeedControllerGroup(Spark(topChannel), Spark(bottomChannel))
	
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
			cowl.speed = value
		}
		get() = cowl.speed
	
	val cowlDistance get() = potentiometer.get()
	
	override fun reset() {
		speed = 0.0
		cowlSpeed = 0.0
	}
	
	suspend fun printCowlDistance() {
		periodic {
			SmartDashboard.putNumber("Cowl Distance", cowlDistance)
		}
	}
}