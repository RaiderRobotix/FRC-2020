package com.raiderrobotix._2020.subsystems

import edu.wpi.first.wpilibj.Counter
import edu.wpi.first.wpilibj.DigitalInput
import edu.wpi.first.wpilibj.Spark
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.Subsystem

object Intake : Subsystem("Shooter") {
    private const val topChannel = 2
    private const val bottomChannel = 3
    private const val outerChannel = 4

    internal val upper = Spark(topChannel)
    internal val lower = Spark(bottomChannel)
    internal val outer = Spark(outerChannel)

    class Digi(val port: Int) {
        val input = DigitalInput(port)
        val counter = Counter(input)
    }

    val IntakeBreaker = Digi(5)

    val StageBreaker = Digi(3)

    val ShooterBreaker = Digi(0)

    init {
        lower.inverted = true
    }

    var speed: Double
        set(it) {
            upper.set(it)
            lower.set(it)
        }
        get() = (upper.get() + lower.get()) / 2

    override fun reset() {
        speed = 0.0
        outer.speed = 0.0
    }

    override suspend fun default() {
        periodic {
            SmartDashboard.putBoolean("ShooterBreaker", ShooterBreaker.input.get())
            SmartDashboard.putBoolean("StageBreaker", StageBreaker.input.get())
            SmartDashboard.putBoolean("IntakeBreaker", IntakeBreaker.input.get())
        }
    }
}
