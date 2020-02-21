package com.raiderrobotix._2020.commands

import com.raiderrobotix._2020.subsystems.ColorWheel
import com.raiderrobotix._2020.subsystems.ColorWheel.WheelColor
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.use

suspend fun turnPanelToColor(color: WheelColor = ColorWheel.color!!) = use {
	periodic {
		if (ColorWheel.color != color)
			ColorWheel.wheel.speed = 0.5
		else stop()
	}
}
