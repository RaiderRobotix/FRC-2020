package com.raiderrobotix._2020

import com.raiderrobotix._2020.commands.Teleop
import com.raiderrobotix._2020.util.updateDistance
import com.raiderrobotix._2020.util.LimeLight
import com.raiderrobotix._2020.util.printColor
import kotlinx.coroutines.GlobalScope
import org.team2471.frc.lib.coroutines.meanlibLaunch
import org.team2471.frc.lib.framework.RobotProgram
import org.team2471.frc.lib.framework.initializeWpilib
import org.team2471.frc.lib.framework.runRobotProgram

object Robot : RobotProgram {
	
	init {
		// Vision
		// LimeLight
	}
	
	@JvmStatic
	fun main(args: Array<String>) {
		initializeWpilib()
		runRobotProgram(Robot)
	}
	
	
	override suspend fun teleop() {
		Teleop()
	}
	
	override suspend fun disable() {
		println("disabled")
		updateDistance()
		printColor()
	}
	
}
