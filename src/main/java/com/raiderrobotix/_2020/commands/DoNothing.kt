package com.raiderrobotix._2020.commands

class DoNothing : Command() {
	
	override fun initialize() {}
	
	override fun execute() {}
	
	override fun end() {}
	
	override fun isFinished(): Boolean = false
	
}