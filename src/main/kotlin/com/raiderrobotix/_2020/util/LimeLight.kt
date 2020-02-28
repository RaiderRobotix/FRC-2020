package com.raiderrobotix._2020.util

import org.ghrobotics.lib.wrappers.networktables.FalconNetworkTable
import org.ghrobotics.lib.wrappers.networktables.get

object LimeLight {
	
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
		// ORDER MATTERS, SEE ledMode.set()
		Default(0),
		Off(1), Blink(2), On(3)
	}
	
	private var ledModeDouble by table["ledMode"](default = 0.0)
	
	var ledMode
		get() = LedMode.values()[ledModeDouble.toInt()]
		set(mode) {
			ledModeDouble = mode.value.toDouble()
		}
	
	var pipeLine by table["pipeline"](default = 0.0)
	
}
