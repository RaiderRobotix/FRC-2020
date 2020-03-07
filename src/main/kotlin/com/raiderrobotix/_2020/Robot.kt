package com.raiderrobotix._2020

import com.raiderrobotix._2020.commands.auton.launchPrints
import com.raiderrobotix._2020.commands.auton.primaryAuton
import com.raiderrobotix._2020.subsystems.*
import com.raiderrobotix._2020.util.LimeLight
import kotlinx.coroutines.cancelAndJoin
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
	
	val subsystems = setOf(
		DriveBase,
		Elevator,
		Shooter,
		ColorWheel,
		Intake,
		Trolley
	)
	
	override suspend fun enable() {
		subsystems.forEach { (it as SensorOutput).update() }
	}
	
	override fun comms() {
		LimeLight
	}
	
	override suspend fun autonomous() {
		val prints = launchPrints()
		primaryAuton()
		prints.cancelAndJoin()
	}
	
}
