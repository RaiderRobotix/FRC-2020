package com.raiderrobotix._2020.commands

import com.raiderrobotix._2020.util.WheelColor
import org.team2471.frc.lib.framework.use

suspend fun turnPanelToColor(color: WheelColor) = use {
	while (WheelColor.color != color) {
		// Turn wheel
	}
}