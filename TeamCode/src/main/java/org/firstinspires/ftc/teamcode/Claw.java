package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Config.CLAW_CLOSE_POSITION;
import static org.firstinspires.ftc.teamcode.Config.CLAW_OPEN_POSITION;
import static org.firstinspires.ftc.teamcode.Config.DECIMAL_FORMAT;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * A wrapper around a two-servo claw.
 */
public class Claw {
	private final ServoImplEx left;
	private final ServoImplEx right;

	private final Telemetry.Item leftItem;
	private final Telemetry.Item rightItem;

	/**
	 * Create a claw system.
	 *
	 * @param hardwareMap Hardware map used to initialize servos.
	 * @param telemetry Telemetry object for logging.
	 */
	public Claw(HardwareMap hardwareMap, Telemetry telemetry) {
		telemetry.log().add("Creating telemetry items...");
		leftItem = telemetry.addData("[DEBUG] Left", DECIMAL_FORMAT, 0.0);
		rightItem = telemetry.addData("[DEBUG] Right", DECIMAL_FORMAT, 0.0);

		telemetry.log().add("Setting up claw hardware...");
		left = hardwareMap.get(ServoImplEx.class, "clawLeft");
		right = hardwareMap.get(ServoImplEx.class, "clawRight");

		telemetry.log().add("Configuring servos...");
		left.setDirection(Servo.Direction.FORWARD);
		right.setDirection(Servo.Direction.REVERSE);

		telemetry.log().add("Claw is ready.");
	}

	/**
	 * Control the slide using a gamepad.
	 *
	 * @param gamepad Gamepad to use.
	 * @see GamepadEx
	 */
	public void control(GamepadEx gamepad) {
		leftItem.setValue(left.getPosition());
		rightItem.setValue(right.getPosition());

		if (gamepad.didFall(GamepadEx.Button.LB)) {
			close();
		} else if (gamepad.didFall(GamepadEx.Button.RB)) {
			open();
		}
	}

	/**
	 * Close the claw. Position is based on Config.CLAW_CLOSE_POSITION.
	 *
	 * @see Config
	 */
	public void close() {
		left.setPosition(CLAW_CLOSE_POSITION);
		right.setPosition(CLAW_CLOSE_POSITION);
	}

	/**
	 * Open the claw. Position is based on Config.CLAW_OPEN_POSITION.
	 *
	 * @see Config
	 */
	public void open() {
		left.setPosition(CLAW_OPEN_POSITION);
		right.setPosition(CLAW_OPEN_POSITION);
	}
}
