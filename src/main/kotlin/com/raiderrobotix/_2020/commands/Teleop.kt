package com.raiderrobotix._2020.commands

import com.raiderrobotix._2020.OperatorInterface
import com.raiderrobotix._2020.subsystems.DriveBase
import kotlinx.coroutines.coroutineScope
import org.team2471.frc.lib.coroutines.meanlibLaunch
import org.team2471.frc.lib.coroutines.periodic

object Teleop: Action {
	
	override suspend operator fun invoke() {
			periodic(0.05) {
				DriveBase.tankDrive(-OperatorInterface.leftY, -OperatorInterface.rightY)
			}
	}
}
