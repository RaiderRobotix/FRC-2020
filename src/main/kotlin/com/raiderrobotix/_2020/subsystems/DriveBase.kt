package com.raiderrobotix._2020.subsystems

import com.kauailabs.navx.frc.AHRS
import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel.MotorType
import edu.wpi.first.wpilibj.SerialPort
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.Subsystem

object DriveBase : Subsystem(name = "Drives") {

    private const val LEFT_FRONT_DRIVE_CAN_ID = 1
    private const val LEFT_BACK_DRIVE_CAN_ID = 2
    private const val RIGHT_FRONT_DRIVE_CAN_ID = 4
    private const val RIGHT_BACK_DRIVE_CAN_ID = 3

    private val leftFrontSpark = CANSparkMax(LEFT_FRONT_DRIVE_CAN_ID, MotorType.kBrushless)
    private val leftBackSpark = CANSparkMax(LEFT_BACK_DRIVE_CAN_ID, MotorType.kBrushless)
    private val rightFrontSpark = CANSparkMax(RIGHT_FRONT_DRIVE_CAN_ID, MotorType.kBrushless)
    private val rightBackSpark = CANSparkMax(RIGHT_BACK_DRIVE_CAN_ID, MotorType.kBrushless)

    private const val TIRE_CIRCUMFERENCE = 9 * Math.PI // TODO
    private const val GEAR_RATIO = 0.047619 // 0.0714286; // TODO
    private const val INCHES_PER_REVOLUTION = GEAR_RATIO * TIRE_CIRCUMFERENCE
    private const val RIGHT_DRIVE_MOTORS_INVERTED = true
    private const val LEFT_DRIVE_MOTORS_INVERTED = false

    private val leftEncoder = leftFrontSpark.encoder
    private val rightEncoder = rightFrontSpark.encoder

    val navX = AHRS(SerialPort.Port.kUSB1)

    init {
        leftFrontSpark.inverted = LEFT_DRIVE_MOTORS_INVERTED
        rightFrontSpark.inverted = RIGHT_DRIVE_MOTORS_INVERTED
        leftBackSpark.follow(leftFrontSpark)
        rightBackSpark.follow(rightFrontSpark)
        leftEncoder.positionConversionFactor = INCHES_PER_REVOLUTION
        rightEncoder.positionConversionFactor = INCHES_PER_REVOLUTION
    }

    val distance: Double
        get() = (leftDistance + rightDistance) / 2.0

    private val leftDistance: Double
        get() = leftEncoder.position

    private val rightDistance: Double
        get() = rightEncoder.position

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
            SmartDashboard.putNumber("Left Encoder", leftEncoder.position)
            SmartDashboard.putNumber("Right Encoder", rightEncoder.position)
            SmartDashboard.putNumber("NAVX YAW", navX.angle)
        }
    }

    override fun reset() {
        speed = 0.0
    }
}
