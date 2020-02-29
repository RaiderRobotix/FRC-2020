package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.subsystems.Intake
import kotlinx.coroutines.coroutineScope
import org.team2471.frc.lib.coroutines.suspendUntil

suspend fun queueBall(numBalls: Int) = coroutineScope {
	Intake.speed = 1.0
	repeat(numBalls) {
		Intake.outer.speed = 0.6
		suspendUntil { !Intake.IntakeBreaker.get() }
		Intake.outer.speed = 0.0
		suspendUntil { Intake.IntakeBreaker.get() }
	}
}