package com.raiderrobotix._2020.commands.drivebase;

import static com.raiderrobotix._2020.subsystems.DriveBase.drives;

import com.raiderrobotix._2020.commands.Command;

public class Turn extends Command {

	private static final double TURN_TOLERANCE = 1.0; // TODO

	private final double angle;
	private final double speed;

	public Turn(double angle, double speed) {
		requires(drives);
		this.angle = angle;
		this.speed = Math.copySign(speed, angle);
	}

	@Override
	protected void initialize() {
		drives.resetGyro();
	}

	@Override
	protected void execute() {
		drives.setSpeed(speed, -speed);
	}

	@Override
	protected void end() {
		drives.setSpeed(0);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(drives.getGyroAngle() - angle) < TURN_TOLERANCE;
	}

}