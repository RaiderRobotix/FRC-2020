package com.raiderrobotix._2020.commands

/**
 * @author Hybras
 */
@DslMarker
annotation class CommandGrouping

typealias commandClause = CommandGroup.() -> Unit
typealias wpiCommand = edu.wpi.first.wpilibj.command.Command
typealias wpiCommandGroup = edu.wpi.first.wpilibj.command.CommandGroup


@CommandGrouping
sealed class CommandGroup(commands: commandClause) : wpiCommandGroup() {
	internal abstract operator fun Command.unaryPlus()
	
	init {
		commands()
	}
	
}

open class sequential(commands: commandClause) : CommandGroup(commands) {
	override fun Command.unaryPlus() = addSequential(this)
}

open class parallel(commands: commandClause) : CommandGroup(commands) {
	override fun Command.unaryPlus() = addParallel(this)
}
