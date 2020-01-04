package com.raiderrobotix._2020

import com.raiderrobotix._2020.commands.Command
import com.raiderrobotix._2020.commands.DoNothing
import com.raiderrobotix._2020.commands.Teleop
import com.raiderrobotix._2020.util.Vision
import edu.wpi.first.wpilibj.RobotBase
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard

object Robot : TimedRobot() {
	
	private val chooser = SendableChooser<Command>()
	
	init {
		chooser.setDefaultOption("Do Nothing", DoNothing())
		Vision
	}
	
	private val teleop = Teleop()
	private val scheduler = Scheduler.getInstance()!!
	private val auton: Command
		get() = chooser.selected
	
	@JvmStatic
	fun main(args: Array<String>) {
		RobotBase.startRobot { this }
	}
	
	
	init {
		SmartDashboard.putData(scheduler)
	}
	
	override fun robotPeriodic() {
		scheduler.run()
	}
	
	override fun autonomousInit() {
		auton.start()
	}
	
	override fun teleopInit() {
		auton.cancel()
		scheduler.removeAll()
		scheduler.add(teleop)
	}
	
}