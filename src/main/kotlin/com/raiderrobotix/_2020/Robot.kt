package com.raiderrobotix._2020

import com.raiderrobotix._2020.subsystems.DriveBase
import com.raiderrobotix._2020.subsystems.Elevator
import com.raiderrobotix._2020.subsystems.Shooter
import com.raiderrobotix._2020.util.printColor
import com.raiderrobotix._2020.util.ultraDistance
import com.raiderrobotix._2020.util.WheelColor
import com.raiderrobotix._2020.util.toPrettyString
import com.raiderrobotix._2020.util.minus
import com.raiderrobotix._2020.util.sensor
import com.raiderrobotix._2020.util.offset
import com.raiderrobotix._2020.util.updateDistance
import org.team2471.frc.lib.framework.RobotProgram
import org.team2471.frc.lib.framework.initializeWpilib
import org.team2471.frc.lib.framework.runRobotProgram

import org.team2471.frc.lib.coroutines.periodic
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj.util.Color


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
		periodic {
			SmartDashboard.putNumber("Cowl Distance", Shooter.cowlDistance)
			SmartDashboard.putNumber("Potent Distance", Shooter.potentiometerDistance)
			SmartDashboard.putNumber("Ultrasound", ultraDistance)
			SmartDashboard.putString("Color", WheelColor.color?.name ?: "Nothing")
			SmartDashboard.putString("Raw Color", (sensor.color.minus (offset)).toPrettyString())
		}
	}
	
	override suspend fun disable() {
		DriveBase.disable()
	}
	
}
