package com.raiderrobotix._2020

import com.raiderrobotix._2020.commands.Teleop
import com.raiderrobotix._2020.util.Vision
import org.team2471.frc.lib.framework.RobotProgram
import org.team2471.frc.lib.framework.initializeWpilib
import org.team2471.frc.lib.framework.runRobotProgram

object Robot : RobotProgram {
	
	init {
		Vision
	}
	
	@JvmStatic
	fun main(args: Array<String>) {
		initializeWpilib()
		runRobotProgram(Robot)
	}
	
	
	override suspend fun teleop() {
		Teleop()
	}
	
}