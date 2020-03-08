package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.subsystems.Intake
import com.raiderrobotix._2020.subsystems.Shooter
import kotlinx.coroutines.delay
import org.team2471.frc.lib.coroutines.suspendUntil

suspend fun fireNBalls(numBalls: Int) {
	Shooter.speed = 1.0
	delay(500)
	println("half second delay")
	Intake.upper.speed = 1.0
	Intake.lower.speed = -1.0
	Intake.outer.speed = 0.6
	repeat(numBalls) {
		suspendUntil { !Intake.ShooterBreaker.input.get() }
		suspendUntil { Intake.ShooterBreaker.input.get() }
		Intake.upper.speed = 0.0
		Intake.lower.speed = 0.0
		Intake.outer.speed = 0.0
		delay(350)
		Intake.upper.speed = 1.0
		Intake.lower.speed = -1.0
		Intake.outer.speed = 0.6
		
	}
	// Shooter.reset()
	Intake.reset()
}
