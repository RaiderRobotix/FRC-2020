package com.raiderrobotix._2020

import com.raiderrobotix._2020.commands.drivebase.centerLimelight
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
		LimeLight
	}
	
	override suspend fun enable() {
		setOf(
			DriveBase,
			Elevator,
			Shooter,
			ColorWheel,
			Intake,
			Trolley
		).forEach(Subsystem::enable)
	}
	
	override suspend fun disable() {
		LimeLight.ledMode = LimeLight.LedMode.off
		setOf(
			DriveBase,
			Elevator,
			Shooter,
			ColorWheel,
			Intake,
			Trolley
		).forEach(Subsystem::disable)
	}
	
	override fun comms() {
	}
	
	override suspend fun autonomous() {
		centerLimelight()
	}
	
}
