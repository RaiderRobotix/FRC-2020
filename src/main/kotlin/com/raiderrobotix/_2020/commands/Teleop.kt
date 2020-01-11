package com.raiderrobotix._2020.commands

import com.raiderrobotix._2020.OperatorInterface
import com.raiderrobotix._2020.subsystems.DriveBase
import kotlinx.coroutines.GlobalScope
import org.team2471.frc.lib.coroutines.delay
import org.team2471.frc.lib.coroutines.meanlibLaunch
import org.team2471.frc.lib.coroutines.periodic

object Teleop {
	
	operator fun invoke() = GlobalScope.meanlibLaunch {
		while (true) {
			DriveBase.tankDrive(-OperatorInterface.leftY, -OperatorInterface.rightY)
			delay(0.05)
		}
		DriveBase.speed = 0.0
	}
	
}
