package com.raiderrobotix._2020

import com.raiderrobotix._2020.commands.auton.*
import com.raiderrobotix._2020.subsystems.*
import com.raiderrobotix._2020.util.LimeLight
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.RobotProgram
import org.team2471.frc.lib.framework.initializeWpilib
import org.team2471.frc.lib.framework.runRobotProgram
import org.team2471.frc.lib.framework.use


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
	}
	
	override suspend fun autonomous() {
		val chooser = SendableChooser<suspend () -> Unit>()
		chooser.setDefaultOption("Primary", ::primaryAuton)
		chooser.addOption("onlyCrossLine", ::onlyCrossLine)
		chooser.addOption("rightPlayerStation", ::rightPlayerStation)
		chooser.addOption("middletoShield", ::middletoShield)
		chooser.addOption("travelOtherSide", ::travelOtherSide)
		SmartDashboard.putData("Auton", chooser)
		chooser.selected.invoke()
	}
	
	data class Recording(val left: Double, val right: Double)
	
	val recordings = mutableListOf<Recording>()
	var recording = true
	
	override suspend fun teleop() {
		use(*subsystems.toTypedArray()) {
			periodic {
				val last = Recording(
					left = OperatorInterface.leftY,
					right = OperatorInterface.rightY
				)
				if (recording) {
					recordings.add(last)
				}
				DriveBase.tankDrive(
					leftSpeed = -last.left,
					rightSpeed = -last.right
				)
				Elevator.speed = -OperatorInterface.operatorY
			}
		}
	}

	override fun comms()	{
	
		launchPrints() // might be possible to remove. if systems enables, default should run
	}
}
