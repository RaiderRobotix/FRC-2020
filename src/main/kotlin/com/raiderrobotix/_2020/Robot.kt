package com.raiderrobotix._2020

import com.raiderrobotix._2020.OperatorInterface.manualControl
import com.raiderrobotix._2020.commands.operatorControl
import com.raiderrobotix._2020.util.updateDistance
import org.team2471.frc.lib.coroutines.delay
import org.team2471.frc.lib.framework.RobotProgram
import org.team2471.frc.lib.framework.initializeWpilib
import org.team2471.frc.lib.framework.runRobotProgram

object Robot : RobotProgram {
	
	@JvmStatic
	fun main(args: Array<String>) {
		initializeWpilib()
		runRobotProgram(Robot)
	}
	
	init {
		OperatorInterface
	}
	
	override suspend fun teleop() {
		operatorControl()
		while (true) {
			manualControl()
			delay(0.02)
		}
	}
	
	override suspend fun disable() {
		println("disabled")
		updateDistance()
	}
	
}
