package com.raiderrobotix._2020.subsystems

import com.raiderrobotix._2020.OperatorInterface
import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.Subsystem


object Elevator : Subsystem("Elevator") {
	
	private val left = CANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushless)
	private val right = CANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushless)
	
	private val left_encoder = left.encoder
	private val right_encoder = right.encoder
	
	init {
		left.follow(right, true)
		left_encoder.position = 0.0
		right_encoder.position = 0.0
	}
	
	private val height get() = (left_encoder.position + right_encoder.position) / 2
	
	var speed: Double
		set(new_speed) {
			right.set(new_speed)
		}
		get() = right.get()
	
	override fun reset() {
		speed = 0.0
	}
	
	override suspend fun default() {
		periodic {
			SmartDashboard.putString("Current", "Left: ${left.outputCurrent}, Right: ${right.outputCurrent}")
			speed = -OperatorInterface.operatorY
		}
	}
}
