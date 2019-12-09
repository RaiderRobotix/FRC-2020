package com.raiderrobotix._2020

import edu.wpi.first.wpilibj.RobotBase
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard

object Robot : TimedRobot() {
	
	private var autonomousCommand: Command? = null
	private val chooser = SendableChooser<Command>()	@JvmStatic
	fun main(args: Array<String>) {							RobotBase.startRobot { this }
	}
	
	
	init {
		SmartDashboard.putData(Scheduler.getInstance())
	}
	
	override fun robotPeriodic() {
		Scheduler.getInstance().run()
	}
	
	override fun autonomousInit() {
		autonomousCommand = chooser.selected
		if (autonomousCommand != null) {
			autonomousCommand!!.start()
		}
	}

	override fun teleopInit() {
		if (autonomousCommand != null) {
			autonomousCommand!!.cancel()
		}
	}


}