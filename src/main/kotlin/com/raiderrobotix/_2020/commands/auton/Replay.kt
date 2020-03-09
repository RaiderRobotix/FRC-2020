package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.Robot.recordings
import com.raiderrobotix._2020.subsystems.DriveBase
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.use

suspend fun replayTeleop() {
    use(DriveBase) {
        var i = 0
        periodic {
            val current = recordings[i++]
            DriveBase.tankDrive(current.left, current.right)
        }
    }
}
