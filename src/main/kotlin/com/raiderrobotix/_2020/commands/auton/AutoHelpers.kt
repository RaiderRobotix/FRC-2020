package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.Robot
import com.raiderrobotix._2020.subsystems.Intake
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import org.team2471.frc.lib.coroutines.meanlibLaunch
import org.team2471.frc.lib.coroutines.suspendUntil

suspend fun queueBall(numBalls: Int) {
	Intake.upper.speed = 1.0
	Intake.lower.speed = -1.0
	repeat(numBalls - 1) {
		Intake.outer.speed = 0.6
		suspendUntil { !Intake.IntakeBreaker.input.get() }
		Intake.outer.speed = 0.0
		suspendUntil { Intake.IntakeBreaker.input.get() }
		delay(800)
	}
		Intake.outer.speed = 0.6
		suspendUntil { !Intake.IntakeBreaker.input.get() }
		Intake.outer.speed = 0.0
}

internal fun launchPrints() = GlobalScope.meanlibLaunch {
	Robot.subsystems.forEach { it.default() }
}
