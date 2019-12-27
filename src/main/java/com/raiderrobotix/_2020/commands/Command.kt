package com.raiderrobotix._2020.commands

import edu.wpi.first.wpilibj.command.Command

abstract class Command : Command() {
	abstract override fun initialize()
	abstract override fun execute()
	abstract override fun end()
}