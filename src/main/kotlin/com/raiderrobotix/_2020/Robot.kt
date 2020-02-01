package com.raiderrobotix._2020

import com.raiderrobotix._2020.commands.operatorControl

import com.raiderrobotix._2020.subsystems.DriveBase
import com.raiderrobotix._2020.util.printColor
import com.raiderrobotix._2020.util.updateDistance
import org.team2471.frc.lib.framework.RobotProgram
import org.team2471.frc.lib.framework.initializeWpilib
import org.team2471.frc.lib.framework.runRobotProgram
import org.team2471.frc.lib.coroutines.delay

object Robot : RobotProgram {
	
	@JvmStatic
	fun main(args: Array<String>) {
		initializeWpilib()
		runRobotProgram(Robot)
	}
	
	override suspend fun teleop() {
		while (true) {
			DriveBase.tankDrive(
				leftSpeed = -OperatorInterface.leftY,
				rightSpeed = -OperatorInterface.rightY
			)
			delay(0.02)
		}
	}
	
	override suspend fun disable() {
		println("disabled")
		updateDistance()
		printColor()
	}
	
}
