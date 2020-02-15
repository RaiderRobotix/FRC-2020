package com.raiderrobotix._2020.commands.shooter

import com.raiderrobotix._2020.commands.drivebase.ANGLE_TOLERANCE
import com.raiderrobotix._2020.subsystems.Shooter
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.use
import kotlin.math.abs
import kotlin.math.sign

suspend fun toAngle(angle: Double) {
	use(Shooter) {
		periodic {
			Shooter.cowlSpeed = 0.2 * sign(angle - Shooter.cowlAngle)
			if (abs(angle - Shooter.cowlAngle) <= ANGLE_TOLERANCE)
				stop()
		}
	}
}