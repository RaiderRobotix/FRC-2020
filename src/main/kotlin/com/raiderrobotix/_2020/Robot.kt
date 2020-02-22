package com.raiderrobotix._2020

import com.raiderrobotix._2020.subsystems.*
import org.team2471.frc.lib.framework.RobotProgram
import org.team2471.frc.lib.framework.Subsystem
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
		setOf(
			DriveBase,
			Elevator,
			Shooter,
			ColorWheel,
			Intake
		).forEach(Subsystem::enable)
	}
	
	override suspend fun disable() {
		DriveBase.disable()
	}
	
}
