package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.subsystems.Intake
import com.raiderrobotix._2020.subsystems.Shooter
import kotlinx.coroutines.coroutineScope
import org.team2471.frc.lib.coroutines.suspendUntil

suspend fun fireNBalls(numBalls: Int) = coroutineScope {
	Shooter.speed = 1.0
	Intake.upper.speed = 1.0
	Intake.lower.speed = -1.0
	Intake.outer.speed = 0.6
	repeat(numBalls) {
		suspendUntil { !Intake.ShooterBreaker.input.get() }
		suspendUntil { Intake.ShooterBreaker.input.get() }
		suspendUntil { Shooter.speed >= 0.9 }
	}
	Shooter.reset()
	Intake.reset()
}
