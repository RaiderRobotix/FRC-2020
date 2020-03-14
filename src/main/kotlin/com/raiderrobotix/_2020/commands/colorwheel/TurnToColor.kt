package com.raiderrobotix._2020.commands.colorwheel

import com.raiderrobotix._2020.subsystems.ColorWheel
import com.raiderrobotix._2020.subsystems.ColorWheel.WheelColor
import com.raiderrobotix._2020.subsystems.ColorWheel.color
import edu.wpi.first.wpilibj.DriverStation
import kotlin.math.sign
import org.team2471.frc.lib.coroutines.suspendUntil

suspend fun positionControl() {
    val endColor = when (
        try {
            // The first character denotes the Color, its uppercase
            DriverStation.getInstance().gameSpecificMessage[0]
        } catch (e: Throwable) {
            error("Color not received from Driver Station")
        }) {
        'R' -> WheelColor.Red
        'B' -> WheelColor.Cyan
        'G' -> WheelColor.Green
        'Y' -> WheelColor.Yellow
        else -> error("Color not received from Driver Station")
    }
    // Keep decaying the speed till we got it
    var speed = 0.35
    while (speed != 0.0) {
        ColorWheel.wheel.speed = speed
        suspendUntil { color == endColor }
        speed = -speed + sign(speed) * 0.05
    }
}
