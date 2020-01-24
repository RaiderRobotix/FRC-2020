package com.raiderrobotix._2020.commands

import com.raiderrobotix._2020.OperatorInterface
import com.raiderrobotix._2020.subsystems.DriveBase
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.use

object Teleop : Action {
	
	override suspend operator fun invoke() = use(DriveBase) {
		periodic {
			DriveBase.tankDrive(-OperatorInterface.leftY, -OperatorInterface.rightY)
		}
	}
}
