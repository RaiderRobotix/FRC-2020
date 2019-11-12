package com.raiderrobotix._2020.util;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class Vision {

	public static final Vision eyes = new Vision();

	private final CameraServer cameraServer = CameraServer.getInstance();
	private final UsbCamera camera = cameraServer.startAutomaticCapture("FrontCam", 0);

	private final int img_height = 180;
	private final int img_width = 320;
	private final int fps = 30;

	private Vision() {
		camera.setResolution(img_width, img_height);
		camera.setFPS(fps);
	}

}