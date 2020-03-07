package com.raiderrobotix._2020

import com.raiderrobotix._2020.commands.auton.*
import com.raiderrobotix._2020.commands.auton.launchPrints
import com.raiderrobotix._2020.commands.shooter.adjustCowl
import com.raiderrobotix._2020.subsystems.*
import com.raiderrobotix._2020.util.LimeLight
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
	
	val subsystems = setOf(
		DriveBase,
		Elevator,
		Shooter,
		ColorWheel,
		Intake,
		Trolley,
		LimeLight
	)
	
	override suspend fun enable() {
		subsystems.forEach { it.enable() }
		launchPrints() // might be possible to remove. if systems enables, default should run
	}
	
	override suspend fun autonomous() {
		primaryAuton()
		// adjustCowl(0.33)
	}
	
	override suspend fun teleop() {
		periodic {
			DriveBase.tankDrive(
				leftSpeed = -OperatorInterface.leftY,
				rightSpeed = -OperatorInterface.rightY
			)
			Elevator.speed = -OperatorInterface.operatorY
		}
	}
	
}
