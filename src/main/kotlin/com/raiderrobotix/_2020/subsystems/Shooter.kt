package com.raiderrobotix._2020.subsystems

import com.raiderrobotix._2020.OperatorInterface.get
import com.raiderrobotix._2020.OperatorInterface.operator
import edu.wpi.first.wpilibj.AnalogInput
import edu.wpi.first.wpilibj.AnalogPotentiometer
import edu.wpi.first.wpilibj.Spark
import edu.wpi.first.wpilibj.SpeedControllerGroup
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.Subsystem

object Shooter : Subsystem("Shooter") {
	private const val topChannel = 1
	private const val bottomChannel = 0

	private val cowl = Spark(7)

	object Potentiometer : AnalogPotentiometer(1) {
		operator fun invoke() = this.get()
	}
	
	object Ultrasound : AnalogInput(0) {
		private var scaling: Double = 100.0
		operator fun invoke() = voltage * scaling
	}
	
	private val group = SpeedControllerGroup(Spark(topChannel), Spark(bottomChannel))
	
	private val safeRange = 0.02..0.9
	
	var speed: Double
		set(it) {
			group.set(it)
		}
		get() = group.get()
	
	var cowlSpeed: Double
		set(value) {
			cowl.speed = when {
				operator[4] ||
						Potentiometer() in safeRange ||
						Potentiometer() > safeRange.endInclusive && value < 0 ||
						Potentiometer() < safeRange.start && value > 0
				-> value
				else -> 0.0
			}
		}
		get() = cowl.speed

	override fun reset() {
		speed = 0.0
		cowlSpeed = 0.0
	}

	override suspend fun default() {
		periodic {
			SmartDashboard.putNumber("Potent Distance", Potentiometer())
			SmartDashboard.putNumber("Ultrasound", Ultrasound())
		}
	}
}