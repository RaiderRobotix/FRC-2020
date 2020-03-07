package com.raiderrobotix._2020.commands.drivebase

import com.raiderrobotix._2020.subsystems.DriveBase
import com.raiderrobotix._2020.util.LimeLight
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.use
import kotlin.math.abs
import kotlin.math.sign

private const val TURN_TOLERANCE = 1.0 // TODO

/**
 * @param speed must positive
 */
suspend fun turn(angularDisplacement: Double, speed: Double) = use(DriveBase) {
	val speed = abs(speed) * sign(angularDisplacement)
	val navX = DriveBase.navX
	navX.zeroYaw()
	periodic {
		if (abs(navX.angle - angularDisplacement) >= TURN_TOLERANCE) {
			DriveBase.tankDrive(speed, -speed)
		} else {
			stop()
		}
	}
}

suspend fun centerLimelight() = use(DriveBase) {
	LimeLight.ledMode = LimeLight.LedMode.On
	val speed = 0.1
	periodic {
		if (abs(LimeLight.x) <= TURN_TOLERANCE) {
			stop()
			SmartDashboard.putBoolean("Centered", true)
		} else {
			SmartDashboard.putBoolean("Centered", false)
			val sgn = sign(LimeLight.x)
			DriveBase.tankDrive(speed * sgn, -speed * sgn)
		}
	}
}
