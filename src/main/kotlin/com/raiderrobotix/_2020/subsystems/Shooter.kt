package com.raiderrobotix._2020.subsystems

import edu.wpi.first.wpilibj.Spark
import org.team2471.frc.lib.framework.Subsystem

object Shooter : Subsystem("Shooter") {
<<<<<<< HEAD
	private const val topChannel = 1 // TODO
	private const val bottomChannel = 0 // TODO
	
	private val top = Spark(topChannel)
	private val bottom = Spark(bottomChannel)
	
=======
	private const val topChannel = 1
	private const val bottomChannel = 0
	
	private val top = Spark(topChannel)
	private val bottom = Spark(bottomChannel)
		
>>>>>>> 3698b0059430f49d049418e8e80966739555c593
	var speed: Double
		set(it) {
			top.set(it)
			bottom.set(it)
		}
		get() = (top.get() + bottom.get()) / 2
	
	override fun reset() {
		speed = 0.0
	}
	
}