package com.raiderrobotix._2020.subsystems

import com.raiderrobotix._2020.OperatorInterface
import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel.MotorType
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.Subsystem


object DriveBase : Subsystem(name="Drives") {
	
	private const val LEFT_FRONT_DRIVE_CAN_ID = 1
	private const val LEFT_BACK_DRIVE_CAN_ID = 2
	private const val RIGHT_FRONT_DRIVE_CAN_ID = 4
	private const val RIGHT_BACK_DRIVE_CAN_ID = 3
	
	private val leftFrontSpark = CANSparkMax(LEFT_FRONT_DRIVE_CAN_ID, MotorType.kBrushless)
	private val leftBackSpark = CANSparkMax(LEFT_BACK_DRIVE_CAN_ID, MotorType.kBrushless)
	private val rightFrontSpark = CANSparkMax(RIGHT_FRONT_DRIVE_CAN_ID, MotorType.kBrushless)
	private val rightBackSpark = CANSparkMax(RIGHT_BACK_DRIVE_CAN_ID, MotorType.kBrushless)
	
	private const val TIRE_CIRCUMFERENCE = 28.375 // TODO
	private const val GEAR_RATIO = 0.047619 // 0.0714286; // TODO
	private const val INCHES_PER_REVOLUTION = GEAR_RATIO * TIRE_CIRCUMFERENCE
	private const val RIGHT_DRIVE_MOTORS_INVERTED = true
	private const val LEFT_DRIVE_MOTORS_INVERTED = false
	
	private val leftEncoder = leftFrontSpark.encoder
	private val rightEncoder = rightFrontSpark.encoder
	
	init {
		leftFrontSpark.inverted = LEFT_DRIVE_MOTORS_INVERTED
		rightFrontSpark.inverted = RIGHT_DRIVE_MOTORS_INVERTED
		leftBackSpark.follow(leftFrontSpark)
		rightBackSpark.follow(rightFrontSpark)
		leftEncoder.positionConversionFactor = INCHES_PER_REVOLUTION
		rightEncoder.positionConversionFactor = INCHES_PER_REVOLUTION
//		navX.subsystem = "DriveBase"
//		SmartDashboard.putData(this)
	}
	
	val averageDistance: Double // Rename to distance
		get() = (leftDistance + rightDistance) / 2.0
	
	private val leftDistance: Double
		get() = leftEncoder.position
	
	private val rightDistance: Double
		get() = rightEncoder.position
	
	//	val ultrasonicDistance: Double
//		get() = ultrasonic.voltage
//
	val gyroAngle: Double
		get() = 0.0 //navX.angle
	
	var speed: Double
		set(it) {
			tankDrive(it, it)
		}
		get() = (leftFrontSpark.get() + rightFrontSpark.get())
	
	
	fun tankDrive(leftSpeed: Double, rightSpeed: Double) {
		leftFrontSpark.set(leftSpeed)
		rightFrontSpark.set(rightSpeed)
	}

	override suspend fun default() {
			periodic {
				tankDrive(
					leftSpeed = -OperatorInterface.leftY,
					rightSpeed = -OperatorInterface.rightY
				)
			}
	}
	
	override fun reset() {
//		navX.reset()
		speed = 0.0
		leftEncoder.position = 0.0
		rightEncoder.position = 0.0
	}
	
//	override fun initSendable(builder: SendableBuilder) {
//		builder.setSmartDashboardType("Drives")
//		builder.setActuator(true)
//		builder.setSafeState { speed = 0.0 }
//		builder.addDoubleProperty("leftDistance", ::leftDistance, null)
//		builder.addDoubleProperty("rightDistance", ::rightDistance, null)
//		val firmwareSupplier = { arrayOf<String>("Left Front " + leftFrontSpark.firmwareString, "Left Back " + leftBackSpark.firmwareString, "Right Front" + rightFrontSpark.firmwareString, "Right Back " + rightBackSpark.firmwareString) }
//		builder.addStringArrayProperty("firmware", firmwareSupplier, null)
//		builder.addDoubleProperty("Gyro", ::gyroAngle, null)
//	}
	
}
