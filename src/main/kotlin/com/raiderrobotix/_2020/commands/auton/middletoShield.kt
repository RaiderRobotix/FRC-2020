package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.subsystems.Intake
import com.raiderrobotix._2020.subsystems.DriveBase
import com.raiderrobotix._2020.commands.drivebase.drive
import com.raiderrobotix._2020.commands.drivebase.turn
import org.team2471.frc.lib.framework.use
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.team2471.frc.lib.coroutines.suspendUntil

suspend fun middletoShield() {
     // robot begins angled on field
    use(DriveBase)  {

        drive(displacement = -3.0*12, speed = 0.2) // input correct displacement
        turn(angle = -45.0, speed = 0.2)

        use(Intake) {
    
            queueBall(3)

        }

        drive(displacement = -2.0*12, speed = 0.2 ) //input correct displacement
        drive(displacement = 1.0*12, speed = 0.2 ) //input correct displacement
        turn(angle = 45.0, speed = 0.2)
        drive(displacement = -2.0*12, speed = 0.2 ) //input correct displacement
        turn(angle = -45.0, speed = 0.2)
        drive(displacement = 1.0*12, speed = 0.2 ) //input correct displacement
        drive(displacement = -2.0*12, speed = 0.2 ) //input correct displacement
        drive(displacement = 1.0*12, speed = 0.2 ) //input correct displacement
        turn(angle = 45.0, speed = 0.2)
        drive(displacement = -2.0*12, speed = 0.2 ) //input correct displacement
        turn(angle = -45.0, speed = 0.2)
        drive(displacement = 1.0*12, speed = 0.2 ) //input correct displacement
        turn(angle = 45.0, speed = 0.2)
        drive(displacement = -3.0*12, speed = 0.2) // input correct displacement

    }
}
