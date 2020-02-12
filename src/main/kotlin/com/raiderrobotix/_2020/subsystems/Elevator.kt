package com.raiderrobotix._2020.subsystems

import com.raiderrobotix._2020.OperatorInterface
import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import org.team2471.frc.lib.framework.Subsystem

object Elevator : Subsystem("Elevator") {
	private const val left_id = 6
	private const val right_id = 5
	
	private val left = CANSparkMax(left_id, CANSparkMaxLowLevel.MotorType.kBrushless)
	private val right = CANSparkMax(right_id, CANSparkMaxLowLevel.MotorType.kBrushless)

	private val left_encoder = left.encoder
	private val right_encoder = right.encoder

	init {
		left.follow(right, true)
		left_encoder.position = 0.0
		right_encoder.position = 0.0
	}
	private const val max_height = 10000 // Todo

	private val height get() = (left_encoder.position + right_encoder.position) / 2

	var speed: Double
		set(new_speed) {
			right.set(when {
				max_height > height -> new_speed
				new_speed < 0.0 -> new_speed
				else -> 0.0
			})
		}
		get() = right.get()

	override fun reset() {
		speed = 0.0
	}

	override suspend fun default() {
		speed = OperatorInterface.operatorY
	}
}
