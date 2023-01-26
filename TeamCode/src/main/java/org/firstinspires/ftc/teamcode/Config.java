package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class Config {
	// Driving
	// FIXME: heading incorrect
	public static final RevHubOrientationOnRobot.LogoFacingDirection LOGO_DIRECTION = RevHubOrientationOnRobot.LogoFacingDirection.UP;
	public static final RevHubOrientationOnRobot.UsbFacingDirection USB_DIRECTION = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
	public static final double STRAFING_MODIFIER = 1.1;

	// Telemetry
	public static final String DECIMAL_FORMAT = "%.2f";
}
