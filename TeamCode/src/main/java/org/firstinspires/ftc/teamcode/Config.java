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
	public static final RevHubOrientationOnRobot.UsbFacingDirection USB_DIRECTION = RevHubOrientationOnRobot.UsbFacingDirection.LEFT;

	/**
	 * Percent increase in strafing, used to counteract friction and imperfect strafing.
	 */
	public static final double STRAFING_MODIFIER = 1.1;

	/**
	 * Format string, used to format all floats/doubles/other decimal numbers.
	 */
	public static final String DECIMAL_FORMAT = "%.2f";

	/**
	 * Servo position for the closed claw, within range [0.0, 1.0]. Servos approach the center as the value approaches 1.
	 */
	public static final double CLAW_CLOSE_POSITION = 0.6;
	/**
	 * Servo position for the open claw, within range [0.0, 1.0] Servos approach the outside as the value approaches 0.
	 */
	public static final double CLAW_OPEN_POSITION = 0.1;

	/**
	 * Velocity to set the slide motor to, in ticks per second.
	 */
	public static final double SLIDE_VELOCITY = 500;
	/**
	 * Bottom limit to stop the slide at when lowering, in ticks.
	 */
	public static final int SLIDE_BOTTOM_LIMIT = 10;
}
