package com.raiderrobotix._2020.subsystems;

import java.util.function.Supplier;

import com.kauailabs.navx.frc.AHRS;
import com.raiderrobotix._2020.commands.Teleop;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase extends Subsystem implements PIDOutput {

	public static final DriveBase drives = new DriveBase();

	private final int LEFT_FRONT_DRIVE_CAN_ID = 2;
	private final int LEFT_BACK_DRIVE_CAN_ID = 1;
	private final int RIGHT_FRONT_DRIVE_CAN_ID = 3;
	private final int RIGHT_BACK_DRIVE_CAN_ID = 4;

	private final CANSparkMax leftFrontSpark = new CANSparkMax(LEFT_FRONT_DRIVE_CAN_ID, MotorType.kBrushless);
	private final CANSparkMax leftBackSpark = new CANSparkMax(LEFT_BACK_DRIVE_CAN_ID, MotorType.kBrushless);
	private final CANSparkMax rightFrontSpark = new CANSparkMax(RIGHT_FRONT_DRIVE_CAN_ID, MotorType.kBrushless);
	private final CANSparkMax rightBackSpark = new CANSparkMax(RIGHT_BACK_DRIVE_CAN_ID, MotorType.kBrushless);

	private final double TIRE_CIRCUMFERENCE = 28.375; // TODO

	private final double GEAR_RATIO = 0.047619; // 0.0714286; // TODO
	private final double INCHES_PER_REVOLUTION = GEAR_RATIO * TIRE_CIRCUMFERENCE;

	private final boolean RIGHT_DRIVE_MOTORS_INVERTED = true; // TODO
	private final boolean LEFT_DRIVE_MOTORS_INVERTED = false; // TODO

	private final CANEncoder leftEncoder = leftFrontSpark.getEncoder();
	private final CANEncoder rightEncoder = rightFrontSpark.getEncoder();

	private final AnalogInput ultrasonic = new AnalogInput(0);

	private final AHRS navX = new AHRS(Port.kUSB1);

	private DriveBase() {
		leftFrontSpark.setInverted(LEFT_DRIVE_MOTORS_INVERTED);
		rightFrontSpark.setInverted(RIGHT_DRIVE_MOTORS_INVERTED);

		leftBackSpark.follow(leftFrontSpark);
		rightBackSpark.follow(rightFrontSpark);

		leftEncoder.setPositionConversionFactor(INCHES_PER_REVOLUTION);
		rightEncoder.setPositionConversionFactor(INCHES_PER_REVOLUTION);

		navX.setSubsystem("DriveBase");

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
				"Right Back " + this.rightBackSpark.getFirmwareString() };

		builder.addStringArrayProperty("firmware", firmwareSupplier, null);
		builder.addDoubleProperty("Gyro", this::getGyroAngle, null);

	}

	@Override
	public void pidWrite(double output) {
		setSpeed(output);
	}

}