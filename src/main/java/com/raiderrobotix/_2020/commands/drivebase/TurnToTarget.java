package com.raiderrobotix._2020.commands.drivebase;

import static com.raiderrobotix._2020.subsystems.DriveBase.drives;
import static com.raiderrobotix._2020.util.LimeLight.camera;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class TurnToTarget extends PIDCommand {

	private static final double TURN_TOLERANCE = 1.0; // TODO

	public TurnToTarget() {
		super(0.1, 0, 0); // TODO
		requires(drives);
	}

	@Override
	protected void initialize() {
		drives.resetGyro();
	}

	@Override
	protected double returnPIDInput() {
		return drives.getGyroAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		drives.setSpeed(output, -output);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(camera.x()) < TURN_TOLERANCE;
	}

}