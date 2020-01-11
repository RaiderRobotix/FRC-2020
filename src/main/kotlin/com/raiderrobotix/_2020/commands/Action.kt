package com.raiderrobotix._2020.commands

import kotlinx.coroutines.Job

interface Action {
	suspend operator fun invoke(): Job
}