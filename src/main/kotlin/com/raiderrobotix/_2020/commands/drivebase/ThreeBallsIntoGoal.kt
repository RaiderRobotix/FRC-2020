package com.raiderrobotix._2020.commands.drivebase

import com.raiderrobotix._2020.subsystems.DriveBase
import com.raiderrobotix._2020.subsystems.Intake
import com.raiderrobotix._2020.subsystems.Shooter
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.team2471.frc.lib.coroutines.suspendUntil

suspend fun threeBallsIntoGoal() = coroutineScope {
	val prints = launch {
		Intake.default()
		Shooter.default()
	}
	
	val readyShooter = launch {
		Shooter.speed = 1.0
		suspendUntil { Shooter.speed >= 0.9 }
	}
	
	val queueBall = launch {
		Intake.speed = 0.5
		suspendUntil { !Intake.ShooterBreaker.get() }
		Intake.speed = 0.0
	}
	
	readyShooter.join()
	queueBall.join()
	
	DriveBase.speed = 0.6
	suspendUntil { DriveBase.averageDistance > 12 * 5 || Shooter.Ultrasound() <= 12 * 5 }
	DriveBase.speed = 0.0
	prints.cancelAndJoin()
}
