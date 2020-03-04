package com.raiderrobotix._2020

import com.raiderrobotix._2020.commands.colorwheel.auton.primaryAuton
import com.raiderrobotix._2020.subsystems.*
import com.raiderrobotix._2020.util.LimeLight
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
			Intake,
			Trolley
		).forEach(Subsystem::enable)
	}
	
	override fun comms() {
		LimeLight
	}
	
	override suspend fun autonomous() {
		primaryAuton()
	}
	
}
