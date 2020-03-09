package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.commands.drivebase.drive
import com.raiderrobotix._2020.commands.drivebase.turn
import com.raiderrobotix._2020.subsystems.DriveBase
import org.team2471.frc.lib.framework.use

suspend fun travelOtherSide() {
    // robot begins angled on field
    use(DriveBase) {
        drive(displacement = -3.0 * 12, speed = 0.2) // input correct distance
        turn(angularDisplacement = -90.0, speed = 0.2)
        drive(displacement = -10.0 * 12, speed = 0.2) // input correct distance
    }
}
