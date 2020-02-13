package com.raiderrobotix._2020

<<<<<<< HEAD
import com.raiderrobotix._2020.subsystems.DriveBase
import com.raiderrobotix._2020.subsystems.Elevator
=======
import com.raiderrobotix._2020.commands.operatorControl
import com.raiderrobotix._2020.subsystems.DriveBase
import com.raiderrobotix._2020.util.printColor
>>>>>>> 3698b0059430f49d049418e8e80966739555c593
import com.raiderrobotix._2020.util.updateDistance
import org.team2471.frc.lib.framework.RobotProgram
import org.team2471.frc.lib.framework.initializeWpilib
import org.team2471.frc.lib.framework.runRobotProgram
import com.raiderrobotix._2020.subsystems.Shooter
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.coroutines.meanlibLaunch
import org.team2471.frc.lib.framework.use
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay

object Robot : RobotProgram {
	
	@JvmStatic
	fun main(args: Array<String>) {
		initializeWpilib()
		runRobotProgram(Robot)
	}
	
	init {
		OperatorInterface
	}
	
	override suspend fun teleop() {
<<<<<<< HEAD
		DriveBase.enable()
		Elevator.enable()
	}
	
=======
		println("teleop")
		operatorControl()
		GlobalScope.meanlibLaunch {
			while(true) {
				println("periodic teleop")
				DriveBase.tankDrive(
					leftSpeed = -OperatorInterface.leftY,
					rightSpeed = -OperatorInterface.rightY
				)
				if (OperatorInterface.operatorTrigger) {
					Shooter.speed = 0.2
				} else {
					Shooter.speed = 0.0
				}
				delay(20)
			}	
		}
	}	
>>>>>>> 3698b0059430f49d049418e8e80966739555c593
	override suspend fun disable() {
		DriveBase.disable()
		println("disabled")
		updateDistance()
	}
	
}
