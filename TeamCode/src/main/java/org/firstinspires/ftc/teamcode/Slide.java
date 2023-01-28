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

public class Slide {
	private final DcMotorEx motor;

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

	public void control(GamepadEx gamepad) {
		if (gamepad.didFall(GamepadEx.Button.A)) {
			raise();
		} else if (gamepad.didFall(GamepadEx.Button.B)) {
			lower();
		}
	}

	public void raise() {
		motor.setTargetPosition(SLIDE_UP_POSITION);
	}

	public void lower() {
		motor.setTargetPosition(SLIDE_DOWN_POSITION);
	}
}
