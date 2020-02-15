package com.raiderrobotix._2020.subsystems

import edu.wpi.first.wpilibj.Spark
import org.team2471.frc.lib.framework.Subsystem

object Shooter : Subsystem("Shooter") {
	private const val topChannel = 1 // TODO
	private const val bottomChannel = 0 // TODO
	private const val cowlChannel = 7 // TODO

	private val top = Spark(topChannel)
	private val bottom = Spark(bottomChannel)
	private val cowl = Spark(cowlChannel)

	var speed: Double
		set(it) {
			top.set(it)
			bottom.set(it)
		}
		get() = (top.get() + bottom.get()) / 2

	var cowlSpeed: Double
		set(value) = let { cowl.speed = value }
		get() = cowl.speed

	override fun reset() {
		speed = 0.0
	}

}