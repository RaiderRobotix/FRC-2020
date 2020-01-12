package com.raiderrobotix._2020;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OperatorInterface implements Sendable {

	public static final OperatorInterface input = new OperatorInterface();

	private static final int LEFT_JOYSTICK_PORT = 0, RIGHT_JOYSTICK_PORT = 1, OPERATOR_JOYSTICK_PORT = 2;

	private static final double JOYSTICK_DEADBAND = 0.15;

	// Joysticks
	private static final Joystick leftStick = new Joystick(LEFT_JOYSTICK_PORT);
	private static final Joystick rightStick = new Joystick(RIGHT_JOYSTICK_PORT);
	private static final Joystick operatorStick = new Joystick(OPERATOR_JOYSTICK_PORT);

	private static final JoystickButton[] operator = new JoystickButton[12];
	private static final JoystickButton[] left = new JoystickButton[12];
	private static final JoystickButton[] right = new JoystickButton[12];

	static {
		for (int button = 0; button < 12; button++) {
			left[button] = new JoystickButton(leftStick, button);
			right[button] = new JoystickButton(rightStick, button);
			operator[button] = new JoystickButton(operatorStick, button);
		}
	}

	private OperatorInterface() {
		SmartDashboard.putData(this);
	}

	public static double getLeftY() {
		double ret = leftStick.getY();
		return Math.abs(ret) > JOYSTICK_DEADBAND ? ret : 0.0;
	}

	public static double getRightY() {
		double ret = rightStick.getY();
		return Math.abs(ret) > JOYSTICK_DEADBAND ? ret : 0.0;
	}

	/**
	 * Down on Joystick is positive, up is negative
	 */
	public static double getOperatorY() {
		return operatorStick.getY();
	}

	public static boolean getLeftButton(int button) {
		return leftStick.getRawButton(button);
	}

	public static boolean getRightButton(int button) {
		return rightStick.getRawButton(button);
	}

	public static boolean getOperatorButton(int button) {
		return operatorStick.getRawButton(button);
	}

	public static boolean getOperatorTrigger() {
		return operatorStick.getTrigger();
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		builder.addDoubleProperty("lY", OperatorInterface::getLeftY, null);
		builder.addDoubleProperty("rY", OperatorInterface::getRightY, null);
		builder.addDoubleProperty("oY", OperatorInterface::getOperatorY, null);
	}
}
