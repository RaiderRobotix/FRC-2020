package com.raiderrobotix._2020.commands

import com.raiderrobotix._2020.OperatorInterface
import com.raiderrobotix._2020.subsystems.DriveBase
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.use

suspend fun operatorControl() = use(DriveBase) {
	println("operator Control")
	periodic {
		println("teleop periodic")
		DriveBase.tankDrive(
			leftSpeed = -OperatorInterface.leftY,
			rightSpeed = -OperatorInterface.rightY
		)
	}
}