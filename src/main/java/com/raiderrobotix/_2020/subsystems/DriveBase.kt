package com.raiderrobotix._2020.subsystems

import com.kauailabs.navx.frc.AHRS
import com.raiderrobotix._2020.commands.Teleop
import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel.MotorType
import edu.wpi.first.wpilibj.AnalogInput
import edu.wpi.first.wpilibj.PIDOutput
import edu.wpi.first.wpilibj.SerialPort.Port
import edu.wpi.first.wpilibj.command.Subsystem
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard

object DriveBase : Subsystem(), PIDOutput {
	
	private const val LEFT_FRONT_DRIVE_CAN_ID = 2
	private const val LEFT_BACK_DRIVE_CAN_ID = 1
	private const val RIGHT_FRONT_DRIVE_CAN_ID = 3
	private const val RIGHT_BACK_DRIVE_CAN_ID = 4
	
	private val leftFrontSpark = CANSparkMax(LEFT_FRONT_DRIVE_CAN_ID, MotorType.kBrushless)
	private val leftBackSpark = CANSparkMax(LEFT_BACK_DRIVE_CAN_ID, MotorType.kBrushless)
	private val rightFrontSpark = CANSparkMax(RIGHT_FRONT_DRIVE_CAN_ID, MotorType.kBrushless)
	private val rightBackSpark = CANSparkMax(RIGHT_BACK_DRIVE_CAN_ID, MotorType.kBrushless)
	
	private const val TIRE_CIRCUMFERENCE = 28.375 // TODO
	private const val GEAR_RATIO = 0.047619 // 0.0714286; // TODO
	private const val INCHES_PER_REVOLUTION = GEAR_RATIO * TIRE_CIRCUMFERENCE
	private const val RIGHT_DRIVE_MOTORS_INVERTED = true // TODO
	private const val LEFT_DRIVE_MOTORS_INVERTED = false // TODO
	
	private val leftEncoder = leftFrontSpark.encoder
	private val rightEncoder = rightFrontSpark.encoder
	private val ultrasonic = AnalogInput(0)
	private val navX = AHRS(Port.kUSB1)
	
	init {
		leftFrontSpark.inverted = LEFT_DRIVE_MOTORS_INVERTED
		rightFrontSpark.inverted = RIGHT_DRIVE_MOTORS_INVERTED
		leftBackSpark.follow(leftFrontSpark)
		rightBackSpark.follow(rightFrontSpark)
		leftEncoder.positionConversionFactor = INCHES_PER_REVOLUTION
		rightEncoder.positionConversionFactor = INCHES_PER_REVOLUTION
		navX.subsystem = "DriveBase"
		SmartDashboard.putData(this)
	}
	
	val averageDistance: Double
		get() = (leftDistance + rightDistance) / 2.0
	
	private val leftDistance: Double
		get() = leftEncoder.position
	
	private val rightDistance: Double
		get() = rightEncoder.position
	
	val ultrasonicDistance: Double
		get() = ultrasonic.voltage
	
	val gyroAngle: Double
		get() = navX.angle
	
	var speed: Double = 0.0
		set(it) {
			setSpeed(it, it)
			field = it
		}
	
	fun setSpeed(leftSpeed: Double, rightSpeed: Double) {
		leftFrontSpark.set(leftSpeed)
		rightFrontSpark.set(rightSpeed)
	}
	
	fun resetEncoders() {
		leftEncoder.position = 0.0
		rightEncoder.position = 0.0
	}
	
	fun resetGyro() = navX.reset()
	
	override fun initDefaultCommand() {
		defaultCommand = Teleop()
	}
	
	override fun initSendable(builder: SendableBuilder) {
		builder.setSmartDashboardType("Drives")
		builder.setActuator(true)
		builder.setSafeState { speed = 0.0 }
		builder.addDoubleProperty("leftDistance", ::leftDistance, null)
		builder.addDoubleProperty("rightDistance", ::rightDistance, null)
		val firmwareSupplier = { arrayOf<String>("Left Front " + leftFrontSpark.firmwareString, "Left Back " + leftBackSpark.getFirmwareString(), "Right Front" + rightFrontSpark.getFirmwareString(), "Right Back " + rightBackSpark.getFirmwareString()) }
		builder.addStringArrayProperty("firmware", firmwareSupplier, null)
		builder.addDoubleProperty("Gyro", ::gyroAngle, null)
	}
	
	override fun pidWrite(output: Double) = setSpeed(output, output)
	
}
