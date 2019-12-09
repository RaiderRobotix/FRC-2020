package com.raiderrobotix._2020.commands

import edu.wpi.first.wpilibj.command.CommandGroup

/**
 * @author Hybras
 */
abstract class CommandGroup() : CommandGroup() {

    protected operator fun Command.unaryPlus() {
        addSequential(this)
    }

 	protected operator fun Command.not(){
        addParallel(this)
    }
}
