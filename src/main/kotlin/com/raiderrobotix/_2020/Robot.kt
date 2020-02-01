package com.raiderrobotix._2020

import com.raiderrobotix._2020.commands.operatorControl
import com.raiderrobotix._2020.util.printColor
import com.raiderrobotix._2020.util.updateDistance
import org.team2471.frc.lib.framework.RobotProgram
import org.team2471.frc.lib.framework.initializeWpilib
import org.team2471.frc.lib.framework.runRobotProgram
import com.raiderrobotix._2020.subsystems.DriveBase
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.use
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay

object Robot : RobotProgram {
	
	@JvmStatic
	fun main(args: Array<String>) {
		initializeWpilib()
		runRobotProgram(Robot)
	}
	
	override suspend fun teleop() {
		operatorControl()
	}
	
	override suspend fun disable() {
		println("disabled")
		updateDistance()
		printColor()
	}
	
}
