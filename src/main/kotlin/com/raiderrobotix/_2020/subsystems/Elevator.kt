package com.raiderrobotix._2020.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import org.team2471.frc.lib.framework.Subsystem

object Elevator : Subsystem("Elevator") {
	val leftid = 0
	val rightid = 0
	val left = CANSparkMax(leftid, CANSparkMaxLowLevel.MotorType.kBrushless)
	
	
	val right = CANSparkMax(rightid, CANSparkMaxLowLevel.MotorType.kBrushless)
	
	init {
		left.follow(right, true)
	}
	
	var speed: Double
		set(it) = right.set(it)
		get() = right.get()
	
	override fun reset() {
		speed = 0.0
	}
}