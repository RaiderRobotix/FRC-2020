package com.raiderrobotix._2020

import com.raiderrobotix._2020.commands.colorwheel.positionControl
import com.raiderrobotix._2020.subsystems.ColorWheel
import com.raiderrobotix._2020.subsystems.ColorWheel.zeroOutColor
import com.raiderrobotix._2020.subsystems.Intake
import com.raiderrobotix._2020.subsystems.Shooter
import com.raiderrobotix._2020.subsystems.Trolley
import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.Joystick
import org.ghrobotics.lib.wrappers.hid.FalconHID
import org.team2471.frc.lib.input.whenTrue
import org.team2471.frc.lib.input.whileTrue
import kotlin.math.abs

object OperatorInterface {
	
	private const val LEFT_JOYSTICK_PORT = 0
	private const val RIGHT_JOYSTICK_PORT = 1
	private const val OPERATOR_JOYSTICK_PORT = 2
	private const val JOYSTICK_DEADBAND = 0.15
	
	// Joysticks
	
	internal val left = Joystick(LEFT_JOYSTICK_PORT)
	internal val right = Joystick(RIGHT_JOYSTICK_PORT)
	internal val operator = Joystick(OPERATOR_JOYSTICK_PORT)
	
	
	init {
		val flip = 2
		
		val shooter = 1
		({ operator[shooter] }).whenTrue { Shooter.speed = 1.0 }
		({ !operator[shooter] }).whenTrue { Shooter.reset() }
		
		val conveyer = 11
		({ operator[conveyer] && !operator[flip] }).whenTrue { Intake.speed = 1.0 }
		({ operator[conveyer] && operator[flip] }).whenTrue { Intake.speed = -0.5 }
		({ !operator[conveyer] }).whenTrue { Intake.reset() }
		
		val roller = 12
		({ operator[roller] && !operator[flip] }).whenTrue {
			Intake.outer.speed = 0.6
		}
		({ operator[roller] && operator[flip] }).whenTrue {
			Intake.outer.speed = -0.7
		}
		({ !operator[roller] }).whenTrue { Intake.reset() }
		
		val trolley = 7
		({ operator[trolley] && !operator[flip] }).whenTrue { Trolley.speed = 1.0 }
		({ operator[trolley] && operator[flip] }).whenTrue { Trolley.speed = -1.0 }
		({ !operator[trolley] }).whenTrue { Trolley.reset() }
		
		//Cowl
		val cowl = 5
		({ operator[cowl] }).whenTrue { Shooter.cowlSpeed = 0.5 }
		({ operator[3] }).whenTrue { Shooter.cowlSpeed = -0.5 }
		({ !operator[cowl] && !operator[3] }).whenTrue { Shooter.cowlSpeed = 0.0 }
		
		//Turn to Color
		val colorWheel = 10
		({ right[colorWheel] }).whileTrue {
			positionControl()
		}
		({ !right[colorWheel] }).whenTrue { ColorWheel.reset() }
		
		({ right[11] }).whenTrue { zeroOutColor(iter = 20) }
		
		
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
		get() = operator.y.takeIf { abs(it) > JOYSTICK_DEADBAND }
			?: 0.0
	
	val operatorTrigger: Boolean
		get() = operator.trigger
	
	operator fun Joystick.get(button: Int) = getRawButton(button)

	operator fun <T : GenericHID> FalconHID<T>.get(button: Int) = getRawButton(button)
	
}
