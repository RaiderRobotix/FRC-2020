package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.subsystems.Intake
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import org.team2471.frc.lib.coroutines.suspendUntil

suspend fun queueBall(numBalls: Int) {
	Intake.upper.speed = 1.0
	val outer = GlobalScope.launch {
		repeat(numBalls) {
			Intake.outer.speed = 0.6
			suspendUntil { !Intake.IntakeBreaker.get() }
			Intake.outer.speed = 0.0
			suspendUntil { Intake.IntakeBreaker.get() }
		}
	}
	val lower = GlobalScope.launch {
		repeat(numBalls) {
			Intake.lower.speed = 1.0
			suspendUntil { !Intake.StageBreaker.get() }
			Intake.lower.speed = 0.9
			suspendUntil { Intake.StageBreaker.get() }
		}
	}
	outer.join()
	lower.cancelAndJoin()
}
