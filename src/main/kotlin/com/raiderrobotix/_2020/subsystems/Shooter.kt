package com.raiderrobotix._2020.subsystems

import edu.wpi.first.wpilibj.DigitalInput
import edu.wpi.first.wpilibj.Spark
import edu.wpi.first.wpilibj.SpeedControllerGroup
import org.team2471.frc.lib.framework.Subsystem

object Shooter : Subsystem("Shooter") {
	private const val topChannel = 1 // TODO
	private const val bottomChannel = 0 // TODO
	private const val cowlChannel = 8 // TODO
	private const val limitDIO = 1 // TODO

	private val cowl = Spark(cowlChannel)
	private val group = SpeedControllerGroup(Spark(topChannel), Spark(bottomChannel))

	private val limit = DigitalInput(limitDIO)
	private const val limitEnabled = true


	var speed: Double
		set(it) {
			if (limitEnabled && !limit.get()) {
				group.set(it)
			}
		}
		get() = group.get()

	var cowlSpeed: Double
		set(value) { cowl.speed = value }
		get() = cowl.speed

	override fun reset() {
		speed = 0.0
	}

}