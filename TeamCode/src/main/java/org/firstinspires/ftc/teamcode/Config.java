package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class Config {
	// FIXME: heading incorrect

	/**
	 * Logo facing direction of the hub, used to configure the IMU.
	 */
	public static final RevHubOrientationOnRobot.LogoFacingDirection LOGO_DIRECTION = RevHubOrientationOnRobot.LogoFacingDirection.UP;
	/**
	 * USB port facing direction of the hub, used to configure the IMU.
	 */
	public static final RevHubOrientationOnRobot.UsbFacingDirection USB_DIRECTION = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;

	/**
	 * Percent increase in strafing, used to counteract friction and imperfect strafing.
	 */
	public static final double STRAFING_MODIFIER = 1.1;

	/**
	 * Format string, used to format all floats/doubles/other decimal numbers.
	 */
	public static final String DECIMAL_FORMAT = "%.2f";
}
