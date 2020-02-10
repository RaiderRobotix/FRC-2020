package com.raiderrobotix._2020

import com.raiderrobotix._2020.OperatorInterface.manualControl
import com.raiderrobotix._2020.commands.operatorControl
import com.raiderrobotix._2020.commands.testPrint
import com.raiderrobotix._2020.subsystems.DriveBase
import com.raiderrobotix._2020.util.updateDistance
import org.team2471.frc.lib.coroutines.delay
import org.team2471.frc.lib.coroutines.periodic
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
		DriveBase.enable()
	}
	
	override suspend fun disable() {
		DriveBase.disable()
		println("disabled")
		updateDistance()
	}
	
}
