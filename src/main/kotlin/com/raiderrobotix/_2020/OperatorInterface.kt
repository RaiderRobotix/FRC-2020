package com.raiderrobotix._2020

import com.raiderrobotix._2020.subsystems.DriveBase
import com.raiderrobotix._2020.subsystems.Intake
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
			button(1) {
				changeOn {
					Shooter.speed = 1.0
				}
				changeOff {
					Shooter.speed = 0.0
				}
			}
			button(2) {
				changeOn {
					Intake.speed = 1.0
				}
				changeOff {
					Intake.speed = 0.0
				}
			}
			button(3) {
				changeOn {
					Intake.speed = -0.6
				}
				changeOff {
					Intake.speed = 0.0
				}
			}
			button(12) {
				changeOn {
					Intake.outer.speed = 0.5
				}
				changeOff {
					Intake.outer.speed = 0.0
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
	
	operator fun Joystick.get(button: Int) = this.getRawButton(button)
	
	fun manualControl() {
		DriveBase.tankDrive(
			leftSpeed = -leftY,
			rightSpeed = -rightY
		)
		
		Shooter.speed = if (operatorTrigger)
			1.0
		else
			0.0
		
		
		
		Intake.outer.speed = when {
			operatorStick[11] -> 0.8
			operatorStick[12] -> -0.7
			else -> 0.0
		}
		
		Intake.lower.speed = when {
			operatorStick[10] -> 1.0
			operatorStick[9] -> -0.6
			else -> 0.0
		}
		
		Intake.upper.speed = when {
			operatorStick[7] -> 1.0
			operatorStick[8] -> -0.6
			else -> 0.0
		}
	}
	
}
