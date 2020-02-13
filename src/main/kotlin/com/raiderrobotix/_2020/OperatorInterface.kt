package com.raiderrobotix._2020

import com.raiderrobotix._2020.subsystems.Intake
import com.raiderrobotix._2020.subsystems.Shooter
import com.raiderrobotix._2020.subsystems.Elevator
import com.raiderrobotix._2020.subsystems.Trolley
import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.Sendable
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.ghrobotics.lib.wrappers.hid.FalconHID
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
	private const val RIGHT_JOYSTICK_PORT = 1
	private const val OPERATOR_JOYSTICK_PORT = 3
	private const val JOYSTICK_DEADBAND = 0.15
	
	// Joysticks
	
	private val left = Joystick(LEFT_JOYSTICK_PORT)
	private val right = Joystick(RIGHT_JOYSTICK_PORT)
	private val operator = Joystick(OPERATOR_JOYSTICK_PORT)


	init {
		operatorStick.mapControls {
			button(1) {
				changeOn {
					Shooter.speed = 0.2
				}
				changeOff {
					Shooter.speed = 0.0
				}
			}
			button(2) {
				changeOn {
					Intake.speed = 0.2
				}
				changeOff {
					Intake.speed = 0.0
				}
			}
			
		}
		
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
	
	operator fun Joystick.get(button: Int) = getRawButton(button)

	operator fun <T : GenericHID> FalconHID<T>.get(button: Int) = getRawButton(button)
	
}
