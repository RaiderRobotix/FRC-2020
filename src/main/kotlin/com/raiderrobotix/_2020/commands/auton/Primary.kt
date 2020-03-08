package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.commands.drivebase.drive
import com.raiderrobotix._2020.commands.drivebase.turn
import com.raiderrobotix._2020.commands.drivebase.driveWithoutCorrection
import com.raiderrobotix._2020.commands.shooter.adjustCowl
import com.raiderrobotix._2020.subsystems.DriveBase
import com.raiderrobotix._2020.subsystems.Intake
import com.raiderrobotix._2020.subsystems.Shooter
import org.team2471.frc.lib.framework.use
import kotlinx.coroutines.delay
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancelAndJoin
import org.team2471.frc.lib.coroutines.meanlibLaunch
import org.team2471.frc.lib.coroutines.suspendUntil
import kotlin.math.abs

suspend fun primaryAuton() {
	use(DriveBase) {
	// adjustCowl(0.31)
    // fireNBalls(3)
	drive(displacement = -2.0 * 12, speed = 0.4)
	turn(angularDisplacement = 2.0, speed = 0.2) 
	DriveBase.tankDrive(-0.3, -0.3)
	queueBall(3)
	suspendUntil{ abs(DriveBase.averageDistance) >= 2.0}
	DriveBase.reset()
	Intake.upper.speed = 1.0
	Intake.lower.speed = -1.0
	suspendUntil { !Intake.ShooterBreaker.input.get() }
	Intake.reset()
	// Shooter.reset()

	drive(displacement = 7.0 * 12, speed = 0.4)
	delay(100)
	turn(angularDisplacement = -3.0, speed = 0.4) 

	DriveBase.reset()

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

