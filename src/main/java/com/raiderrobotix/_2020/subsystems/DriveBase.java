package com.raiderrobotix._2020.subsystems;

import java.util.function.Supplier;

import com.kauailabs.navx.frc.AHRS;
import com.raiderrobotix._2020.commands.Teleop;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase extends Subsystem {

	public static final DriveBase drives = new DriveBase();

	private static final int LEFT_FRONT_DRIVE_CAN_ID = 2;
	private static final int LEFT_BACK_DRIVE_CAN_ID = 1;
	private static final int RIGHT_FRONT_DRIVE_CAN_ID = 3;
	private static final int RIGHT_BACK_DRIVE_CAN_ID = 4;

	private static final CANSparkMax leftFrontSpark = new CANSparkMax(LEFT_FRONT_DRIVE_CAN_ID, MotorType.kBrushless);
	private static final CANSparkMax leftBackSpark = new CANSparkMax(LEFT_BACK_DRIVE_CAN_ID, MotorType.kBrushless);
	private static final CANSparkMax rightFrontSpark = new CANSparkMax(RIGHT_FRONT_DRIVE_CAN_ID, MotorType.kBrushless);
	private static final CANSparkMax rightBackSpark = new CANSparkMax(RIGHT_BACK_DRIVE_CAN_ID, MotorType.kBrushless);

	private static final double TIRE_CIRCUMFERENCE = 28.375; // TODO

	private static final double GEAR_RATIO = 0.047619; // 0.0714286; // TODO
	private static final double INCHES_PER_REVOLUTION = GEAR_RATIO * TIRE_CIRCUMFERENCE;

	private static final boolean RIGHT_DRIVE_MOTORS_INVERTED = true; // TODO
	private static final boolean LEFT_DRIVE_MOTORS_INVERTED = false; // TODO

	private static final CANEncoder leftEncoder = leftFrontSpark.getEncoder();
	private static final CANEncoder rightEncoder = rightFrontSpark.getEncoder();

	private static final AnalogInput ultrasonic = new AnalogInput(0);

	private static final AHRS navX = new AHRS(Port.kUSB1);

	private DriveBase() {
		leftFrontSpark.setInverted(LEFT_DRIVE_MOTORS_INVERTED);
		rightFrontSpark.setInverted(RIGHT_DRIVE_MOTORS_INVERTED);

		leftBackSpark.follow(leftFrontSpark);
		rightBackSpark.follow(rightFrontSpark);

		leftEncoder.setPositionConversionFactor(INCHES_PER_REVOLUTION);
		rightEncoder.setPositionConversionFactor(INCHES_PER_REVOLUTION);

		SmartDashboard.putData(this);
	}

	public void setSpeed(double speed) {
		setSpeed(speed, speed);
	}

	public void setSpeed(double leftSpeed, double rightSpeed) {
		leftFrontSpark.set(leftSpeed);
		rightFrontSpark.set(rightSpeed);
	}

	public double getAverageDistance() {
		return (getLeftDistance() + getRightDistance()) / 2.0;
	}

	public double getLeftDistance() {
		return leftEncoder.getPosition();
	}

	public double getRightDistance() {
		return rightEncoder.getPosition();
	}

	public void resetEncoders() {
		leftEncoder.setPosition(0);
		rightEncoder.setPosition(0);
	}

	public double getUltrasonicDistance() {
		return ultrasonic.getVoltage();
	}

	public double getGyroAngle() {
		return navX.getAngle();
	}

	public void resetGyro() {
		navX.reset();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Teleop());
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("Drives");
		builder.setActuator(true);
		builder.setSafeState(() -> this.setSpeed(0));
		builder.addDoubleProperty("leftDistance", this::getLeftDistance, null);
		builder.addDoubleProperty("rightDistance", this::getRightDistance, null);
		final Supplier<String[]> firmwareSupplier = () -> new String[] {
				"Left Front " + leftFrontSpark.getFirmwareString(), "Left Back " + leftBackSpark.getFirmwareString(),
				"Right Front" + rightFrontSpark.getFirmwareString(),
				"Right Back " + rightBackSpark.getFirmwareString() };

		builder.addStringArrayProperty("firmware", firmwareSupplier, null);
		builder.addDoubleProperty("Gyro", this::getGyroAngle, null);

	}

}
