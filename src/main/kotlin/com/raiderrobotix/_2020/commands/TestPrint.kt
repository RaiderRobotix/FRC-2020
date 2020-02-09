package com.raiderrobotix._2020.commands

import com.raiderrobotix._2020.OperatorInterface
import kotlinx.coroutines.GlobalScope
import org.team2471.frc.lib.coroutines.meanlibLaunch
import org.team2471.frc.lib.coroutines.periodic

fun testPrint() = GlobalScope.meanlibLaunch {
    periodic {
        print("periodic")
        print("lY: ${OperatorInterface.rightY}")
        print("rY: ${OperatorInterface.leftY}")
    }
}