package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.commands.drivebase.drive
import com.raiderrobotix._2020.subsystems.DriveBase

suspend fun onlyCrossLine() {
	// Auton Test
	// Start on Left Player Station
	// add cowl
	fireNBalls(3)
	drive(displacement = -2.0*12, speed = 0.2) // input correct distance
	
	DriveBase.speed = 0.0
}
