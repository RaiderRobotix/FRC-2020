package com.raiderrobotix._2020.commands

import com.raiderrobotix._2020.OperatorInterface
import com.raiderrobotix._2020.subsystems.DriveBase
import kotlinx.coroutines.GlobalScope
import org.team2471.frc.lib.coroutines.meanlibLaunch
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.use

<<<<<<< HEAD
suspend fun operatorControl() = GlobalScope.meanlibLaunch {
	use(DriveBase) {
		periodic {
			DriveBase.tankDrive(
				leftSpeed = -OperatorInterface.leftY,
				rightSpeed = -OperatorInterface.rightY
			)
		}
=======
suspend fun operatorControl() = use(DriveBase) {
	println("operator Control")
	periodic {
		println("teleop periodic")
		DriveBase.tankDrive(
			leftSpeed = -OperatorInterface.leftY,
			rightSpeed = -OperatorInterface.rightY
		)
>>>>>>> 3698b0059430f49d049418e8e80966739555c593
	}
}
