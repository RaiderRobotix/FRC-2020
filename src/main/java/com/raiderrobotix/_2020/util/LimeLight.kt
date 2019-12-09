package com.raiderrobotix._2020.util

import edu.wpi.first.networktables.NetworkTableInstance
import edu.wpi.first.wpilibj.Sendable
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder
import java.util.function.BooleanSupplier
import java.util.function.DoubleSupplier

object LimeLight: Sendable {
	
	private val table = NetworkTableInstance.getDefault().getTable("limelight")
	private val _x = table.getEntry("tx")
	private val _y = table.getEntry("ty")
	private val _area = table.getEntry("ta")
	private val _validTargets = table.getEntry("tv")
	private val _camMode = table.getEntry("camMode")
	private val _ledMode = table.getEntry("ledMode")
	
	/**
	 * @return Horizontal Offset From Crosshair To Target
	 */
	val x:Double
		get() = _x.getDouble(0.0)

	/**
	 * @return Vertical Offset From Crosshair To Target
	 */
	val y: Double
		get() =  _y.getDouble(0.0)
	

	/**
	 * @return Target Area (0% of image to 100% of image)
	 */
	val targetArea: Double
		get() = _area.getDouble(0.0)
	

	/**
	 * @return Whether the limelight has any valid targets
	 */
	val targetFound: Boolean
		get() = _validTargets.getDouble(0.0) == 0.0

	val isProcessing: Boolean
		get() = _camMode.getDouble(0.0) == 0.0

	var ledMode: String
		get() {
			return when (_ledMode.getDouble(0.0).toInt()) {
				0 -> "default"
				1 -> "off"
				2 -> "blink"
				3 -> "on"
				else -> throw IllegalStateException("The limelight reported an illegal LED Mode")
			}
		}
		set(mode) {
			when (mode) {
				"default" -> _ledMode.setDouble(0.0)
				"off" -> _ledMode.setDouble(1.0)
				"blink" -> _ledMode.setDouble(2.0)
				"on" -> _ledMode.setDouble(3.0)
				else -> throw IllegalArgumentException("An illegal LED Mode was passed")
			}
		}

	override fun initSendable(builder: SendableBuilder) {
		builder.addBooleanProperty("Targeting", ::targetFound , null)
		builder.addDoubleProperty("x", ::x, null)
		builder.addDoubleProperty("y", ::y, null)
		builder.addDoubleProperty("Area", ::targetArea, null)
	}
	
	private const val name = "Camera"
	override fun setName(name: String) {}
	override fun getName(): String = name
	override fun setSubsystem(subsystem: String) {}
	override fun getSubsystem(): String = name
	
}