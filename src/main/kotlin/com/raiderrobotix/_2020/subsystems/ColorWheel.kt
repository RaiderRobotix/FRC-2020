package com.raiderrobotix._2020.subsystems

import edu.wpi.first.wpilibj.Spark
import org.team2471.frc.lib.framework.Subsystem

object ColorWheel : Subsystem("ColorWheel") {
	private const val channel = 6
	internal val wheel = Spark(6)
	
	override fun reset() {
		wheel.set(0.0)
	}
	
}