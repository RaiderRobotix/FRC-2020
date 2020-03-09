package com.raiderrobotix._2020.commands.shooter

import com.raiderrobotix._2020.subsystems.Shooter
import kotlin.math.abs
import kotlin.math.sign
import org.team2471.frc.lib.coroutines.periodic

suspend fun adjustCowl(potentiometerReading: Double) {
    val tolerance = 0.01
    val speed = 0.4 * sign(potentiometerReading - Shooter.Potentiometer())
    periodic {
        if (abs(Shooter.Potentiometer() - potentiometerReading) > tolerance) {
            Shooter.cowlSpeed = speed
        } else {
            Shooter.cowlSpeed = 0.0
        }
    }
}
