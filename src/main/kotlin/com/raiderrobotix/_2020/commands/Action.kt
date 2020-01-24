package com.raiderrobotix._2020.commands

interface Action {
	suspend operator fun invoke()
}
