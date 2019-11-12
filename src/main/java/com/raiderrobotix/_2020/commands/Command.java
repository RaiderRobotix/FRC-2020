package com.raiderrobotix._2020.commands;

public abstract class Command extends edu.wpi.first.wpilibj.command.Command {
	@Override
	protected abstract void initialize();

	@Override
	protected abstract void execute();

	@Override
	protected abstract void end();
}