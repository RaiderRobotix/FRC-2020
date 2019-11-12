package com.raiderrobotix._2020.commands.drivebase;

import static com.raiderrobotix._2020.subsystems.DriveBase.drives;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class DrivePID extends PIDCommand {

	private static final double DISTANCE_TOLERANCE = 1.0;// TODO

	private final double distance;

	public DrivePID(double distance) {
		super(0.1, 0, 0); // TODO
		requires(drives);
		this.distance = distance;
	}

	@Override
	protected void initialize() {
		drives.resetEncoders();
	}

	@Override
	protected double returnPIDInput() {
		return distance / drives.getAverageDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		drives.setSpeed(output, output);
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