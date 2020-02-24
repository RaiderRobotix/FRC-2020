package com.raiderrobotix._2020.commands.drivebase

import com.raiderrobotix._2020.subsystems.DriveBase
import com.raiderrobotix._2020.subsystems.Elevator
import com.raiderrobotix._2020.subsystems.Intake
import com.raiderrobotix._2020.subsystems.Shooter
import kotlinx.coroutines.delay
import org.team2471.frc.lib.coroutines.delay
import org.team2471.frc.lib.coroutines.suspendUntil

suspend fun ThreeBallsIntoGoal() {
	repeat(3) {
		Shooter.speed = 0.5
		delay(2000)
		// Adjust cowl
		suspendUntil { Intake.ShooterBreaker.get() }
		delay(1.0)
		Intake.speed = 0.6
		suspendUntil { !Intake.ShooterBreaker.get() }
		suspendUntil { Intake.ShooterBreaker.get() }
		Elevator.speed = 0.0
	}
	DriveBase.speed = 0.6
	suspendUntil { DriveBase.averageDistance > 12 * 5 || Shooter.Ultrasound() <= 12 * 5 }
	DriveBase.speed = 0.0
}