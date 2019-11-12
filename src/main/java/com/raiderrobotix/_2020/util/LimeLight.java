package com.raiderrobotix._2020.util;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class LimeLight implements Sendable {
	public static final LimeLight camera = new LimeLight();

	private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	private final NetworkTableEntry x = table.getEntry("tx");
	private final NetworkTableEntry y = table.getEntry("ty");
	private final NetworkTableEntry area = table.getEntry("ta");
	private final NetworkTableEntry validTargets = table.getEntry("tv");
	private final NetworkTableEntry camMode = table.getEntry("camMode");
	private final NetworkTableEntry ledMode = table.getEntry("ledMode");

	private LimeLight() {
	}

	/**
	 * @return Horizontal Offset From Crosshair To Target
	 */
	public final double x() {
		return x.getDouble(0);
	}

	/**
	 * @return Vertical Offset From Crosshair To Target
	 */
	public final double y() {
		return y.getDouble(0);
	}

	/**
	 * @return Target Area (0% of image to 100% of image)
	 */
	public final double targetArea() {
		return area.getDouble(0);
	}

	/**
	 * @return Whether the limelight has any valid targets
	 */
	public final boolean targetFound() {
		return validTargets.getDouble(0) == 0.0;
	}

	public final boolean isProcessing() {
		return camMode.getDouble(0) == 0;
	}

	public String ledMode() {
		switch ((int) ledMode.getDouble(0)) {
		case 0:
			return "default";
		case 1:
			return "off";
		case 2:
			return "blink";
		case 3:
			return "on";
		default:
			throw new IllegalStateException("The limelight reported an illegal LED Mode");
		}
	}

	public void ledMode(String mode) {
		switch (mode) {
		case "default":
			ledMode.setDouble(0);
			break;
		case "off":
			ledMode.setDouble(1);
			break;
		case "blink":
			ledMode.setDouble(2);
			break;
		case "on":
			ledMode.setDouble(3);
			break;
		default:
			throw new IllegalArgumentException("An illegal LED Mode was passed");
		}
	}

	public void initSendable(SendableBuilder builder) {
		builder.addBooleanProperty("Targetting?", this::targetFound, null);
		builder.addDoubleProperty("x", this::x, null);
		builder.addDoubleProperty("y", this::y, null);
		builder.addDoubleProperty("Area", this::targetArea, null);
	}

	@Override
	public String getName() {
		return "Camera";
	}

	@Override
	public void setName(String name) {
	}

	@Override
	public String getSubsystem() {
		return null;
	}

	@Override
	public void setSubsystem(String subsystem) {
	}

}