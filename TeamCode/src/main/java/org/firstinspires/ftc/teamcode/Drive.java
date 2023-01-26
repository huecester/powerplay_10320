package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Config.DECIMAL_FORMAT;
import static org.firstinspires.ftc.teamcode.Config.LOGO_DIRECTION;
import static org.firstinspires.ftc.teamcode.Config.STRAFING_MODIFIER;
import static org.firstinspires.ftc.teamcode.Config.USB_DIRECTION;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class Drive {
	private final IMU imu;

	private final DcMotorEx frontLeft;
	private final DcMotorEx backLeft;
	private final DcMotorEx frontRight;
	private final DcMotorEx backRight;

	private final Telemetry.Item headingItem;
	private final Telemetry.Item gamepadXItem;
	private final Telemetry.Item gamepadYItem;
	private final Telemetry.Item gamepadTurnItem;

	private final boolean useHeading;

	// TODO: remove
	private final Telemetry.Item debugItem;

	public Drive(HardwareMap hardwareMap, Telemetry telemetry) {
		this(hardwareMap, telemetry, true);
	}

	public Drive(HardwareMap hardwareMap, Telemetry telemetry, boolean useHeading) {
		this.useHeading = useHeading;

		telemetry.log().add("Creating telemetry entries...");
		if (useHeading) {
			headingItem = telemetry.addData("Heading", "Press [Y] to reset.");
		} else {
			headingItem = telemetry.addData("Heading", "Heading is disabled.");
		}
		Telemetry.Line gamepadLine = telemetry.addLine("Gamepad");
		gamepadXItem = gamepadLine.addData("X", DECIMAL_FORMAT, 0.0);
		gamepadYItem = gamepadLine.addData("Y", DECIMAL_FORMAT, 0.0);
		gamepadTurnItem = gamepadLine.addData("Turn", DECIMAL_FORMAT, 0.0);
		debugItem = telemetry.addData("[DEBUG]", "");

		telemetry.log().add("Setting up driving hardware...");
		frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
		backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
		frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
		backRight = hardwareMap.get(DcMotorEx.class, "backRight");
		imu = hardwareMap.get(IMU.class, "imu");

		initialize(telemetry);

		telemetry.log().add("Driving is ready.");
	}

	public void tick(Gamepad gamepad) {
		if (useHeading) {
			if (gamepad.y) {
				imu.resetYaw();
				headingItem.setValue("Heading is reset.");
			} else {
				headingItem.setValue("Press [Y] to reset.");
			}
		}

		gamepadXItem.setValue(DECIMAL_FORMAT, gamepad.left_stick_x);
		gamepadYItem.setValue(DECIMAL_FORMAT, -gamepad.left_stick_y);
		gamepadTurnItem.setValue(DECIMAL_FORMAT, gamepad.right_stick_x);

		// https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html#robot-centric-final-sample-code
		double y = -gamepad.left_stick_y;
		double x = gamepad.left_stick_x * STRAFING_MODIFIER; // Make strafing more powerful
		double turn = gamepad.right_stick_x;

		// https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html#field-centric
		if (useHeading) {
			YawPitchRollAngles angles = imu.getRobotYawPitchRollAngles();
			double headingDegrees = angles.getYaw(AngleUnit.DEGREES);
			double heading = angles.getYaw(AngleUnit.RADIANS);
			debugItem.setValue("Heading: " + DECIMAL_FORMAT + " deg | " + DECIMAL_FORMAT + " rad", headingDegrees, heading);

			x = x * Math.cos(heading) - y * Math.sin(heading);
			y = x * Math.sin(heading) + y * Math.cos(heading);
		}

		// Clip power range to [0.0, 1.0]
		double denominator = Math.max(Math.abs(x) + Math.abs(y) + Math.abs(turn), 1);
		frontLeft.setPower((y + x + turn) / denominator);
		backLeft.setPower((y - x + turn) / denominator);
		frontRight.setPower((y - x - turn) / denominator);
		backRight.setPower((y + x - turn) / denominator);
	}

	private void initialize(Telemetry telemetry) {
		telemetry.log().add("Configuring motors...");
		frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
		backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
		frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
		backRight.setDirection(DcMotorSimple.Direction.FORWARD);

		frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

		frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

		frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

		telemetry.log().add("Initializing IMU...");
		imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(LOGO_DIRECTION, USB_DIRECTION)));
	}
}
