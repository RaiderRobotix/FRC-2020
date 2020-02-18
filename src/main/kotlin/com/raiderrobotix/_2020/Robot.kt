package com.raiderrobotix._2020

import com.raiderrobotix._2020.subsystems.DriveBase
import com.raiderrobotix._2020.subsystems.Elevator
import com.raiderrobotix._2020.util.printColor
import com.raiderrobotix._2020.util.updateDistance
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
		Elevator.enable()
//		printCowlDistance()
//		updateDistance() //ultra distance
		printColor()
	}
	
	override suspend fun disable() {
		DriveBase.disable()
		println("disabled")
		updateDistance()
	}
	
}
