package com.raiderrobotix._2020

import com.raiderrobotix._2020.OperatorInterface.get
import com.raiderrobotix._2020.commands.operatorControl
import com.raiderrobotix._2020.subsystems.DriveBase
import com.raiderrobotix._2020.subsystems.Intake
import com.raiderrobotix._2020.subsystems.Shooter
import com.raiderrobotix._2020.util.printColor
import com.raiderrobotix._2020.util.updateDistance
import org.team2471.frc.lib.coroutines.delay
import org.team2471.frc.lib.framework.RobotProgram
import org.team2471.frc.lib.framework.initializeWpilib
import org.team2471.frc.lib.framework.runRobotProgram
import kotlin.math.abs

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
		operatorControl()
		printColor()
		while (true) {
			val left = -OperatorInterface.leftY
			val right = -OperatorInterface.rightY
			DriveBase.tankDrive(
				leftSpeed = left,
				rightSpeed = right
			)
			if (abs(left) > 0 || abs(right) > 0 || OperatorInterface.operatorStick[12]) {
				Intake.mouthSpeed = 0.5
			} else {
				Intake.mouthSpeed = 0.0
			}
			
			if (OperatorInterface.operatorTrigger) {
				Shooter.speed = 1.0
			} else {
				Shooter.speed = 0.0
			}
			Intake.speed = when {
				OperatorInterface.operatorStick[2] -> 1.0
				OperatorInterface.operatorStick[3] -> -0.5
				else -> 0.0
			}
			delay(0.02)
		}
	}
	
	override suspend fun disable() {
		println("disabled")
		updateDistance()
	}
	
}
