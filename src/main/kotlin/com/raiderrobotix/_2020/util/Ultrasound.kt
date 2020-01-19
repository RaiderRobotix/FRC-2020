package com.raiderrobotix._2020.util

import edu.wpi.first.wpilibj.AnalogInput
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.team2471.frc.lib.coroutines.periodic

val ultrasound = AnalogInput(0)

suspend fun updateDistance() = periodic(0.05) {
    SmartDashboard.putNumber("Ultrasound", ultrasound.voltage)
    // println("Ultrasound: ${ultrasound.voltage / 9.77}")
}

