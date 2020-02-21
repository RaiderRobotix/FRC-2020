package com.raiderrobotix._2020

import com.raiderrobotix._2020.subsystems.ColorWheel
import com.raiderrobotix._2020.subsystems.ColorWheel.minus
import com.raiderrobotix._2020.subsystems.ColorWheel.toPrettyString
import com.raiderrobotix._2020.subsystems.DriveBase
import com.raiderrobotix._2020.subsystems.Elevator
import com.raiderrobotix._2020.subsystems.Shooter
import com.raiderrobotix._2020.util.ultraDistance
import org.team2471.frc.lib.framework.RobotProgram
import org.team2471.frc.lib.framework.initializeWpilib
import org.team2471.frc.lib.framework.runRobotProgram

import org.team2471.frc.lib.coroutines.periodic
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard


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
		Shooter.enable()
		ColorWheel.enable()
		zeroOutColor(iter = 20)
		periodic {
			SmartDashboard.putNumber("Ultrasound", ultraDistance)
		}
	}
	
	override suspend fun disable() {
		DriveBase.disable()
	}
	
}
