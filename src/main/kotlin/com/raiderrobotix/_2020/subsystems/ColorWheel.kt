package com.raiderrobotix._2020.subsystems

import edu.wpi.first.wpilibj.Spark
import org.team2471.frc.lib.framework.Subsystem

object ColorWheel : Subsystem("ColorWheel") {
	private const val channel = 6
	internal val wheel = Spark(6)
    
	var speed: Double
		set(it) {
			wheel.set(it)
			
		}
		get() = (wheel.get())
	
	override fun reset() {
		speed = 0.0
	}
	
}