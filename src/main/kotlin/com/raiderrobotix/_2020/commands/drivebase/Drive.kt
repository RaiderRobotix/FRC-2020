package com.raiderrobotix._2020.commands.drivebase

import com.raiderrobotix._2020.commands.Action
import com.raiderrobotix._2020.subsystems.DriveBase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.team2471.frc.lib.coroutines.MeanlibDispatcher
import org.team2471.frc.lib.coroutines.delay
import kotlin.math.abs

class Drive(private val distance: Double, private val speed: Double): Action {
	
	companion object {
		const val ANGLE_TOLERANCE = 1.0 // TODO
		const val SPEED_CORRECTION = 0.10// TODO
		const val DISTANCE_TOLERANCE = 1.0// TODO
	}
	
	override suspend operator fun invoke() = GlobalScope.launch(MeanlibDispatcher) {
		DriveBase.reset()
		while (abs(DriveBase.averageDistance - distance) >= DISTANCE_TOLERANCE) {
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
			DriveBase.tankDrive(leftSpeed, rightSpeed)
			delay(0.06)
		}
		DriveBase.speed = 0.0
	}
	
}