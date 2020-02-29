package com.raiderrobotix._2020

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
	
	val subsystems = setOf(
		DriveBase,
		Elevator,
		Shooter,
		ColorWheel,
		Intake,
		Trolley
	)
	
	init {
		OperatorInterface
		LimeLight
	}
	
	override suspend fun enable() {
		subsystems.forEach(Subsystem::enable)
	}
	
	override suspend fun disable() {
		LimeLight.ledMode = LimeLight.LedMode.Off
		subsystems.forEach(Subsystem::disable)
	}
	
	override fun comms() {
	}
	
}
