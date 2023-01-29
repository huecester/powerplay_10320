package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class Config {
	// FIXME: heading incorrect

	/**
	 * Logo facing direction of the hub, used to configure the IMU.
	 *
	 * @see Drive
	 */
	public static final RevHubOrientationOnRobot.LogoFacingDirection LOGO_DIRECTION = RevHubOrientationOnRobot.LogoFacingDirection.UP;
	/**
	 * USB port facing direction of the hub, used to configure the IMU.
	 *
	 * @see Drive
	 */
	public static final RevHubOrientationOnRobot.UsbFacingDirection USB_DIRECTION = RevHubOrientationOnRobot.UsbFacingDirection.LEFT;

	/**
	 * Percent increase in strafing, used to counteract friction and imperfect strafing.
	 *
	 * @see Drive
	 */
	public static final double STRAFING_MODIFIER = 1.1;
	/**
	 * Value to multiply speed by when using fine tune.
	 *
	 * @see Drive
	 */
	public static final double FINE_TUNE_MODIFIER = 1.0 / 4.0;

	/**
	 * Format string, used to format all floats/doubles/other decimal numbers.
	 */
	public static final String DECIMAL_FORMAT = "%.2f";

	/**
	 * Servo position for the closed claw, within range [0.0, 1.0]. Servos approach the center as the value approaches 1.
	 *
	 * @see Claw
	 */
	public static final double CLAW_CLOSE_POSITION = 0.7;
	/**
	 * Servo position for the open claw, within range [0.0, 1.0] Servos approach the outside as the value approaches 0.
	 *
	 * @see Claw
	 */
	public static final double CLAW_OPEN_POSITION = 0.1;

	/**
	 * Velocity to set the slide motor to, in ticks per second.
	 *
	 * @see Slide
	 */
	public static final double SLIDE_VELOCITY = 10000;
	/**
	 * Bottom limit to stop the slide at when lowering, in ticks.
	 *
	 * @see Slide
	 */
	public static final int SLIDE_BOTTOM_LIMIT = 50;
	/**
	 * Top limit to stop the slide at when raising, in ticks.
	 *
	 * @see Slide
	 */
	public static final int SLIDE_TOP_LIMIT = 9250;

	/**
	 * Size of AprilTags, in meters.
	 *
	 * @see ParkingAuton
	 */
	public static final double APRIL_TAG_SIZE = 0.166;
	/**
	 * ID of AprilTag for left parking space.
	 *
	 * @see ParkingAuton
	 */
	public static final int TAG_LEFT = 18;
	/**
	 * ID of AprilTag for middle parking space.
	 *
	 * @see ParkingAuton
	 */
	public static final int TAG_MIDDLE = 3;
	/**
	 * ID of AprilTag for right parking space.
	 *
	 * @see ParkingAuton
	 */
	public static final int TAG_RIGHT = 7;
}
