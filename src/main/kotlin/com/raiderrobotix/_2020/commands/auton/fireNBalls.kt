package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.subsystems.Intake
import com.raiderrobotix._2020.subsystems.Shooter
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.team2471.frc.lib.coroutines.suspendUntil

suspend fun fireNBalls(numBalls: Int) = coroutineScope {
	
	val readyShooter = launch {
		Shooter.speed = 1.0
		suspendUntil { Shooter.speed >= 0.15 }
	}
	queueBall(numBalls)
	delay(10 * 1000)
	readyShooter.join()
	Shooter.reset()
	Shooter.speed = 0.0
	Intake.reset()
}
