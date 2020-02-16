package com.raiderrobotix._2020.commands.drivebase

import com.raiderrobotix._2020.subsystems.DriveBase
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.use
import kotlin.math.abs

private const val TURN_TOLERANCE = 1.0 // TODO

/**
 * @param speed must positive
 */
suspend fun turn(angle: Double, speed: Double) = use(DriveBase) {
	val navX = DriveBase.navX
	navX.zeroYaw()
	periodic {
		if (abs(navX.angle - angle) >= TURN_TOLERANCE) {
			DriveBase.tankDrive(speed, -speed)
		} else {
			stop()
		}
	}
}
