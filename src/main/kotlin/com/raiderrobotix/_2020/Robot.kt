package com.raiderrobotix._2020

import com.raiderrobotix._2020.subsystems.ColorWheel
import com.raiderrobotix._2020.subsystems.DriveBase
import com.raiderrobotix._2020.subsystems.Elevator
import com.raiderrobotix._2020.subsystems.Shooter
import com.raiderrobotix._2020.util.ultraDistance
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.team2471.frc.lib.coroutines.periodic
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
			ColorWheel
		).forEach(Subsystem::enable)
		periodic {
			SmartDashboard.putNumber("Ultrasound", ultraDistance)
		}
	}
	
	override suspend fun disable() {
		DriveBase.disable()
	}
	
}
