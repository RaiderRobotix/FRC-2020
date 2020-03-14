package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.commands.drivebase.driveWithoutCorrection
import com.raiderrobotix._2020.subsystems.DriveBase
import org.team2471.frc.lib.framework.use

suspend fun primaryAuton() {
    use(DriveBase) {
// 		adjustCowl(0.31)
        // fireNBalls(3)
        val initial = DriveBase.distance
        driveWithoutCorrection(displacement = -4.0 * 12, speed = 0.25, rightIncrement = 0.01)
        val after = DriveBase.distance
        val togo = -12.0 * 12 - (after - initial)
        driveWithoutCorrection(displacement = togo, speed = 0.60, rightIncrement = 0.03)
// 		drive(displacement = -5.8 * 12, speed = 0.2)
// 		turn(angularDisplacement = 5.0, speed = 0.2)
// 		DriveBase.speed = -0.2
// 		queueBall(3)
// 		DriveBase.speed = 0.0
        // repeat(3) {
        // 	Intake.upper.speed = 1.0
        // 	Intake.lower.speed = -1.0
        // 	Intake.outer.speed = 0.6
        // 	DriveBase.speed = -0.2
        // 	suspendUntil { !Intake.IntakeBreaker.input.get() }
        // 	DriveBase.speed = 0.0
        // 	delay(500)
        // 	Intake.upper.speed = 0.0
        // 	Intake.lower.speed = 0.0
        // 	Intake.outer.speed = 0.0
        // }

        // drive(displacement = 2.0 * 12, speed = 0.3)
        // turn(angleToTrench, 0.3)
        // drive(displacement = 3.0 * 12, speed = 0.5)
        // turn(-angleToTrench, 0.3)
        // fireNBalls(1)
    }
}
