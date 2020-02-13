package com.raiderrobotix._2020.subsystems

import edu.wpi.first.wpilibj.Spark
import org.team2471.frc.lib.framework.Subsystem

<<<<<<< HEAD
object Intake : Subsystem("Shooter") {
	private const val topChannel = 2
	private const val bottomChannel = 3
	private const val outerChannel = 4
	
	internal val upper = Spark(topChannel)
	internal val lower = Spark(bottomChannel)
	internal val outer = Spark(outerChannel)
=======
object Intake : Subsystem("Intake") {
	private const val topChannel = 3
	private const val bottomChannel = 2
	
	private val upper = Spark(topChannel)
	private val lower = Spark(bottomChannel)
>>>>>>> 3698b0059430f49d049418e8e80966739555c593
	
	init {
		lower.inverted = true
	}
	
	var speed: Double
		set(it) {
			upper.set(it)
			lower.set(it)
		}
		get() = (upper.get() + lower.get()) / 2
	
<<<<<<< HEAD
	override fun reset() {
		speed = 0.0
		outer.speed = 0.0
	}
	
=======
>>>>>>> 3698b0059430f49d049418e8e80966739555c593
}