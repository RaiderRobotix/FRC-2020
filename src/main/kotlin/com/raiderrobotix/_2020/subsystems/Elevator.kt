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
	
	init {
		left.follow(right, true)
	}
	
	var speed: Double
		set(it) = right.set(it)
		get() = right.get()
	
	override fun reset() {
		speed = 0.0
	}

	override suspend fun default() {
		speed = OperatorInterface.operatorY
	}
}
