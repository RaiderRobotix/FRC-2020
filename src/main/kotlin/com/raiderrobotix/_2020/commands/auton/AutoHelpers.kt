package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.Robot
import com.raiderrobotix._2020.subsystems.Intake
import kotlinx.coroutines.GlobalScope
import org.team2471.frc.lib.coroutines.meanlibLaunch
import org.team2471.frc.lib.coroutines.suspendUntil

suspend fun queueBall(numBalls: Int) {
	Intake.upper.speed = 1.0
	Intake.lower.speed = -1.0
	repeat(numBalls) {
		Intake.outer.speed = 0.6
		suspendUntil { !Intake.IntakeBreaker.input.get() }
		Intake.outer.speed = 0.0
		suspendUntil { Intake.IntakeBreaker.input.get() }
	}
	
}

internal fun launchPrints() = GlobalScope.meanlibLaunch {
	Robot.subsystems.forEach { it.default() }
}
