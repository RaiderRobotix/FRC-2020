package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.subsystems.Intake
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.team2471.frc.lib.coroutines.suspendUntil

suspend fun middletoShield() {
     // robot begins angled on field
    use(DriveBase)  {

    drive(distance = -3.0*12, speed = 0.2) // input correct distance
    turn(angle = -45.0, speed = 0.2)

    use(Intake) {
   
    queueBall(3)

    }

    drive(distance = -2.0*12, speed = 0.2 ) //input correct distance
    drive(distance = 1.0*12, speed = 0.2 ) //input correct distance
    turn(angle = 45.0, speed = 0.2)
    drive(distance = -2.0*12, speed = 0.2 ) //input correct distance
    turn(angle = -45.0, speed = 0.2)
    drive(distance = 1.0*12, speed = 0.2 ) //input correct distance
    drive(distance = -2.0*12, speed = 0.2 ) //input correct distance
    drive(distance = 1.0*12, speed = 0.2 ) //input correct distance
    turn(angle = 45.0, speed = 0.2)
    drive(distance = -2.0*12, speed = 0.2 ) //input correct distance
    turn(angle = -45.0, speed = 0.2)
    drive(distance = 1.0*12, speed = 0.2 ) //input correct distance
    turn(angle = 45.0, speed = 0.2)
    drive(distance = -3.0*12, speed = 0.2) // input correct distance

    }
}
