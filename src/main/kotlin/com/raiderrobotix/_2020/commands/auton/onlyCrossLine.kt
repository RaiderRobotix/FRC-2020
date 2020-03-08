package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.commands.drivebase.drive
import com.raiderrobotix._2020.subsystems.DriveBase

suspend fun onlyCrossLine() {
	// Auton Test
	// Start on Left Player Station
	
	fireNBalls(3)
	drive(displacement = -1.5*12, speed = 0.2) // input correct distance
	
	DriveBase.speed = 0.0
}
