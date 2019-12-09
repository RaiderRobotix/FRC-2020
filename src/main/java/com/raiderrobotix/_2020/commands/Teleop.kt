package com.raiderrobotix._2020.commands

import com.raiderrobotix._2020.OperatorInterface
import com.raiderrobotix._2020.subsystems.DriveBase

class Teleop : Command() {

    init {
        requires(DriveBase)
    }

    override fun execute() {
        DriveBase.setSpeed(-OperatorInterface.leftY, -OperatorInterface.rightY)
    }

    override fun isFinished() = false

    override fun initialize() {
    }

    override fun end() {
        DriveBase.speed = 0.0
    }
}
