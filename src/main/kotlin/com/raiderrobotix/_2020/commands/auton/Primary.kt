package com.raiderrobotix._2020.commands.auton

suspend fun primaryAuton() {
    	// Auton Test

	fireNBalls(3)
	drive(3.0, -0.2) // input correct distance
	turn(-15.0, 0.2)

	drive(3.0, -0.2)
	queueBall(3)
	suspendUntil { !Intake.ShooterBreaker.get() }
	Intake.speed = 0.0
	DriveBase.speed = 0.0
}