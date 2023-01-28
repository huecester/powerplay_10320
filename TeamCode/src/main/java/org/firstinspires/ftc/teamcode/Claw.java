package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Config.CLAW_CLOSE_POSITION;
import static org.firstinspires.ftc.teamcode.Config.CLAW_OPEN_POSITION;
import static org.firstinspires.ftc.teamcode.Config.DECIMAL_FORMAT;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw {
	private final ServoImplEx left;
	private final ServoImplEx right;

	private final Telemetry.Item leftItem;
	private final Telemetry.Item rightItem;

	public Claw(HardwareMap hardwareMap, Telemetry telemetry) {
		telemetry.log().add("Creating telemetry items...");
		leftItem = telemetry.addData("[DEBUG] Left", DECIMAL_FORMAT, 0.0);
		rightItem = telemetry.addData("[DEBUG] Right", DECIMAL_FORMAT, 0.0);

		telemetry.log().add("Setting up claw hardware...");
		left = hardwareMap.get(ServoImplEx.class, "clawLeft");
		right = hardwareMap.get(ServoImplEx.class, "clawRight");

		telemetry.log().add("Configuring servos...");
		left.setDirection(Servo.Direction.FORWARD);
		right.setDirection(Servo.Direction.FORWARD);

		telemetry.log().add("Claw is ready.");
	}

	public void control(GamepadEx gamepad) {
		leftItem.setValue(left.getPosition());
		rightItem.setValue(right.getPosition());

		if (gamepad.didFall(GamepadEx.Button.LB)) {
			close();
		} else if (gamepad.didFall(GamepadEx.Button.RB)) {
			open();
		}
	}

	public void close() {
		left.setPosition(CLAW_CLOSE_POSITION);
		right.setPosition(CLAW_CLOSE_POSITION);
	}

	public void open() {
		left.setPosition(CLAW_OPEN_POSITION);
		right.setPosition(CLAW_OPEN_POSITION);
	}
}
