package com.raiderrobotix._2020.commands.drivebase

import com.raiderrobotix._2020.commands.Command
import com.raiderrobotix._2020.subsystems.DriveBase
import kotlin.math.abs

class Drive(private val distance: Double, private val speed: Double) : Command() {
	
	companion object {
		const val ANGLE_TOLERANCE = 1.0 // TODO
		const val SPEED_CORRECTION = 0.10// TODO
		const val DISTANCE_TOLERANCE = 1.0// TODO
	}
	
	
	init {
		requires(DriveBase)
	}
	
	override fun initialize() {
		DriveBase.resetEncoders()
	}
	
	override fun execute() {
		var leftSpeed = speed
		var rightSpeed = speed
		
		if (abs(DriveBase.gyroAngle) > ANGLE_TOLERANCE) { // Adjust speeds for in case of veering
			if (DriveBase.gyroAngle > 0) { // Too far clockwise
				if (distance > 0)
					leftSpeed -= SPEED_CORRECTION
				else
					rightSpeed += SPEED_CORRECTION
			} else { // Too far counterclockwise
				if (distance > 0)
					rightSpeed -= SPEED_CORRECTION
				else
					leftSpeed += SPEED_CORRECTION
			}
		}
		DriveBase.setSpeed(leftSpeed, rightSpeed)
		
	}
	
	override fun end() {
		DriveBase.speed = 0.0
	}
	
	override fun isFinished() = abs(DriveBase.averageDistance - distance) < DISTANCE_TOLERANCE
	
}