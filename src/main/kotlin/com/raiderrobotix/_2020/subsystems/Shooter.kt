package com.raiderrobotix._2020.subsystems

import edu.wpi.first.wpilibj.Spark
import org.team2471.frc.lib.framework.Subsystem

object Shooter : Subsystem("Shooter") {
	private const val topChannel = 1
	private const val bottomChannel = 0
	
	private val top = Spark(topChannel)
	private val bottom = Spark(bottomChannel)
	
	
	var speed: Double
		set(it) {
			top.set(it)
			bottom.set(it)
		}
		get() = (top.get() + bottom.get()) / 2
	
}