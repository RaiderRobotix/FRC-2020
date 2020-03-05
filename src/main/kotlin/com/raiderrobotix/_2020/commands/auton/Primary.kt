package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.commands.drivebase.drive
import com.raiderrobotix._2020.subsystems.DriveBase
import com.raiderrobotix._2020.subsystems.Intake
import com.raiderrobotix._2020.subsystems.Shooter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancelAndJoin
import org.team2471.frc.lib.coroutines.meanlibLaunch

suspend fun primaryAuton() {
	val prints = GlobalScope.meanlibLaunch {
		Intake.default()
		Shooter.default()
	}
//	fireNBalls(3)
//	drive(3.0, -0.2) // input correct distance
//	turn(-15.0, 0.2)
//
//	drive(3.0, -0.2)
	queueBall(3)
	drive(3.0, -0.2)
//	suspendUntil { !Intake.ShooterBreaker.get() }
	Intake.speed = 0.0
	DriveBase.speed = 0.0
	prints.cancelAndJoin()
}
