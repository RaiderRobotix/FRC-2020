package com.raiderrobotix._2020

import com.raiderrobotix._2020.subsystems.Shooter
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.Sendable
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.ghrobotics.lib.wrappers.hid.mapControls
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
	private const val OPERATOR_JOYSTICK_PORT = 2
	private const val JOYSTICK_DEADBAND = 0.15
	
	// Joysticks
	
	val leftStick = Joystick(LEFT_JOYSTICK_PORT)
	val rightStick = Joystick(RIGHT_JOYSTICK_PORT)
	val operatorStick = Joystick(OPERATOR_JOYSTICK_PORT)
	
	init {
		operatorStick.mapControls {
			button(3) {
				changeOn {
					Shooter.speed = 0.5
				}
				changeOff {
					Shooter.speed = 0.0
				}
			}
		}
	}
	
	/**
	 * Down on Joystick is positive, up is negative
	 */
	val leftY: Double
		get() {
			val ret = leftStick.y
			return if (abs(ret) > JOYSTICK_DEADBAND) ret else 0.0
		}
	
	val rightY: Double
		get() {
			val ret = rightStick.y
			return if (abs(ret) > JOYSTICK_DEADBAND) ret else 0.0
		}
	
	val operatorY: Double
		get() = operatorStick.y
	
	val operatorTrigger: Boolean
		get() = operatorStick.trigger
	
}
