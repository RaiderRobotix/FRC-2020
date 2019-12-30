package com.raiderrobotix._2020.util

import edu.wpi.first.cameraserver.CameraServer

object Vision {
	
	private val cameraServer = CameraServer.getInstance()
	private val camera = cameraServer.startAutomaticCapture("FrontCam", 0)
	
	private const val img_height = 180
	private const val img_width = 320
	private const val fps = 30
	
	init {
		camera.setResolution(img_width, img_height)
		camera.setFPS(fps)
	}
	
}