package com.raiderrobotix._2020.commands

import com.raiderrobotix._2020.subsystems.ColorWheel
import com.raiderrobotix._2020.util.WheelColor
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.use

suspend fun turnPanelToColor(color: WheelColor) = use {
	periodic {
		if (WheelColor.color != color)
		ColorWheel.wheel.set(0.5)
		else stop()
	}
}
