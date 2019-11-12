package com.raiderrobotix._2020.commands;

import static com.raiderrobotix._2020.OperatorInterface.input;
import static com.raiderrobotix._2020.subsystems.DriveBase.drives;

import edu.wpi.first.wpilibj.command.Command;

public final class Teleop extends Command {

	public Teleop() {
		requires(drives);
	}

	@Override
	protected final void execute() {
		drives.setSpeed(-input.getLeftY(), -input.getRightY());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}