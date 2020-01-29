package com.raiderrobotix._2020.commands

import com.raiderrobotix._2020.util.WheelColor
import org.team2471.frc.lib.framework.use

class TurnWheelToColor(val color: WheelColor) : Action {
	override suspend fun invoke() = use {
		while (WheelColor.color != color) {
			// Turn wheel
		}
	}
}