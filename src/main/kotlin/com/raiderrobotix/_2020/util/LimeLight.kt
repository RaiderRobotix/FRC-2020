package com.raiderrobotix._2020.util

import edu.wpi.first.wpilibj.Sendable
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry
import org.ghrobotics.lib.wrappers.networktables.FalconNetworkTable
import org.ghrobotics.lib.wrappers.networktables.get

object LimeLight : Sendable {
	
	init {
		SendableRegistry.add(this, "LimeLight")
	}
	
	private val table = FalconNetworkTable.getTable("limelight")
	
	/**
	 * @return Horizontal Offset From Crosshair To Target
	 */
	val x by table["tx"](default = 0.0)
	
	/**
	 * @return Vertical Offset From Crosshair To Target
	 */
	val y by table["ty"](default = 0.0)
	
	/**
	 * @return Target Area (0% of image to 100% of image)
	 */
	val targetArea by table["ta"](default = 0.0)
	
	private val tv by table["tv"](default = 0.0)
	
	/**
	 * @return Whether the limelight has any valid targets
	 */
	val targetFound get() = tv == 0.0
	
	private var camMode by table["camMode"](default = 0.0)
	
	var processing
		get() = camMode == 0.0
		set(it) {
			camMode = if (it) 0.0 else 1.0
		}
	
	enum class LedMode(internal val value: Int) {
		default(0), off(1), blink(2), on(3)
	}
	
	private var ledModeDouble by table["ledMode"](default = 0.0)
	
	var ledMode
		get() = LedMode.values()[ledModeDouble.toInt()]
		set(mode) {
			ledModeDouble = mode.value.toDouble()
		}
	
	var pipeLine by table["pipeline"](default = 0.0)
	
	override fun initSendable(builder: SendableBuilder) {
		builder.addBooleanProperty("Targeting", ::targetFound, null)
		builder.addDoubleProperty("x", ::x, null)
		builder.addDoubleProperty("y", ::y, null)
		builder.addDoubleProperty("Area", ::targetArea, null)
	}
	
}
