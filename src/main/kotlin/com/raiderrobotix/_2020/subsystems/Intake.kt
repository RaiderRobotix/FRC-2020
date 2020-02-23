package com.raiderrobotix._2020.subsystems

import edu.wpi.first.wpilibj.Counter
import edu.wpi.first.wpilibj.DigitalInput
import edu.wpi.first.wpilibj.Spark
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.Subsystem


object Intake : Subsystem("Shooter") {
	private const val topChannel = 2
	private const val bottomChannel = 3
	private const val outerChannel = 4
	
	private val upper = Spark(topChannel)
	private val lower = Spark(bottomChannel)
	internal val outer = Spark(outerChannel)
	
	class Digi(val port: Int) {
		private val input = DigitalInput(port)
		private val counter = Counter(input)
		private var count = 0
		fun update(): Boolean {
			return if (count != invoke()) {
				count = invoke()
				true
			} else false
		}
		
		fun get() = input.get()
		operator fun invoke() = counter.get()
	}

//	val IntakeBreaker = Digi(1)
//
//	val StageBreaker = Digi(2)
	
	val ShooterBreaker = Digi(0)
	
	init {
		lower.inverted = true
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
			SmartDashboard.putBoolean("shooterBreaker", ShooterBreaker.get())
		}
	}

}