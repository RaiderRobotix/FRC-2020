package com.raiderrobotix._2020

import com.raiderrobotix._2020.commands.operatorControl
import com.raiderrobotix._2020.subsystems.DriveBase
import com.raiderrobotix._2020.util.printColor
import com.raiderrobotix._2020.util.updateDistance
import org.team2471.frc.lib.framework.RobotProgram
import org.team2471.frc.lib.framework.initializeWpilib
import org.team2471.frc.lib.framework.runRobotProgram
import com.raiderrobotix._2020.subsystems.Shooter
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.coroutines.meanlibLaunch
import org.team2471.frc.lib.framework.use
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay

object Robot : RobotProgram {
	
	@JvmStatic
	fun main(args: Array<String>) {
		initializeWpilib()
		runRobotProgram(Robot)
	}
	
	override suspend fun teleop() {
		operatorControl()
		GlobalScope.meanlibLaunch {
			while(true) {
				println("periodic teleop")
				DriveBase.tankDrive(
					leftSpeed = -OperatorInterface.leftY,
					rightSpeed = -OperatorInterface.rightY
				)
				if (OperatorInterface.operatorTrigger) {
					Shooter.speed = 0.2
				} else {
					Shooter.speed = 0.0
				}
				delay(20)
			}	
		}
	}	
	override suspend fun disable() {
		println("disabled")
		updateDistance()
		printColor()
	}
	
}
