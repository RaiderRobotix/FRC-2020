package com.raiderrobotix._2020.commands.auton

import com.raiderrobotix._2020.commands.drivebase.drive
import com.raiderrobotix._2020.commands.drivebase.turn
import com.raiderrobotix._2020.commands.shooter.adjustCowl
import com.raiderrobotix._2020.subsystems.DriveBase
import com.raiderrobotix._2020.subsystems.Intake
import org.team2471.frc.lib.framework.use

suspend fun primaryAuton() {
	use(DriveBase) {
		adjustCowl(0.33)
		fireNBalls(3)
		turn(-30.0, 0.3)
		drive(displacement = -3.0 * 12, speed = 0.5)
		turn(30.0, 0.3)
		use(Intake) {
			queueBall(3)
			drive(displacement = -2.0 * 12, speed = 0.3)
		}
		drive(displacement = 2.0 * 12, speed = 0.3)
		turn(-30.0, 0.3)
		drive(displacement = 3.0 * 12, speed = 0.5)
		turn(30.0, 0.3)
		fireNBalls(3)
	}
}
