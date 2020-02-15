package com.raiderrobotix._2020.commands

import com.raiderrobotix._2020.OperatorInterface
import com.raiderrobotix._2020.subsystems.DriveBase
import kotlinx.coroutines.GlobalScope
import org.team2471.frc.lib.coroutines.meanlibLaunch
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.use

suspend fun operatorControl() = GlobalScope.meanlibLaunch {
	use(DriveBase) {
		periodic {
			DriveBase.tankDrive(
				leftSpeed = -OperatorInterface.leftY,
				rightSpeed = -OperatorInterface.rightY
			)
		}
	}
}
