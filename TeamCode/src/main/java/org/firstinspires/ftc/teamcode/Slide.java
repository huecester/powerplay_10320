package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Config.SLIDE_BOTTOM_LIMIT;
import static org.firstinspires.ftc.teamcode.Config.SLIDE_VELOCITY;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * A wrapper around a one-motor linear slide.
 */
public class Slide {
	private final DcMotorEx motor;

	private final Telemetry.Item debugItem;

	/**
	 * Create a slide system.
	 *
	 * @param hardwareMap Hardware map used to initialize the motor.
	 * @param telemetry Telemetry object for logging.
	 */
	public Slide(HardwareMap hardwareMap, Telemetry telemetry) {
		telemetry.log().add("Setting up slide...");

		debugItem = telemetry.addData("[DEBUG] Slide", "");

		telemetry.log().add("Setting up slide hardware...");
		motor = hardwareMap.get(DcMotorEx.class, "slide");

		telemetry.log().add("Configuring motor...");
		motor.setDirection(DcMotorSimple.Direction.FORWARD);
		motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

		telemetry.log().add("Slide is ready.");
	}

	/**
	 * Control the slide using a gamepad. [A] raises the slide, and [B] lowers it.
	 *
	 * @param gamepad Gamepad to use.
	 * @see GamepadEx
	 */
	public void control(GamepadEx gamepad) {
		debugItem.setValue(motor.getCurrentPosition());

		if (gamepad.isPressed(GamepadEx.Button.A)) {
			raise();
		} else if (gamepad.isPressed(GamepadEx.Button.B)) {
			lower();
		} else {
			stop();
		}
	}

	/**
	 * Raise the slide.
	 */
	public void raise() {
		/*
		if (motor.getCurrent() >= SLIDE_TOP_LIMIT)
			stop();
		else
		*/
			motor.setVelocity(SLIDE_VELOCITY);
	}

	/**
	 * Lower the slide.
	 */
	public void lower() {
		if (motor.getCurrentPosition() <= SLIDE_BOTTOM_LIMIT)
			stop();
		else
			motor.setVelocity(-SLIDE_VELOCITY);
	}

	/**
	 * Stop the slide.
	 */
	public void stop() {
		motor.setVelocity(0);
	}
}
