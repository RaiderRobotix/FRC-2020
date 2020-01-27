package com.raiderrobotix._2020.util

import com.revrobotics.ColorSensorV3
import edu.wpi.first.wpilibj.I2C
import edu.wpi.first.wpilibj.util.Color

import org.team2471.frc.lib.coroutines.periodic
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard




val colorSensor = ColorSensorV3(I2C.Port.kOnboard)

suspend fun printColor() = periodic {
	SmartDashboard.putString("Color",colorSensor.color.toString())
}
