package com.raiderrobotix._2020.subsystems

import edu.wpi.first.wpilibj.Spark
import org.team2471.frc.lib.framework.Subsystem

object Trolley : Subsystem("Trolley"), SensorOutput {
	private const val id = 5
	
	private val slide = Spark(id)
	
	var speed: Double
		set(it) = slide.set(it)
		get() = slide.get()
	
	override fun reset() {
		speed = 0.0
	}
	
	override suspend fun update() {
	}
}
