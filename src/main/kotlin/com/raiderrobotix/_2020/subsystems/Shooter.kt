package com.raiderrobotix._2020.subsystems

import edu.wpi.first.wpilibj.Spark
import org.team2471.frc.lib.framework.Subsystem

object Shooter : Subsystem("Shooter") {
	private const val topChannel = 0 // TODO
	private const val bottomChannel = 1 // TODO
	
	private val top = Spark(topChannel)
	private val bottom = Spark(bottomChannel)
	
	init {
		bottom.inverted = true
	}
	
	var speed: Double
		set(it) {
			top.set(it)
			bottom.set(it)
		}
		get() = (top.get() + bottom.get()) / 2
	
}