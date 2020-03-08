package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.commands.drivebase.drive
import com.raiderrobotix._2020.commands.shooter.adjustCowl
import com.raiderrobotix._2020.subsystems.DriveBase

suspend fun primaryAuton() {
	adjustCowl(0.31)
	fireNBalls(3)
	drive(displacement = -5.8 * 12, speed = 0.5)
	DriveBase.speed = -0.2
	queueBall(3)
	DriveBase.speed = 0.0
}
