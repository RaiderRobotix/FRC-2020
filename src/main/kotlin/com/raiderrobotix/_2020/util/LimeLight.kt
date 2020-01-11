package com.raiderrobotix._2020.util

import edu.wpi.first.networktables.NetworkTableInstance
import edu.wpi.first.wpilibj.Sendable
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder

object LimeLight : Sendable {
	
	private object Table {
		private val table = NetworkTableInstance.getDefault().getTable("limelight")!!
		val tx = table.getEntry("tx")!!
		val ty = table.getEntry("ty")!!
		val ta = table.getEntry("ta")!!
		val tv = table.getEntry("tv")!!
		val camMode = table.getEntry("camMode")!!
		val ledMode = table.getEntry("ledMode")!!
	}
	
	/**
	 * @return Horizontal Offset From Crosshair To Target
	 */
	val x: Double get() = Table.tx.getDouble(0.0)
	
	/**
	 * @return Vertical Offset From Crosshair To Target
	 */
	val y: Double get() = Table.ty.getDouble(0.0)
	
	/**
	 * @return Target Area (0% of image to 100% of image)
	 */
	val targetArea: Double get() = Table.ta.getDouble(0.0)
	
	/**
	 * @return Whether the limelight has any valid targets
	 */
	val targetFound: Boolean get() = Table.tv.getDouble(0.0) == 0.0
	
	val processing: Boolean get() = Table.camMode.getDouble(0.0) == 0.0
	
	enum class LedMode(internal val value: Int) {
		default(0), off(1), blink(2), on(3)
	}
	
	var ledMode: LedMode
		get() = LedMode.values()[Table.ledMode.getDouble(0.0).toInt()]
		set(mode) {
			Table.ledMode.setDouble(mode.value.toDouble())
		}
	
	override fun initSendable(builder: SendableBuilder) {
		builder.addBooleanProperty("Targeting", ::targetFound, null)
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