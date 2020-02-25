package com.raiderrobotix._2020.subsystems

import com.raiderrobotix._2020.OperatorInterface
import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import edu.wpi.first.wpilibj.Counter
import edu.wpi.first.wpilibj.SpeedControllerGroup
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.team2471.frc.lib.coroutines.periodic
import org.team2471.frc.lib.framework.Subsystem


object Elevator : Subsystem("Elevator") {
	private const val left_id = 6
	private const val right_id = 5
	
	private val left = CANSparkMax(left_id, CANSparkMaxLowLevel.MotorType.kBrushless)
	private val right = CANSparkMax(right_id, CANSparkMaxLowLevel.MotorType.kBrushless)
	
	private val group = SpeedControllerGroup(left, right)
	private val left_encoder = left.encoder
	private val right_encoder = right.encoder

//	private const val limitDIO = 1 // TODO
//	private val counter = Counter(DigitalInput(limitDIO))
//	private var count = 0
//	private const val limitEnabled = false
	
	operator fun Counter.invoke() = get()
	
	init {
		left.inverted = true
		left_encoder.position = 0.0
		right_encoder.position = 0.0
	}
	
	private const val max_height = 10000 // Todo
	
	private val height get() = (left_encoder.position + right_encoder.position) / 2
	
	var speed: Double
		set(new_speed) {
			group.set(
//				if (limitEnabled && count < counter()) {
//					count = counter()
//					new_speed.coerceAtLeast(0.0)
//				} else
				new_speed)
		}
		get() = group.get()
	
	override fun reset() {
		speed = 0.0
	}
	
	override suspend fun default() {
		periodic {
			SmartDashboard.putString("Voltage", "Left: ${left.busVoltage}, Right: ${right.busVoltage}")
			speed = -OperatorInterface.operatorY
		}
	}
}
