package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.commands.drivebase.drive
import com.raiderrobotix._2020.subsystems.DriveBase

suspend fun travelOtherSide()   {
    // robot begins angled on field
    use(DriveBase)  {
	drive(distance = -3.0*12, speed = 0.2) // input correct distance
    turn(angle = -90.0, speed = 0.2)
    drive(distance = -10.0*12, speed = 0.2 ) //input correct distance
    }
}