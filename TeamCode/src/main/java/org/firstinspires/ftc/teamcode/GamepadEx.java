package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * A wrapper around a Gamepad. Provides additional utility methods, such as didFall and didRise to detect falling-/rising-edge button presses.
 */
public class GamepadEx {
	/**
	 * A gamepad button.
	 */
	public enum Button {
		A,
		B,
		X,
		Y,
		LB,
		RB,
		UP,
		DOWN,
		LEFT,
		RIGHT,
		BACK,
		GUIDE,
		START,
		LS,
		RS,
	}

	private final Gamepad previous = new Gamepad();
	private final Gamepad current = new Gamepad();

	/**
	 * Update the gamepad state. Should be called every loop, before using anything that uses the gamepad.
	 *
	 * @param gamepad Gamepad to update state from.
	 */
	public void tick(Gamepad gamepad) {
		previous.copy(current);
		current.copy(gamepad);
	}

	/**
	 * Checks for a rising-edge press from a button.
	 *
	 * @param button Button to check.
	 * @return Returns true if the button did rise (was pressed).
	 */
	public boolean didRise(Button button) {
		switch (button) {
			case A:
				return !previous.a && current.a;
			case B:
				return !previous.b && current.b;
			case X:
				return !previous.x && current.x;
			case Y:
				return !previous.y && current.y;
			case LB:
				return !previous.left_bumper && current.left_bumper;
			case RB:
				return !previous.right_bumper && current.right_bumper;
			case UP:
				return !previous.dpad_up && current.dpad_up;
			case DOWN:
				return !previous.dpad_down && current.dpad_down;
			case LEFT:
				return !previous.dpad_left && current.dpad_left;
			case RIGHT:
				return !previous.dpad_right && current.dpad_right;
			case BACK:
				return !previous.back && current.back;
			case GUIDE:
				return !previous.guide && current.guide;
			case START:
				return !previous.start && current.start;
			case LS:
				return !previous.left_stick_button && current.left_stick_button;
			case RS:
				return !previous.right_stick_button && current.right_stick_button;
			default:
				return false;
		}
	}

	/**
	 * Checks for a falling-edge press from a button.
	 *
	 * @param button Button to check.
	 * @return Returns true if the button did fall (was released).
	 */
	public boolean didFall(Button button) {
		switch (button) {
			case A:
				return previous.a && !current.a;
			case B:
				return previous.b && !current.b;
			case X:
				return previous.x && !current.x;
			case Y:
				return previous.y && !current.y;
			case LB:
				return previous.left_bumper && !current.left_bumper;
			case RB:
				return previous.right_bumper && !current.right_bumper;
			case UP:
				return previous.dpad_up && !current.dpad_up;
			case DOWN:
				return previous.dpad_down && !current.dpad_down;
			case LEFT:
				return previous.dpad_left && !current.dpad_left;
			case RIGHT:
				return previous.dpad_right && !current.dpad_right;
			case BACK:
				return previous.back && !current.back;
			case GUIDE:
				return previous.guide && !current.guide;
			case START:
				return previous.start && !current.start;
			case LS:
				return previous.left_stick_button && !current.left_stick_button;
			case RS:
				return previous.right_stick_button && !current.right_stick_button;
			default:
				return false;
		}
	}

	public boolean isPressed(Button button) {
		switch (button) {
			case A:
				return current.a;
			case B:
				return current.b;
			case X:
				return current.x;
			case Y:
				return current.y;
			case LB:
				return current.left_bumper;
			case RB:
				return current.right_bumper;
			case UP:
				return current.dpad_up;
			case DOWN:
				return current.dpad_down;
			case LEFT:
				return current.dpad_left;
			case RIGHT:
				return current.dpad_right;
			case BACK:
				return current.back;
			case GUIDE:
				return current.guide;
			case START:
				return current.start;
			case LS:
				return current.left_stick_button;
			case RS:
				return current.right_stick_button;
			default:
				return false;
		}
	}

	/**
	 * Get the left stick's X value.
	 *
	 * @return Returns the left stick's X value.
	 */
	public double getLeftX() {
		return current.left_stick_x;
	}

	/**
	 * Get the left stick's Y value.
	 *
	 * @return Returns the left stick's Y value.
	 */
	public double getLeftY() {
		return current.left_stick_y;
	}

	/**
	 * Get the right stick's X value.
	 *
	 * @return Returns the right stick's X value.
	 */
	public double getRightX() {
		return current.right_stick_x;
	}

	/**
	 * Get the right stick's Y value.
	 *
	 * @return Returns the right stick's Y value.
	 */
	public double getRightY() {
		return current.right_stick_y;
	}
}
