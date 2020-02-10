package com.raiderrobotix._2020

import com.raiderrobotix._2020.subsystems.DriveBase
import com.raiderrobotix._2020.subsystems.Intake
import com.raiderrobotix._2020.subsystems.Shooter
import com.raiderrobotix._2020.subsystems.Elevator
import com.raiderrobotix._2020.subsystems.Trolley
import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.Sendable
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import org.ghrobotics.lib.wrappers.hid.FalconHID
import org.ghrobotics.lib.wrappers.hid.mapControls
import org.team2471.frc.lib.coroutines.meanlibLaunch
import org.team2471.frc.lib.framework.use
import org.team2471.frc.lib.input.whenTrue
import kotlin.math.abs

object OperatorInterface : Sendable {
	
	init {
		SmartDashboard.putData(this)
	}
	
	override fun initSendable(builder: SendableBuilder) {
		builder.addDoubleProperty("lY", ::leftY, null)
		builder.addDoubleProperty("rY", ::rightY, null)
		builder.addDoubleProperty("oY", ::operatorY, null)
	}
	
	private const val LEFT_JOYSTICK_PORT = 0
	private const val RIGHT_JOYSTICK_PORT = 2
	private const val OPERATOR_JOYSTICK_PORT = 1
	private const val JOYSTICK_DEADBAND = 0.15
	
	// Joysticks
	
	private val left = Joystick(LEFT_JOYSTICK_PORT)
	private val right = Joystick(RIGHT_JOYSTICK_PORT)
	private val operator = Joystick(OPERATOR_JOYSTICK_PORT)


	init {
		({ operator[1] }).whenTrue { Shooter.speed = 1.0 }
		({ !operator[1] }).whenTrue { Shooter.reset() }
		({ operator[2] }).whenTrue {
			Intake.speed = 1.0
			Intake.outer.speed = 0.6
		}
		({ !operator[2] }).whenTrue { Intake.reset() }
		({ operator[3] }).whenTrue {
			Intake.speed = -0.5
			Intake.outer.speed = -0.7
		}
		({ !operator[3] }).whenTrue { Intake.reset() }
		({ operator[9] }).whenTrue { Elevator.speed = 0.6 }
		({ !operator[9] }).whenTrue { Elevator.reset() }
		({ operator[10] }).whenTrue { Elevator.speed = -0.6 }
		({ !operator[10] }).whenTrue { Elevator.reset() }
		({ operator[7] }).whenTrue { Trolley.speed = 0.6 }
		({ !operator[7] }).whenTrue { Trolley.reset() }
		({ operator[8] }).whenTrue { Trolley.speed = -0.6 }
		({ !operator[8] }).whenTrue { Trolley.reset() }
	}
	
	/**
	 * Down on Joystick is positive, up is negative
	 */
	val leftY: Double
		get() {
			val ret = left.y
			return if (abs(ret) > JOYSTICK_DEADBAND) ret else 0.0
		}
	
	val rightY: Double
		get() = right.y.takeIf { abs(it)  > JOYSTICK_DEADBAND } ?: 0.0
	
	val operatorY: Double
		get() = operator.y
	
	val operatorTrigger: Boolean
		get() = operator.trigger
	
	operator fun Joystick.get(button: Int) = this.getRawButton(button)

	operator fun <T : GenericHID> FalconHID<T>.get(button: Int) = this.getRawButton(button)
	
}
