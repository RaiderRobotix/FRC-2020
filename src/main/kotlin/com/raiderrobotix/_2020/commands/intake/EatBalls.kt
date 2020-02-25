package com.raiderrobotix._2020.commands.intake

import com.raiderrobotix._2020.subsystems.Intake
import kotlinx.coroutines.GlobalScope
import org.team2471.frc.lib.coroutines.meanlibLaunch
import org.team2471.frc.lib.coroutines.periodic

fun eatBalls() = GlobalScope.meanlibLaunch {
	try {
		periodic {
			Intake.speed = 0.6
		}
	} finally {
		Intake.reset()
	}
}