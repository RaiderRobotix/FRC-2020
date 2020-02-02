package com.raiderrobotix._2020.subsystems

import edu.wpi.first.wpilibj.Spark
import org.team2471.frc.lib.framework.Subsystem

object Intake : Subsystem("Shooter") {
	private const val topChannel = 2
	private const val bottomChannel = 3 // TODO
	
	private val upper = Spark(topChannel)
	private val lower = Spark(bottomChannel)
	
	init {
        lower.inverted = true
	}
	
	var speed: Double
		set(it) {
			upper.set(it)
			lower.set(it)
		}
		get() = (upper.get() + lower.get()) / 2
	
}