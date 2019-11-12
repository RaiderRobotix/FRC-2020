package com.raiderrobotix._2020.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Override getCommand() to return a 2 dimensional array. Commands within the
 * same 2nd dimension execute in parallel.
 * <p>
 * Example : In {{first},{second,third}}, second and third execute in parallel.
 * 
 * @author Hybras
 */
public abstract class CommandGroup extends edu.wpi.first.wpilibj.command.CommandGroup {

	protected CommandGroup() {
		for (Command[] parallelGroup : getCommands()) {
			if (parallelGroup.length == 1) {
				addSequential(parallelGroup[0]);
			} else {
				for (var command : parallelGroup) {
					addParallel(command);
				}
			}
		}
	}

	protected abstract Command[][] getCommands();
}