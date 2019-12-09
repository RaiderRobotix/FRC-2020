package com.raiderrobotix._2020.commands.drivebase

import com.raiderrobotix._2020.commands.Command
import com.raiderrobotix._2020.subsystems.DriveBase
import kotlin.math.abs

/**
 * @param speed must positive
 */
class Turn(private val angle: Double, private val speed: Double) : Command() {
	
	private val TURN_TOLERANCE = 1.0 // TODO
	
	init {
		requires(DriveBase)
	}
	
	override fun initialize() {
		DriveBase.resetGyro()
	}
	
	override fun execute() {
		DriveBase.setSpeed(speed, -speed)
	}
	
	override fun end() {
		DriveBase.speed = 0.0
	}
	
	override fun isFinished(): Boolean  = abs(DriveBase.gyroAngle - angle) < TURN_TOLERANCE
	
}