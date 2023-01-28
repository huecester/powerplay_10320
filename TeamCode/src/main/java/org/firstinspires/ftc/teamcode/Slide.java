package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Config.SLIDE_DOWN_POSITION;
import static org.firstinspires.ftc.teamcode.Config.SLIDE_POWER;
import static org.firstinspires.ftc.teamcode.Config.SLIDE_UP_POSITION;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * A wrapper around a one-motor linear slide.
 */
public class Slide {
	private final DcMotorEx motor;

	/**
	 * Create a slide system.
	 *
	 * @param hardwareMap Hardware map used to initialize the motor.
	 * @param telemetry Telemetry object for logging.
	 */
	public Slide(HardwareMap hardwareMap, Telemetry telemetry) {
		telemetry.log().add("Setting up slide hardware...");
		motor = hardwareMap.get(DcMotorEx.class, "slide");

		telemetry.log().add("Configuring motor...");
		motor.setDirection(DcMotorSimple.Direction.FORWARD);
		motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		motor.setPower(SLIDE_POWER);

		telemetry.log().add("Slide is ready.");
	}

	/**
	 * Control the slide using a gamepad. [A] raises the slide, and [B] lowers it.
	 *
	 * @param gamepad Gamepad to use.
	 * @see GamepadEx
	 */
	public void control(GamepadEx gamepad) {
		if (gamepad.didFall(GamepadEx.Button.A)) {
			raise();
		} else if (gamepad.didFall(GamepadEx.Button.B)) {
			lower();
		}
	}

	/**
	 * Raise the slide. Based on Config.SLIDE_UP_POSITION.
	 *
	 * @see Config
	 */
	public void raise() {
		motor.setTargetPosition(SLIDE_UP_POSITION);
	}

	/**
	 * Lower the slide. Based on Config.SLIDE_DOWN_POSITION.
	 *
	 * @see Config
	 */
	public void lower() {
		motor.setTargetPosition(SLIDE_DOWN_POSITION);
	}
}
