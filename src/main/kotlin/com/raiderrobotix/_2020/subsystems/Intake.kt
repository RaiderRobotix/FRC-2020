package com.raiderrobotix._2020.subsystems

import edu.wpi.first.wpilibj.Counter
import edu.wpi.first.wpilibj.Spark
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.Subsystem
import org.team2471.frc.lib.input.whenTrue


object Intake : Subsystem("Shooter") {
	private const val topChannel = 2
	private const val bottomChannel = 3
	private const val outerChannel = 4
	
	private val upper = Spark(topChannel)
	private val lower = Spark(bottomChannel)
	internal val outer = Spark(outerChannel)
	
	object LineBreaker {
		private val counter = Counter(1)
		var count = 0
		operator fun invoke() = counter.get()
	}
	
	init {
		lower.inverted = true
		({ LineBreaker.count != LineBreaker() }).whenTrue {
			LineBreaker.count = LineBreaker()
			// action
		}
	}
	
	var speed: Double
		set(it) {
			upper.set(it)
			lower.set(it)
		}
		get() = (upper.get() + lower.get()) / 2
	
	override fun reset() {
		speed = 0.0
		outer.speed = 0.0
	}
	
	override suspend fun default() {
		periodic {
			SmartDashboard.putNumber("LineBreaker", LineBreaker().toDouble())
		}
	}
	
}