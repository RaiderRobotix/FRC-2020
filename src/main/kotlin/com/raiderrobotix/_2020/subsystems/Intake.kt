package com.raiderrobotix._2020.subsystems

import edu.wpi.first.wpilibj.AnalogInput
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
	
	object ultrasound : AnalogInput(0) {
		private var scaling: Double = 100.0
		operator fun invoke() = voltage * scaling
	}
	
	override suspend fun default() {
		periodic(0.05) {
			SmartDashboard.putNumber("Ultrasound", ultrasound())
		}
	}
	
}