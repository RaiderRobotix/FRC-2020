package com.raiderrobotix._2020.commands.drivebase

import com.raiderrobotix._2020.commands.Action
import com.raiderrobotix._2020.subsystems.DriveBase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.team2471.frc.lib.coroutines.MeanlibDispatcher
import org.team2471.frc.lib.coroutines.delay
import kotlin.math.abs

/**
 * @param speed must positive
 */
class Turn(private val angle: Double, private val speed: Double): Action {
	
	companion object {
		private const val TURN_TOLERANCE = 1.0 // TODO
	}
	
	override suspend fun invoke() = GlobalScope.launch(MeanlibDispatcher){
		DriveBase.reset()
		while (!(abs(DriveBase.gyroAngle - angle) < TURN_TOLERANCE)) {
			DriveBase.tankDrive(speed, -speed)
			delay(0.05)
		}
		DriveBase.speed = 0.0
	}
	
}