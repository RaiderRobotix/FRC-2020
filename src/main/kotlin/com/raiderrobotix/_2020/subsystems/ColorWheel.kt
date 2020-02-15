package com.raiderrobotix._2020.subsystems

import edu.wpi.first.wpilibj.Spark
import org.team2471.frc.lib.framework.Subsystem

object ColorWheel : Subsystem("ColorWheel") {
	val motor = Spark(6)
}