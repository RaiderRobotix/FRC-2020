package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.commands.drivebase.turn
import com.raiderrobotix._2020.commands.drivebase.drive
import com.raiderrobotix._2020.subsystems.Intake
import com.raiderrobotix._2020.subsystems.DriveBase
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.team2471.frc.lib.coroutines.suspendUntil

suspend fun rightPlayerStation() {
    drive(3.0, -0.2)
    // 	// Auton Test
    //     // Start on Right Player Station

	// drive(3.0, -0.2) // input correct distance
    // queueBall(1)
    // turn(15.0, 0.2) // input correct distance
    // drive(1.0, 0.2) // input correct distance
    // turn(-15.0, 0.2) // input correct distance
    // drive(1.0, -0.2) // input correct distance
    // queueBall(1)
    // turn(15.0, 0.2) // input correct distance
    // drive(10.0, 0.2) // input correct distance
    // suspendUntil { !Intake.ShooterBreaker.get() }

    // DriveBase.speed = 0.0
}