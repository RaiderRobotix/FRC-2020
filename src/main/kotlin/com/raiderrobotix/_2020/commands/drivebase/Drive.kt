package com.raiderrobotix._2020.commands.drivebase

import com.raiderrobotix._2020.subsystems.DriveBase
import org.team2471.frc.lib.coroutines.delay
import org.team2471.frc.lib.framework.use
import kotlin.math.abs
import kotlin.math.sign
import java.io.File
import java.util.Date

const val ANGLE_TOLERANCE = 5.0 // TODO
const val SPEED_CORRECTION = 0.10// TODO
const val DISTANCE_TOLERANCE = 1.0// TODO

suspend fun drive(displacement: Double, speed: Double) = use(DriveBase) {
	val speed = abs(speed) * sign(displacement)
	val currentPosition  = DriveBase.distance
	while (abs( (DriveBase.distance - currentPosition) - displacement) >= DISTANCE_TOLERANCE) {
		var leftSpeed = speed
		var rightSpeed = speed
		
		if (abs(DriveBase.navX.yaw) > ANGLE_TOLERANCE) { // Adjust speeds for in case of veering
			if (DriveBase.navX.yaw > 0) { // Too far clockwise
				if (displacement > 0)
					leftSpeed -= SPEED_CORRECTION
				else
					rightSpeed += SPEED_CORRECTION
			} else { // Too far counterclockwise
				if (displacement > 0)
					rightSpeed -= SPEED_CORRECTION
				else
					leftSpeed += SPEED_CORRECTION
			}
		}
		DriveBase.tankDrive(leftSpeed, rightSpeed)
		delay(0.015)
	}
}
/*
 @param displacement in INCHES
 */
suspend fun driveWithoutCorrection(displacement: Double, speed: Double, rightIncrement: Double) = use(DriveBase) {
	val speed = abs(speed) * sign(displacement)
	val startingPosition  = DriveBase.distance
	// Uses the right encoder ONLY
	while (abs((DriveBase.distance - startingPosition) - displacement) >= DISTANCE_TOLERANCE) {
		val rightSpeed = speed + sign(speed) * rightIncrement
		DriveBase.tankDrive(speed, rightSpeed)
		delay(0.015)
	}
	File("/home/lvuser/alog" + Date().toString()).apply {
		writeText("")
		appendText("Starting: ${startingPosition}\n")
		appendText("Exp Displacement: ${displacement}\n")
		appendText("Ending: ${DriveBase.distance}\n")
		appendText("Act Displacement: ${DriveBase.distance - startingPosition}\n")
	}	
	
}

