package com.raiderrobotix._2020.commands.drivebase;

import static com.raiderrobotix._2020.subsystems.DriveBase.drives;

import com.raiderrobotix._2020.commands.Command;

public class Drive extends Command {

	private static final double ANGLE_TOLERANCE = 1.0; // TODO
	private static final double SPEED_CORRECTION = 0.10;// TODO
	private static final double DISTANCE_TOLERANCE = 1.0;// TODO

	private final double distance;
	private final double speed;

	public Drive(double distance, double speed) {
		requires(drives);
		this.distance = distance;
		this.speed = Math.copySign(speed, distance);
	}

	@Override
	protected void initialize() {
		drives.resetEncoders();
	}

	@Override
	protected void execute() {
		double leftSpeed = speed;
		double rightSpeed = speed;

		if (Math.abs(drives.getGyroAngle()) > ANGLE_TOLERANCE) { // Adjust speeds for in case of veering
			if (drives.getGyroAngle() > 0) { // Too far clockwise
				if (distance > 0)
					leftSpeed -= SPEED_CORRECTION;
				else
					rightSpeed += SPEED_CORRECTION;
			} else { // Too far counterclockwise
				if (distance > 0)
					rightSpeed -= SPEED_CORRECTION;
				else
					leftSpeed += SPEED_CORRECTION;
			}
		}
		drives.setSpeed(leftSpeed, rightSpeed);

	}

	@Override
	protected void end() {
		drives.setSpeed(0);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(drives.getAverageDistance() - distance) < DISTANCE_TOLERANCE;
	}

}