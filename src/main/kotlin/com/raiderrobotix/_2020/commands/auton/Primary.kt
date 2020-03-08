package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.commands.drivebase.drive
import com.raiderrobotix._2020.commands.drivebase.turn
import com.raiderrobotix._2020.commands.shooter.adjustCowl
import com.raiderrobotix._2020.subsystems.DriveBase
import org.team2471.frc.lib.framework.use
import kotlinx.coroutines.delay
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancelAndJoin
import org.team2471.frc.lib.coroutines.meanlibLaunch
import org.team2471.frc.lib.coroutines.suspendUntil

suspend fun primaryAuton() {
	use(DriveBase) {
	// adjustCowl(0.31)
	// fireNBalls(3)
	drive(displacement = -6.0 * 12, speed = 0.2)
	turn(angularDisplacement = 3.0, speed = 0.2) 
	DriveBase.reset()
	DriveBase.speed = -0.2
	val queue = GlobalScope.meanlibLaunch {
		queueBall(3)
	}
	suspendUntil{DriveBase.averageDistance > 2.0*12}
	DriveBase.speed = 0.0
	queue.cancelAndJoin()
	Intake.upper.speed = 1.0
	Intake.lower.speed = -1.0
	suspendUntil { !Intake.ShooterBreaker.input.get() }
	Intake.reset()
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

